package com.example.tb_laota.BestDeals.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.tb_laota.BestDeals.Item;
import com.example.tb_laota.BestDeals.R;
import com.example.tb_laota.BestDeals.app.AppController;

import java.util.List;

public class Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    ImageLoader imageLoader = AppController.getApplication().getmImageLoader();

    public Adapter(Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_layout, null);
        }
        if (imageLoader == null) {
            imageLoader = AppController.getApplication().getmImageLoader();
        }

        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.image_view);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView rate = (TextView) convertView.findViewById(R.id.tv_rate);
        TextView description = (TextView) convertView.findViewById(R.id.tv_genre);
        TextView createdAt = (TextView) convertView.findViewById(R.id.tv_year);

        //getting data for row
        Item item = items.get(position);
        imageView.setImageUrl(item.getImage(), imageLoader);

        //title
        title.setText(item.getTitle());

        //rate
        rate.setText(String.valueOf(item.getRate()));

        //description
        description.setText(item.getDescription());

        //year
        createdAt.setText(String.valueOf(item.getCreatedAt()));

        return convertView;
    }
}
