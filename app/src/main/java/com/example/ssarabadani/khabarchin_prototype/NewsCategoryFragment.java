package com.example.ssarabadani.khabarchin_prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NewsCategory> newsCategory;
    private Adapter adapter;

    private RequestQueue queue;


    public NewsCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view = inflater.inflate(R.layout.fragment_news_category, container, false);
        LinearLayoutManager llm = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

//        for (int i = 0; i < 10; i++) {
//
//            dataModels.add(new DataModel(" ??? ????? " + i, R.mipmap.khabar_chin));
//
//        }

        newsCategory = new ArrayList<>();
        adapter = new Adapter(getActivity(), newsCategory);
        getNewsJson();
        recyclerView.setAdapter(adapter);

        return view;
    }


    private ArrayList<NewsCategory> getNewsJson() {

        queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET,
                "http://khabar-chin.com/rest/all_categories/",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                newsCategory.add(new NewsCategory(jsonObject.getString("pk"), jsonObject.getJSONObject("fields").getString("local_name"), R.mipmap.khabar_chin));
                            } catch (JSONException e) {

                                e.printStackTrace();

                            }
                            adapter.notifyDataSetChanged();

                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        queue.add(objectRequest);
        return newsCategory;
    }


}


