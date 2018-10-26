package com.example.huilee.newandroidxproject.storagedata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huilee.newandroidxproject.R;

public class SqlDataSaveActivity extends AppCompatActivity {

    private Button createData;
    private Button addData;
    private Button updateData;
    private Button deleteData;
    private Button queryData;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        createData = findViewById(R.id.btn1);
        addData = findViewById(R.id.btn3);
        updateData = findViewById(R.id.btn4);
        deleteData = findViewById(R.id.btn5);
        queryData = findViewById(R.id.btn6);

        dbHelper = new MyDatabaseHelper(this, "BookStore.db",null, 2);

        createData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SQLiteDatabase db = dbHelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("name","The magic");
                values.put("author","Dan Brown");
                values.put("pages",635);
                values.put("price",38.4);
                db.insert("Book",null,values);
                values.clear();

                values.put("name","The lost symbol");
                values.put("author","Dan Brown");
                values.put("pages",325);
                values.put("price",10.4);
                db.insert("Book",null,values);
            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",495.89);
                db.update("Book",values,"name = ?", new String[]{"The magic"});
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                db.delete("Book","pages>?", new String[]{"500"});
            }
        });

        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d("LHF","name :"+name+", author : "+author +",pages : "+pages+", price: "+price);

                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });


    }




}
