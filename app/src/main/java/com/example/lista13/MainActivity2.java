package com.example.lista13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    public final static ArrayList<String> lista= new ArrayList<>();
    SQLiteDatabase database;
    int[] columnIndices = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = openOrCreateDatabase("COVID", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS COVID (country String, cases double , active double,casesPerOneMillion double,testsPerOneMillion double)";
        database.execSQL(sqlDB);


        ArrayList<String> wyniki = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT country,cases,active,casesPerOneMillion,testsPerOneMillion FROM COVID",null);

            if (c.moveToFirst()) {

                do {
                    String country = c.getString(c.getColumnIndex("country"));
                    double cases = c.getDouble(c.getColumnIndex("cases"));
                    double active = c.getDouble(c.getColumnIndex("active"));
                    double casesPerOneMillion = c.getDouble(c.getColumnIndex("casesPerOneMillion"));
                    double testsPerOneMillion = c.getDouble(c.getColumnIndex("testsPerOneMillion"));

                    wyniki.add("Country: " + country + ", C/A: " + cases+"/" + active + ", casesPerOneMillion: " + casesPerOneMillion + ", testPerOneMillion: " + testsPerOneMillion);

                } while (c.moveToNext());
            }


        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,wyniki);
        listView.setAdapter(adapter);
        c.close();




    }
}