package com.example.ssarabadani.khabarchin_prototype.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ssarabadani.khabarchin_prototype.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by s.sarabadani on 8/31/2015.
 */
public class Splash extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final ViewGroup splash = (ViewGroup) findViewById(R.id.splash);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET, "http://www.google.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(Splash.this, MainActivity.class);
                intent.putExtra("state", true);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Snackbar.make(splash, "متاسفانه یا اینترنت نداری یا خیلی کنده!", Snackbar.LENGTH_SHORT).show();
                finish();
            }

        });

        queue.add(jsonArrayRequest);

    }
}
