package com.example.lista13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

public class AddCountry extends AppCompatActivity {
    public final static String MESSAGE="message";
    SQLiteDatabase database;
    int[] columnIndices = new int[5];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        database = openOrCreateDatabase("COVID", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS COVID (country String, cases double , active double,casesPerOneMillion double,testsPerOneMillion double)";
        database.execSQL(sqlDB);

    }

    public void onBtnAdd(View view) {

        final EditText editText = (EditText) findViewById(R.id.editTextCountry); //wpis
        String dane = editText.getText().toString();
        String url = "https://coronavirus-19-api.herokuapp.com/countries/"+dane;


        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response ->
        {
            COVIDData test = gson.fromJson(response, COVIDData.class);
            Map p=gson.fromJson(response.toString(), Map.class);
            for(Object key:p.keySet());
            String country=p.get("country").toString();
            double active= (double)p.get("cases");
            double cases= (double) p.get("active");
            double casesPerOneMillion=(double) p.get("casesPerOneMillion");
            double testsPerOneMillion=(double) p.get("testsPerOneMillion");

            COVIDData covidData =new COVIDData(country,active,cases,casesPerOneMillion,testsPerOneMillion);

            String sqlCovid = "INSERT INTO COVID VALUES (?,?,?,?,?)";
            SQLiteStatement statement = database.compileStatement(sqlCovid);
            statement.bindString(1, covidData.getCountry_name());
            statement.bindDouble(2, covidData.getCases());
            statement.bindDouble(3, covidData.getActive());
            statement.bindDouble(4, covidData.getCasesPerOneMillion());
            statement.bindDouble(5, covidData.getTestsPerOneMillion());

            statement.executeInsert();
            Toast.makeText(this,"Data added for " +country,Toast.LENGTH_LONG).show();

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);





    }
}