package com.example.ssarabadani.khabarchin_prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<DataModel> dataModels = new ArrayList<>();


    public NewsCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view = inflater.inflate(R.layout.fragment_news_category, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());


        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        for (int i = 0; i < 10; i++) {

            dataModels.add(new DataModel(" ??? ????? " + i, R.mipmap.khabar_chin));

        }


        Adapter adapter = new Adapter(getActivity(), dataModels);
        recyclerView.setAdapter(adapter);

        return view;
    }


}
