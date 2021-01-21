package com.example.lista13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://coronavirus-19-api.herokuapp.com/countries/poland";
    private static final String TAG = MainActivity.class.getName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnAdd(View view) {
        Intent intent = new Intent(this,AddCountry.class);
        this.startActivity(intent);


    }



    public void btnShow(View view) {

       Intent intent = new Intent(this,MainActivity2.class);
        this.startActivity(intent);




    }

    public void btnStats(View view) {
        Intent intent = new Intent(this,Stats.class);
        this.startActivity(intent);
    }
}
