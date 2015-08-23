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

        public newsViewHolder(View view) {
            super(view);

            cardImage = (ImageView) view.findViewById(R.id.image_card);
            cardText = (TextView) view.findViewById(R.id.text_card);


        }

    }


    public static class newsViewHolderBanner extends RecyclerView.ViewHolder {


        public newsViewHolderBanner(View itemView) {
            super(itemView);
        }
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
        int pos = R.mipmap.khabar_chin;
        switch(position){

            case 0:
                pos = R.mipmap.news_1;
                break;
            case 1:
                pos = R.mipmap.news_2;
                break;
            case 2:
                pos = R.mipmap.news_3;
                break;
            case 3:
                pos = R.mipmap.news_4;
                break;
            case 4:
                pos = R.mipmap.news_5;
                break;
            case 5:
                pos = R.mipmap.news_6;
                break;
            case 6:
                pos = R.mipmap.news_7;
                break;
            case 7:
                pos = R.mipmap.news_8;
                break;
            case 8:
                pos = R.mipmap.news_9;
                break;
            case 9:
                pos = R.mipmap.news_10;
                break;
            case 10:
                pos = R.mipmap.news_11;
                break;
            case 11:
                pos = R.mipmap.news_12;
                break;
            case 12:
                pos = R.mipmap.news_13;
                break;

        }
        holder.cardImage.setImageResource(pos);
//        holder.cardImage.setImageResource(dataModels.get(position).image);


    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }


}
