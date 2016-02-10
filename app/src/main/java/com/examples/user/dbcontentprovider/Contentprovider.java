package com.examples.user.dbcontentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by User on 2/8/2016.
 */
public class Contentprovider extends ContentProvider {
    private Dbase db;
    private SQLiteDatabase database;
    private static final String AUTHORITY = "com.examples.user.dbcontentprovider.Contentprovider";
    public static final int uricode = 1;
    public static final int TUTORIAL_ID = 110;


    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + Dbase.Tablename);

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/mt-tutorial";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/mt-tutorial";
    private final static UriMatcher sURIMatcher;

    static {
        sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        sURIMatcher.addURI(AUTHORITY, Dbase.Tablename, uricode);
        sURIMatcher.addURI(AUTHORITY, Dbase.Tablename + "/#", TUTORIAL_ID);
    }

    @Override
    public boolean onCreate() {
        db = new Dbase(getContext());
        return true;

    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        Cursor c;
        database = db.getReadableDatabase();
        c = database.query(db.Tablename, projection, selection, selectionArgs, null, null, sortOrder);


        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        database = db.getWritableDatabase();
        if (sURIMatcher.match(uri) == uricode) {
            database.insert(db.Tablename, null, values);
        }
        database.close();

        getContext().getContentResolver().notifyChange(uri, null);
        return null;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
