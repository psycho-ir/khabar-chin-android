package com.example.ssarabadani.khabarchin_prototype.Fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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
import java.util.List;


/**
 * CREATED BY SAJAD
 */
public class SubCategoryFragment extends Fragment {

    private ArrayList<SubModel> subModels;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private SubCategoryAdapter subCategoryAdapter;
    private int param;
    private int currentPage;





    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;


    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_sub_category, container, false);
        subModels = new ArrayList<>();

//        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
//        final RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.progress_back_layout);
        Bundle bundle = this.getArguments();
        final String categoryName = bundle.getString("category");

        recyclerView = (RecyclerView) v.findViewById(R.id.sub_category_fragment_recycler);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        subCategoryAdapter = new SubCategoryAdapter(getActivity(), subModels);


        param = 5;
        currentPage = 1;



        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = llm.getItemCount();
                firstVisibleItem = llm.findFirstVisibleItemPosition();

//                progressBar.setVisibility(View.GONE);
//                relativeLayout.setVisibility(View.GONE);
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;

                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached
//                    progressBar.setVisibility(View.VISIBLE);
//                    relativeLayout.setVisibility(View.VISIBLE);


                    currentPage++;
                    // Do something
                    volleyRequestMaker(categoryName, currentPage, param);
                    subCategoryAdapter.notifyDataSetChanged();
                    Log.i("woooooooooooooow", String.valueOf(param) + " items added");
                    loading = true;

                }
            }

        });

        volleyRequestMaker(categoryName,currentPage, param);


        recyclerView.setAdapter(subCategoryAdapter);


        return v;
    }


    private ArrayList<SubModel> volleyRequestMaker(String categoryName,int currentPage, int size) {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String Url = "http://khabar-chin.com/rest/important/?categories=" + categoryName + "&page_number="+ String.valueOf(currentPage) + "&size=" + String.valueOf(size);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                subModels.add(new SubModel(jsonObject.getString("title"), jsonObject.getString("agency"), jsonObject.getString("abstract"), jsonObject.getInt("likes"),
                                        jsonObject.getString("img_address"), R.mipmap.khabar_chin, jsonObject.getString("date")));
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

        return subModels;

    }


}
