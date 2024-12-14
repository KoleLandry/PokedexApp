package com.landrykole.pokedexapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Entity {
    private int id;
    private String name;
    private String type1;
    private String type2;
    private int imageResource;
    private boolean isCaught;

    // Constructor
    public Entity(int id, String name, String type1, String type2, Context context) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.imageResource = getImageResourceById(id, context);

        // Get "isCaught" from database
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        this.isCaught = dbHelper.isPokemonCaught(id);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType1() { return type1; }

    public String getType2() { return type2; }

    public int getImageResources() {
        return imageResource;
    }

    public boolean isCaught() {
        return isCaught;
    }

    private int getImageResourceById(int id, Context context) {
        String[] potentialImages = {
                "poke_capture_" + String.format("%04d", id) + "_000_uk_n_00000000_f_n", // Image for unknown gender
                "poke_capture_" + String.format("%04d", id) + "_000_mf_n_00000000_f_n", // Image for male/female
                "poke_capture_" + String.format("%04d", id) + "_000_md_n_00000000_f_n", // Image for male variant
                "poke_capture_" + String.format("%04d", id) + "_000_fd_n_00000000_f_n", // Image for female variant
                "poke_capture_" + String.format("%04d", id) + "_000_mo_n_00000000_f_n", // Image for male only
                "poke_capture_" + String.format("%04d", id) + "_000_fo_n_00000000_f_n" // Image for female only
        };

        for (String imageName : potentialImages) {
            int imageResource = getResourceId(imageName, context);

            if (imageResource != 0) {
                return imageResource;
            }
        }

        // If we can't find the image, show an egg (default image)
        return getResourceId("poke_capture_0000_000_uk_n_00000000_f_n", context);
    }

    // Method to get the resource ID for the image
    private int getResourceId(String imageName, Context context) {
        // Assuming images are stored in the res/drawable folder
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
}
