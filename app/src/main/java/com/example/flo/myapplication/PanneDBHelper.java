package com.example.flo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Flo on 22.06.2015.
 */
public class PanneDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "panneManager";

    // Contacts table name
    private static final String TABLE_PANNEN = "panne";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SYMPTOM = "symptom";

    private static final String KEY_ANZSCHRITTE = "anzschritte";
    private static final String KEY_SCHRITTE = "schritte";
    private static final String KEY_BILDER = "bilder";
    private static final String KEY_FAEHRTNOCH = "faehrtnoch";


    public PanneDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_PANNE_TABLE = "CREATE TABLE " + TABLE_PANNEN + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SYMPTOM + " TEXT," + KEY_ANZSCHRITTE + " TEXT," + KEY_SCHRITTE + " TEXT," + KEY_BILDER + " TEXT," + KEY_FAEHRTNOCH + " TEXT" + ")";

        db.execSQL(CREATE_PANNE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANNEN);

        // Create tables again
        onCreate(db);
    }


    // Adding new panne
    void addPanne(Panne panne) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, panne.getName());
        values.put(KEY_SYMPTOM, panne.getSymptom());
        values.put(KEY_ANZSCHRITTE, panne.getNumberOfSteps());
        values.put(KEY_SCHRITTE, panne.getSteps());
        values.put(KEY_BILDER, panne.getPictures());
        values.put(KEY_FAEHRTNOCH, panne.getDriveAble());

        // Inserting Row
        db.insert(TABLE_PANNEN, null, values);
        db.close(); // Closing database connection
    }

    Panne getPanne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PANNEN, new String[]{KEY_ID,
                        KEY_NAME, KEY_NAME, KEY_SYMPTOM, KEY_ANZSCHRITTE, KEY_SCHRITTE, KEY_BILDER, KEY_FAEHRTNOCH}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Panne panne = new Panne(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5), cursor.getString(6));
        // return contact
        return panne;
    }

    public ArrayList<Panne> getAllPannen() {
        ArrayList<Panne> pannenList = new ArrayList<Panne>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PANNEN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Panne panne = new Panne();
                panne.setId(Integer.parseInt(cursor.getString(0)));
                panne.setName(cursor.getString(1));
                panne.setSymptom(cursor.getString(2));
                panne.setNumberOfSteps(Integer.parseInt(cursor.getString(3)));
                panne.setSteps(cursor.getString(4));
                panne.setPictures(cursor.getString(5));
                panne.setDriveAble(cursor.getString(6));
                // Adding contact to list
                pannenList.add(panne);
            } while (cursor.moveToNext());
        }

        // return contact list
        return pannenList;
    }

    public int updatePanne(Panne panne) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, panne.getName());
        values.put(KEY_SYMPTOM, panne.getSymptom());
        values.put(KEY_ANZSCHRITTE, panne.getNumberOfSteps());
        values.put(KEY_SCHRITTE, panne.getSteps());
        values.put(KEY_BILDER, panne.getPictures());
        values.put(KEY_FAEHRTNOCH, panne.getDriveAble());


        // updating row
        return db.update(TABLE_PANNEN, values, KEY_ID + " = ?",
                new String[]{String.valueOf(panne.getId())});
    }

    public void deletePanne(Panne panne) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PANNEN, KEY_ID + " = ?",
                new String[]{String.valueOf(panne.getId())});
        db.close();
    }

    public boolean deleteAllPannen() {

        SQLiteDatabase db = this.getWritableDatabase();
        int doneDelete = 0;
        doneDelete = db.delete(TABLE_PANNEN, null, null);
        return doneDelete > 0;

    }

    public int getPannenCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PANNEN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
