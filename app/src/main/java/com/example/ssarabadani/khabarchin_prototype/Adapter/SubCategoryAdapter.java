package com.example.ssarabadani.khabarchin_prototype.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssarabadani.khabarchin_prototype.Model.SubModel;
import com.example.ssarabadani.khabarchin_prototype.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.awt.font.TextAttribute;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 7/31/2015.
 */
public class SubCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnTouchListener {

    Context mContext;
    ArrayList<SubModel> subModel;
    Integer[] titleBackGround = new Integer[1000];
    private long first_tap, second_tap;
    private boolean flag = true, flag2 = true, flag3;
    private ImageView refrence;
    private int counter, firstView, secondView, mDoubleTap = 0;
    private float firstX, secondX, firstY, secondY;
    GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener());


    public class subCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView sub_title;
        TextView sub_abstract;
        ImageView news_image;
        ImageView agency_logo;
        TextView agency_name;
        TextView likeCounter;
        CardView sub_image;
        TextView sub_cat;
        TextView date_view;
        ImageView plusSign;

        public subCategoryViewHolder(View view) {
            super(view);

            sub_cat = (TextView) view.findViewById(R.id.sub_cat_agency_title);
            sub_image = (CardView) view.findViewById(R.id.relativeLayout);
            sub_title = (TextView) view.findViewById(R.id.sub_news_title);
            sub_abstract = (TextView) view.findViewById(R.id.sub_cat_abstract_text_view);
            news_image = (ImageView) view.findViewById(R.id.news_image);
            agency_logo = (ImageView) view.findViewById(R.id.agency_logo);
            agency_name = (TextView) view.findViewById(R.id.agency_name);
            likeCounter = (TextView) view.findViewById(R.id.like_counter);
            date_view = (TextView) view.findViewById(R.id.date_view);
            plusSign = (ImageView) view.findViewById(R.id.plus_sign);
            view.setOnTouchListener(SubCategoryAdapter.this);

        }
    }


    public SubCategoryAdapter(Context context, ArrayList<SubModel> subModel, Integer[] titleBackGround) {

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
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        final subCategoryViewHolder holder = (subCategoryViewHolder) viewHolder;

        refrence = holder.plusSign;

        String url = subModel.get(position).getNews_img_address();
        holder.itemView.setTag(position);

        if (subModel.get(position).getLike_counter() > 1000) {
            holder.likeCounter.setText(String.valueOf(subModel.get(position).getLike_counter() % 1000) + "+k");
        } else {
            holder.likeCounter.setText(String.valueOf(subModel.get(position).getLike_counter()));
        }

        Picasso.with(mContext)
                .load(subModel.get(position).getNews_img_address())
                .into(holder.news_image);
        holder.agency_logo.setImageResource(R.mipmap.khabar_chin);
        holder.agency_name.setText(subModel.get(position).getAgency_name());
        holder.sub_abstract.setText(subModel.get(position).getNews_abstract());
        holder.sub_title.setText(subModel.get(position).getSub_title());
        holder.date_view.setText(subModel.get(position).getDate());
        holder.plusSign.setVisibility(View.INVISIBLE);
//============================================================================================


        AsyncTask<String, Void, Bitmap> asyncTask = new AsyncTask<String, Void, Bitmap>() {

            Bitmap image;

            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    image = BitmapFactory.decodeStream(input);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return image;
            }

            @Override
            protected void onPostExecute(final Bitmap result) {
//                if (result != null) {
//                    Palette palette = Palette.generate(result);

                if (titleBackGround[position] != null) {
                    holder.sub_title.setBackgroundColor(titleBackGround[position]);
                } else {
                    Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            palette.generate(result);
                            int temp = palette.getLightMutedColor(R.color.accent_material_dark);
                            titleBackGround[position] = (temp);
                            holder.sub_title.setBackgroundColor(titleBackGround[position]);

                            if (((int) holder.itemView.getTag()) == position) {

                                holder.sub_title.setBackgroundColor(titleBackGround[position]);

                            }
                        }
                    };

                    if (result != null) {
                        listener.onGenerated(Palette.generate(result));
                    }
                }
            }
        };

        asyncTask.execute(url);

        //=========================================================
    }


    @Override
    public int getItemCount() {
        return subModel.size();
    }


    @Override
    public boolean onTouch(final View view, MotionEvent motionEvent) {



        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent motionEvent) {

                view.findViewById(R.id.plus_sign).setVisibility(View.VISIBLE);
                AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setDuration(1000);
                fadeOut.setFillAfter(true);
                view.findViewById(R.id.plus_sign).setAnimation(fadeOut);
                view.findViewById(R.id.plus_sign).setVisibility(View.INVISIBLE);
                Log.i("boomba", "taped");
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {

                view.findViewById(R.id.plus_sign).setVisibility(View.VISIBLE);
                AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setDuration(1000);
                fadeOut.setFillAfter(true);
                view.findViewById(R.id.plus_sign).setAnimation(fadeOut);
                view.findViewById(R.id.plus_sign).setVisibility(View.INVISIBLE);
                Log.i("boomba", "taped");
                return false;
            }
        });

//        firstX = motionEvent.getX();
//        firstY = motionEvent.getY();
//        first_tap = motionEvent.getDownTime();


//            if((first_tap - second_tap)  > 0 && (first_tap - second_tap) < 500 ) {
//
//                counter++;
//
//            }

//            if (flag) {
//
//                second_tap = first_tap;
//                flag = false;
//                secondY = firstY;
//                secondX = firstX;
//                mDoubleTap = 0;
//            } else if ((first_tap - second_tap) > 0 && (first_tap - second_tap) < 500 && firstX == secondX && firstY == secondY && mDoubleTap == 0) {
//                    view.findViewById(R.id.plus_sign).setVisibility(View.VISIBLE);
//                    AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
//                    fadeOut.setDuration(1000);
//                    fadeOut.setFillAfter(true);
//                    counter++;
//                    Log.i("tap", "taped");
//                    view.findViewById(R.id.plus_sign).setAnimation(fadeOut);
//                    view.findViewById(R.id.plus_sign).setVisibility(View.INVISIBLE);
////                Toast.makeText(mContext, "Double Taped!", Toast.LENGTH_SHORT).show();
//                    mDoubleTap = 1;
//                    flag = true;
//                } else {
//                    flag = true;
//                    mDoubleTap = 1;
//                }



        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }


}










