package com.example.ssarabadani.khabarchin_prototype.Fragments;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ssarabadani.khabarchin_prototype.Adapter.SubCategoryAdapter;
import com.example.ssarabadani.khabarchin_prototype.Constants.Constants;
import com.example.ssarabadani.khabarchin_prototype.Model.SubModel;
import com.example.ssarabadani.khabarchin_prototype.R;
import com.example.ssarabadani.khabarchin_prototype.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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
    private FloatingActionButton FAB;
    private WebDetailView webViewFrag = new WebDetailView();

    private TextView page_name;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount, lastItemVisible, heightCounter = 0;
    private FragmentManager fragmentManager;
    boolean state = true;


    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_sub_category, container, false);
        subModels = new ArrayList<>();

        Bundle bundle = this.getArguments();
        final String categoryName = bundle.getString("category");
        recyclerView = (RecyclerView) v.findViewById(R.id.sub_category_fragment_recycler);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        subCategoryAdapter = new SubCategoryAdapter(getActivity(), subModels, bundle.getString("cat_name"));

        page_name = (TextView) getActivity().findViewById(R.id.page_name);
        page_name.setText(bundle.getString("cat_name"));


        recyclerView.setAdapter(subCategoryAdapter);


        FAB = (FloatingActionButton) v.findViewById(R.id.fab);
        FAB.setScaleX(0);
        FAB.setScaleY(0);
        FAB.setVisibility(View.GONE);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                llm.smoothScrollToPosition(recyclerView, null, 0);

            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                fragmentManager = getFragmentManager();

                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.DETAIL_URL + subModels.get((int) view.getTag()).getId());
                webViewFrag.setArguments(bundle);
                Log.i("url", Constants.DETAIL_URL + subModels.get((int) view.getTag()).getId());
                fragmentManager.beginTransaction().replace(R.id.main_frame, webViewFrag, "detail").addToBackStack("detail").commit();

            }
        }));


        param = 5;
        currentPage = 1;


        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }


            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = llm.getItemCount();
                firstVisibleItem = llm.findFirstVisibleItemPosition();
                lastItemVisible = llm.findLastVisibleItemPosition();
                llm.setSmoothScrollbarEnabled(true);
                Log.i("woooooooooooooow", String.valueOf(dy) + " items added");


                subCategoryAdapter.setOnScrollListener(new SubCategoryAdapter.onScrollListener() {
                    @Override
                    public boolean fastScrolled() {
                        if (dy < 70 && dy > 0) {

                            return true;
                        } else {

                            return false;

                        }
                    }
                });

                heightCounter += dy;
                state = true;

                if (heightCounter <= 909) {
                    FAB.setVisibility(View.GONE);
                    FAB.setScaleX(0);
                    FAB.setScaleY(0);


                } else if (heightCounter < 1000 && heightCounter > 909) {
                    FAB.setVisibility(View.VISIBLE);
                    String value = "0." + "" + heightCounter % 900;
                    Log.i("value", value);
                    FAB.setScaleX(Float.valueOf(value));
                    FAB.setScaleY(Float.valueOf(value));
                } else {
                    FAB.setVisibility(View.VISIBLE);
                    FAB.setScaleX(1);
                    FAB.setScaleY(1);

                }


                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;

                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    currentPage++;
                    // Do something
                    volleyRequestMaker(categoryName, currentPage, param);
                    subCategoryAdapter.notifyDataSetChanged();
                    loading = true;

                }
            }

        });

        volleyRequestMaker(categoryName, currentPage, param);


        return v;
    }


    private ArrayList<SubModel> volleyRequestMaker(String categoryName, int currentPage, int size) {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String Url = Constants.IMPORTANT_NEWS_URL + "?categories=" + categoryName + "&page_number=" + String.valueOf(currentPage) + "&size=" + String.valueOf(size);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                subModels.add(new SubModel(jsonObject.getString("title"), jsonObject.getString("agency"), jsonObject.getString("abstract"), jsonObject.getInt("likes"),
                                        jsonObject.getString("img_address"), R.mipmap.khabar_chin, jsonObject.getString("date"), jsonObject.getInt("id")));
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



