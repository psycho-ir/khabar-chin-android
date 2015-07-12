package sarabadani.com.khabarchin.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import sarabadani.com.khabarchin.R;
import sarabadani.com.khabarchin.model.News;

/**
 * Created by SOROOSH on 7/12/15.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    static class NewsHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }

    Context context;
    int layoutResourceId;
    List<News> newsItems = null;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.newsItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        NewsHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new NewsHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.image);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        } else {
            holder = (NewsHolder) row.getTag();
        }

        News news = newsItems.get(position);
        Log.i("INFO", "News: " + news);
        Log.i("INFO", "Holder: " + holder);
        holder.txtTitle.setText(news.getTitle());
        Picasso.with(context).load(news.getImgAddress()).into(holder.imgIcon);
        return row;
    }
}
