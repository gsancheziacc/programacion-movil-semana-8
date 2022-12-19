package com.example.gabriel_sanchez_s8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gabriel_sanchez_s8.models.News;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private static LayoutInflater inflater =null;
    Context context;
    ArrayList<News> newsArrayList;
    public ListAdapter(Context context, ArrayList<News> newsArrayList){
        this.context = context;
        this.newsArrayList = newsArrayList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        @SuppressLint({"ViewHolder", "InflateParams"}) final View view = inflater.inflate(R.layout.news_list_item, null);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvDescription = view.findViewById(R.id.tvDescription);

        tvTitle.setText(newsArrayList.get(i).getTitle());
        tvDescription.setText(newsArrayList.get(i).getDescription());
        return view;
    }
    @Override
    public int getCount() {
        return newsArrayList.size();
    }
    @Override
    public News getItem(int i) {
        return newsArrayList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return newsArrayList.get(i).getId();
    }
}
