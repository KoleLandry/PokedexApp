package com.landrykole.pokedexapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EntityAdapter extends BaseAdapter {
    private Context context;
    private List<Entity> entityList;
    private List<Entity> filteredEntityList;
    private boolean isCaught = false;

    public EntityAdapter(Context context, List<Entity> entityList) {
        this.context = context;
        this.entityList = entityList;
        this.filteredEntityList = new ArrayList<>(entityList);
    }

    @Override
    public int getCount() {
        return filteredEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.entity_item, parent, false);
        }

        TextView pokedexNum = convertView.findViewById(R.id.pokedexNum);
        TextView pokemonName = convertView.findViewById(R.id.pokemonName);
        ImageView pkmnImg = convertView.findViewById(R.id.pokemonImg);
        ImageView collectIcon = convertView.findViewById(R.id.collectIcon);

        RelativeLayout pokemonLayout = convertView.findViewById(R.id.pokemonLayout);

        Entity entity = (Entity) getItem(position);
        int id = entity.getId();

        boolean isCaught = dbHelper.isPokemonCaught(id);

        pokedexNum.setText(String.format("#%04d", entity.getId())); // Format number to 4 digits
        pokemonName.setText(entity.getName());

        pkmnImg.setImageResource(entity.getImageResources());

        if (isCaught) {
            collectIcon.setImageResource(R.drawable.icon_caught);
        } else {
            collectIcon.setImageResource(R.drawable.icon_missing);
        }

        // THIS SHOULD LAUNCH THE DETAILS STUFFS
        pokemonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the details activity
                Intent intent = new Intent(context, PokemonDetails.class);
                intent.putExtra("name", entity.getName());
                intent.putExtra("type1", entity.getType1());
                intent.putExtra("type2", entity.getType2());
                intent.putExtra("imageResId", entity.getImageResources());
                context.startActivity(intent);
            }
        });



        pkmnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked on " + entity.getName() + "!", Toast.LENGTH_SHORT).show();
            }
        });

        collectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newIsCaught = !isCaught;
                dbHelper.pokemonCollected(id, newIsCaught);

                // Update UI with new status
                if (newIsCaught) {
                    collectIcon.setImageResource(R.drawable.icon_caught);
                } else {
                    collectIcon.setImageResource(R.drawable.icon_missing);
                }

                if (context instanceof MainActivity) {
                    ((MainActivity) context).updateCaughtProgress();
                }
            }
        });

        return convertView;
    }

    // Add a method to filter the list based on search query
    public void filter(String query) {
        if (query == null) {
            query = "";
        } else {
            query = query.toLowerCase();
            query = query.trim();
        }
        filteredEntityList.clear();

        if (query.isEmpty()) {
            filteredEntityList.addAll(entityList);
        } else {
            for (Entity entity : entityList) {
                if (entity.getName().toLowerCase().contains(query)) {
                    filteredEntityList.add(entity);
                }
            }
        }
        notifyDataSetChanged();
    }
}
