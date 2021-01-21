package com.example.lista13;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Stats extends AppCompatActivity {
    SQLiteDatabase database;
    int[] columnIndices = new int[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        database = openOrCreateDatabase("COVID", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS COVID (country String, cases double , active double,casesPerOneMillion double,testsPerOneMillion double)";
        database.execSQL(sqlDB);


        String sqlCount = "SELECT sum(cases) FROM COVID";
        Cursor cursor= database.rawQuery(sqlCount,null);
        cursor.moveToFirst();
        int sumCases = cursor.getInt(0);
        cursor.close();

        TextView textStats1 = (TextView) findViewById(R.id.stats1);
        textStats1.setText("SUM OF CASES: " +sumCases);


        String sqlCount1= "SELECT country FROM COVID order by (cases-active)/cases*100 desc";
        Cursor cursor1 = database.rawQuery(sqlCount1,null);
        cursor1.moveToFirst();
        String procCases = cursor1.getString(0);
        cursor1.close();

        TextView textStats2 = (TextView) findViewById(R.id.stats2);
        textStats2.setText("HIGHEST CURED PERCENT IN: " +procCases);



        ArrayList<String> statsTests= new ArrayList<>();
        String sqlCount2= "SELECT country FROM COVID order by testsPerOneMillion desc";
        Cursor cursor2 = database.rawQuery(sqlCount2,null);
        if (cursor2.moveToFirst()) {

            do {
                String country = cursor2.getString(cursor2.getColumnIndex("country"));

                statsTests.add(country);
            } while (cursor2.moveToNext());
        }

        cursor1.close();

        TextView textStats3 = (TextView) findViewById(R.id.stats3);
        textStats3.setText("TESTS PER ONE MILLION (DESCENDING): " + statsTests.toString());




    }
}