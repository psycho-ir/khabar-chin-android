package com.example.ssarabadani.khabarchin_prototype.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ssarabadani.khabarchin_prototype.Adapter.CategoryAdapter;
import com.example.ssarabadani.khabarchin_prototype.Model.NewsCategoryModel;
import com.example.ssarabadani.khabarchin_prototype.R;
import com.example.ssarabadani.khabarchin_prototype.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NewsCategoryModel> newsCategory;
    private CategoryAdapter adapter;


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
        final FragmentManager fragmentManager;
        fragmentManager = getFragmentManager();
        final SubCategoryFragment det_fragment = new SubCategoryFragment();

        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

//        for (int i = 0; i < 10; i++) {
//
//            dataModels.add(new DataModel(" ??? ????? " + i, R.mipmap.khabar_chin));
//
//        }

        newsCategory = new ArrayList<>();
        adapter = new CategoryAdapter(getActivity(), newsCategory);
        getNewsJson();
        System.out.println(adapter.getItemCount());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("category", newsCategory.get(position).getPk());
                bundle.putString("cat_name", newsCategory.get(position).getTitle());
                det_fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.main_frame, det_fragment, "sub_category").addToBackStack("sub_category_news").commit();
                Toast.makeText(getActivity(), "item selected: " + newsCategory.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));

        return view;
    }


    private ArrayList<NewsCategoryModel> getNewsJson() {

        queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET,
                "http://khabar-chin.com/rest/all_categories/",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String field =jsonObject.getJSONObject("fields").getString("local_name");
                                String pk =  jsonObject.getString("pk");
                                Log.i("INFO", "PK: "+ pk  + " Field: " + field);
                                newsCategory.add(new NewsCategoryModel(pk, field , R.mipmap.khabar_chin));
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


