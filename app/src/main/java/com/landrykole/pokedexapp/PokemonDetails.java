package com.landrykole.pokedexapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetails extends AppCompatActivity {

    private TextView nameTextView, type1TextView, type2TextView, pokemonWeaknesses;
    private ImageView pokemonImageView;
    List<String> pkmnWeaknesses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_details);

        nameTextView = findViewById(R.id.pokemonName);
        type1TextView = findViewById(R.id.pkmnDetailsType1);
        type2TextView = findViewById(R.id.pkmnDetailsType2);
        pokemonImageView = findViewById(R.id.pokemonImg);
        //pokemonWeaknesses = findViewById(R.id.weaknessTypes);

        // Get the intent data passed from MainActivity
        Intent intent = getIntent();
        String pokemonName = intent.getStringExtra("name");
        String type1 = intent.getStringExtra("type1");
        String type2 = intent.getStringExtra("type2");
        int imageResId = intent.getIntExtra("imageResId", -1);

        // Set the data in the UI
        nameTextView.setText(pokemonName);
        type1TextView.setText(type1);
        type2TextView.setText(type2);
        pokemonImageView.setImageResource(imageResId);
    }
}
