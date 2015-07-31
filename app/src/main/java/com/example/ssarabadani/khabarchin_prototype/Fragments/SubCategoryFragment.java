package com.example.ssarabadani.khabarchin_prototype.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ssarabadani.khabarchin_prototype.Adapter.SubCategoryAdapter;
import com.example.ssarabadani.khabarchin_prototype.Model.NewsCategoryModel;
import com.example.ssarabadani.khabarchin_prototype.Model.SubModel;
import com.example.ssarabadani.khabarchin_prototype.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {

    private ArrayList<SubModel> subModels;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private SubCategoryAdapter subCategoryAdapter;

    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sub_category, container, false);
        subModels = new ArrayList<>();

        Bundle bundle = this.getArguments();
        String categoryName = bundle.getString("category");

        recyclerView = (RecyclerView) v.findViewById(R.id.sub_category_fragment_recycler);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        subCategoryAdapter = new SubCategoryAdapter(getActivity(), subModels);


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String Url = "http://khabar-chin.com/rest/important/?categories="+categoryName+"&size=6";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        subModels.add(new SubModel(jsonObject.getString("title"), jsonObject.getString("agency"), jsonObject.getString("abstract"), jsonObject.getInt("likes"),
                                jsonObject.getString("img_address"), R.mipmap.khabar_chin));
                    } catch (JSONException e) {

                        e.printStackTrace();

                    }
                    subCategoryAdapter.notifyDataSetChanged();

                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("nothing");
                    }

                });
        queue.add(jsonArrayRequest);
        recyclerView.setAdapter(subCategoryAdapter);


        return v;
    }


}
