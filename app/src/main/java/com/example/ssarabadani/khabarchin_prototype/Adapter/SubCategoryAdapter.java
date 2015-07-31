package com.example.ssarabadani.khabarchin_prototype.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssarabadani.khabarchin_prototype.Model.SubModel;
import com.example.ssarabadani.khabarchin_prototype.R;
import com.squareup.picasso.Picasso;

import java.awt.font.TextAttribute;
import java.net.ContentHandler;
import java.util.ArrayList;

/**
 * Created by Admin on 7/31/2015.
 */
public class SubCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<SubModel> subModel;

    public static class subCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView sub_title;
        TextView sub_abstract;
        ImageView news_image;
        ImageView agency_logo;
        TextView agency_name;
        TextView likeCounter;


        public subCategoryViewHolder(View view) {
            super(view);

            sub_title = (TextView) view.findViewById(R.id.sub_news_title);
            sub_abstract = (TextView) view.findViewById(R.id.sub_cat_abstract_text_view);
            news_image = (ImageView) view.findViewById(R.id.news_image);
            agency_logo = (ImageView) view.findViewById(R.id.agency_logo);
            agency_name = (TextView) view.findViewById(R.id.agency_name);
            likeCounter = (TextView) view.findViewById(R.id.like_counter);

        }
    }


    public SubCategoryAdapter(Context context, ArrayList<SubModel> subModel) {

        mContext = context;
        this.subModel = subModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_card_view, parent, false);
        subCategoryViewHolder NVH = new subCategoryViewHolder(v);
        return NVH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        subCategoryViewHolder holder = (subCategoryViewHolder) viewHolder;

        holder.likeCounter.setText(String.valueOf(subModel.get(position).getLike_counter()));
        holder.agency_logo.setImageResource(R.mipmap.khabar_chin);
        holder.agency_name.setText(subModel.get(position).getAgency_name());
        holder.sub_abstract.setText(subModel.get(position).getNews_abstract());
        holder.sub_title.setText(subModel.get(position).getSub_title());
        Picasso.with(mContext).load(subModel.get(position).getNews_img_address()).into(holder.news_image);


    }

    @Override
    public int getItemCount() {
        return subModel.size();
    }
}
