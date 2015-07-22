package com.example.ssarabadani.khabarchin_prototype;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsActivity extends Fragment {

    private ArrayList<NewsCategory> newsCategory;

    public DetailsActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        newsCategory = new ArrayList<>();


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String Url = "http://khabar-chin.com/rest/important/?categories=social&size=6";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++) {

                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        newsCategory.add(new NewsCategory(jsonObject.getString("id"), jsonObject.getString("title"), Integer.parseInt(jsonObject.getString("img_address"))));
//                        adapter.notifyDatasSetChanged();
                    }catch(JSONException e) {

                        e.printStackTrace();

                    }

                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("nothing");
                    }
                });


        return v;
    }


}
