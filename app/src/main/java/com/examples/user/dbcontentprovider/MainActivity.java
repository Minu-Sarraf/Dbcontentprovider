package com.examples.user.dbcontentprovider;

import android.app.LoaderManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Dbase controller;
    Button btn, btn1;
    EditText et;


    public static final int LOADER = 0x01;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    ArrayList<Datalist> list = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter adapter;
    CursorLoader cursorLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Dbase(this);
        //t=(TextView)findViewById(R.id.textView3);
        btn = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button2);
        et = (EditText) findViewById(R.id.textView);
        // t= (TextView) findViewById(R.id.title1);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // Cursor tutorials = this.managedQuery(Contentprovider.CONTENT_URI, projection, null, null, null);


        adapter = new MyAdapter(null);
        mRecyclerView.setAdapter(adapter);
        // setAdapter(adapter);*/
        // btn.setOnClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SQLiteDatabase database = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                Log.e("values", String.valueOf(values));


                values.put(Dbase.colm, et.getText().toString());
                // values.put(col2,date);
                ContentResolver content = getApplicationContext().getContentResolver();
                // values.put("ReadText", (String) values.get("ReadText"));

                // Uri uri=getContentResolver().insert(Contentprovider.CONTENT_URI, values);
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                content.insert(Contentprovider.CONTENT_URI, values);


            }
        });


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {Dbase.ID, Dbase.colm};
        cursorLoader = new CursorLoader(this, Contentprovider.CONTENT_URI, null, null, null, null);
        return cursorLoader;

    }

    public void onClickDisplay(View view) {
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        c.moveToFirst();
        while (c.moveToNext()) {


            list.add(new Datalist("_id: " + c.getString(0) + " name: " + c.getString(1)));
            adapter.refresh(list);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //  adapter.swapCursor(null);

    }

}
