package com.landrykole.pokedexapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView entityListView;
    private List<Entity> entityList = new ArrayList<>();
    private EntityAdapter entityAdapter;
    ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set app to light / dark mode
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int darkMode = sharedPreferences.getInt("DarkMode", AppCompatDelegate.MODE_NIGHT_NO);
        AppCompatDelegate.setDefaultNightMode(darkMode);

        entityListView = findViewById(R.id.entityListView);
        progressBar = findViewById(R.id.caughtProgress);
        TextView caughtText = findViewById(R.id.caughtText);

        loadJSONFromAsset();
        entityAdapter = new EntityAdapter(this, entityList);
        entityListView.setAdapter(entityAdapter);

        ImageView menuIcon = findViewById(R.id.menu_icon);
        ImageView filterIcon = findViewById(R.id.dark_icon);

        updateCaughtProgress();

        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the dark/light mode
                toggleDarkMode();
            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(MainActivity.this, "You clicked on the menu :)", Toast.LENGTH_SHORT).show();
           }
        });
    }

    public void toggleDarkMode() {
        int mode = AppCompatDelegate.getDefaultNightMode();
        int newMode;

        if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
            newMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else {
            newMode = AppCompatDelegate.MODE_NIGHT_YES;
        }

        Toast.makeText(MainActivity.this, "Applying new theme :)", Toast.LENGTH_SHORT).show();

        AppCompatDelegate.setDefaultNightMode(newMode); // Apply new theme
        recreate();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("DarkMode", newMode);
        editor.apply();
    }

    // Update the progress bar
    public void updateCaughtProgress() {
        int caughtCount = 0;
        int totalPokemon = 50;

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        for (Entity entity : entityList) {
            if (dbHelper.isPokemonCaught(entity.getId())) {
                caughtCount++;
            }
        }

        progressBar.setProgress(caughtCount);
        TextView caughtText = findViewById(R.id.caughtText);
        caughtText.setText("Collected: " + caughtCount + "/" + totalPokemon);
    }

    private void loadJSONFromAsset() {
        try {
            InputStream is = getAssets().open("original151Pokemon.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonContent = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonContent);

            // Initialize the database helper
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Entity entity = new Entity(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("type1"),
                        jsonObject.getString("type2"),
                        this
                );
                entityList.add(entity);

                dbHelper.addPokemon(entity);
            }

            entityAdapter.notifyDataSetChanged(); // Update the adapter
            SearchView searchView = findViewById(R.id.searchView);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;  // No need to do anything when the search is submitted
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText != null) {
                        entityAdapter.filter(newText); // Filter the Pokémon list based on the query

                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}