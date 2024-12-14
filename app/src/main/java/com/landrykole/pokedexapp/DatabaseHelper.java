package com.landrykole.pokedexapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "allPokemonDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery =
                "CREATE TABLE pokemon (" +
                        "id INTEGER PRIMARY KEY, " +
                        "name TEXT," +
                        "type1 TEXT," +
                        "type2 TEXT," +
                        "region TEXT," +
                        "collected INTEGER DEFAULT 0" + // 0 = not collected, 1 = is collected
                        ");";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pokemon");
        onCreate(db); // Recreate the table
    }

    public void addPokemon(Entity entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", entity.getId());
        values.put("name", entity.getName());
        values.put("type1", entity.getType1());
        values.put("type2", entity.getType2());
        values.put("collected", 0); // Default value is not collected

        db.insert("pokemon", null, values);
        db.close();
    }

    public void pokemonCollected(int id, boolean collected) {
        SQLiteDatabase db = this.getWritableDatabase();
        int collectedNum = collected ? 1 : 0;

        String updateQuery = "UPDATE pokemon SET collected = ? WHERE id = ?";

        db.execSQL(updateQuery, new Object[] {collectedNum, id});
        db.close();
    }

    public boolean isPokemonCaught(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("pokemon", new String[] {"collected" },
                "id = ?", new String[] {String.valueOf(id)}, null, null, null);
        boolean isCaught = false;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int collected = cursor.getInt(cursor.getColumnIndex("collected"));
                isCaught = collected == 1;
            }
            cursor.close();
        }
        return isCaught;
    }

}
