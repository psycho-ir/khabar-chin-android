package com.example.ssarabadani.khabarchin_prototype.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssarabadani.khabarchin_prototype.Model.NewsCategoryModel;
import com.example.ssarabadani.khabarchin_prototype.R;

import java.util.ArrayList;

/**
 * Created by s.sarabadani on 7/14/2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<NewsCategoryModel> dataModels;

    public static class newsViewHolder extends RecyclerView.ViewHolder  {

        TextView cardText;
        ImageView cardImage;
//        CardView cv;

        public newsViewHolder(View view) {
            super(view);

//            cv = (CardView) view.findViewById(R.id.card_view);
            cardImage = (ImageView) view.findViewById(R.id.image_card);
            cardText = (TextView) view.findViewById(R.id.text_card);

//            view.setOnClickListener(this);

        }

//        @Override
//        public void onClick(View view) {
//
//            Toast.makeText(view.getContext(), "this item was clicked " + view.getId() , Toast.LENGTH_SHORT).show();
//
//        }
    }


    public CategoryAdapter(Context context, ArrayList<NewsCategoryModel> dataModels) {

        mContext = context;
        this.dataModels = dataModels;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        newsViewHolder NVH = new newsViewHolder(v);
        return NVH;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        newsViewHolder holder = (newsViewHolder) viewHolder;
        holder.cardText.setText(dataModels.get(position).getTitle());
        holder.cardImage.setImageResource(R.mipmap.khabar_chin);
//        holder.cardImage.setImageResource(dataModels.get(position).image);


    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }


}
