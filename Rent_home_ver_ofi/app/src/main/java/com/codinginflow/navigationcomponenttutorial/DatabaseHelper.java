package com.codinginflow.navigationcomponenttutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {


    //    public static final String TABLE_NAME="Users";
//
//    public static final String COLUMN_ID="_id";
//    public static final String COLUMN_USERNAME="NazwaUżytkownika";
//    public static final String COLUMNT_EMAIL="Email";
//    public static final String COLUMNT_PASSWORD="Haslo";
//    public static final String COLUMNT_ACTIVITY="Aktywnosc";
//    public static final String COLUMNT_ACTIVITY="Aktywnosc";


    private Context context;
    private static final String DATABASE_NAME = "RentHomeLibrary.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_NAME = "Ogloszenia";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_OWNER = "Gospodarz";
    private static final String COLUMN_PRICE = "Cena";
    private static final String COLUMN_LAT = "Długosc";
    private static final String COLUMN_LONG = "Szerokosc";
    private static final String COLUMN_ACTIVITY = "Aktywnosc";
    private static final String COLUMN_DESCRIPTION = "Opis";
    private static final String COLUMN_IMG = "Zdjecie";
    private static final String COLUMN_DATATIME = "Data";
    private static final String COLUMN_TITLE = "Tytuł";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_OWNER + " TEXT, " +
                COLUMN_LAT + " REAL, " +
                COLUMN_LONG + " REAL, " +
                COLUMN_ACTIVITY + " INTEGRER, " +
                COLUMN_IMG + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATATIME + " INTEGRER, " +
                COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addOgloszenie(String title, String data, int price,int activity,String img,String description,String owner,int lat,int LONG){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DATATIME, data);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ACTIVITY,activity );
        cv.put(COLUMN_IMG, img);
        cv.put(COLUMN_DESCRIPTION,description );
        cv.put(COLUMN_OWNER, owner);
        cv.put(COLUMN_LAT,lat );
        cv.put(COLUMN_LONG,LONG);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
//
//    void updateData(String row_id, String title, String author, String pages){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_TITLE, title);
//        cv.put(COLUMN_AUTHOR, author);
//        cv.put(COLUMN_PAGES, pages);
//
//        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    void deleteOneRow(String row_id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    void deleteAllData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_NAME);
//    }
}
