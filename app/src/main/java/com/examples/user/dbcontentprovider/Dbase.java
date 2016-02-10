package com.examples.user.dbcontentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/5/2016.
 */
public class Dbase extends SQLiteOpenHelper {
    private static final String LOGCAT = null;
    public static String Tablename = "ReadText";
    public static String ID = "_id";
    public static final String colm = "name";
    private static final String col2="date";
    static final String CREATE_DB_TABLE = ("CREATE TABLE " + Tablename + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");


    public Dbase(Context ctx) {
        super(ctx, "Character.db", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //query = "CREATE TABLE "+Tablename +"( _id INTEGER PRIMARY KEY AUTOINCREMENT, colm TEXT)";
        db.execSQL(CREATE_DB_TABLE);

        Log.d(LOGCAT, "animals Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query;
        query = "DROP TABLE IF EXISTS" + Tablename;
        db.execSQL(query);
        onCreate(db);
    }

    private boolean update(String id, String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put(colm, name);
        database.update(Tablename, values, "_id=?", new String[]{id});
        return true;
    }

    public boolean insertpics(String name,String date) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.e("values", String.valueOf(values));


        values.put(colm, name);
       // values.put(col2,date);

        // values.put("ReadText", (String) values.get("ReadText"));
        database.insert(Tablename, null, values);
        Log.e("Data in db", "result");
        database.close();


        return (true);

    }


    public Cursor getdata() {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "select * from " + Tablename;
        Cursor c = database.rawQuery(selectQuery, null);

        return c;


    }

}
