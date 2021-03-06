package com.oc.liza.mynews.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oc.liza.mynews.R;
import com.oc.liza.mynews.models.News;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    // FOR DATA
    private List<News> news;
    private Context context;

    // CONSTRUCTOR
    public NewsAdapter(List<News> news) {
        this.news = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);

        return new NewsViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A NEWS ITEM
    @Override
    public void onBindViewHolder(NewsViewHolder viewHolder, int position) {
        viewHolder.updateWithNewsItem(this.news.get(position), context);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.news.size();
    }
}

