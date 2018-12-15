package com.oc.liza.mynews.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oc.liza.mynews.R;
import com.oc.liza.mynews.models.News;
import com.oc.liza.mynews.models.NewsImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_item_title)
    TextView title;
    @BindView(R.id.fragment_date)
    TextView date;
    @BindView(R.id.fragment_section)
    TextView section;
    @BindView(R.id.thumbnail)
    ImageView thumbnail;

    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNewsItem(News newsItem, Context context) {
        this.title.setText(newsItem.getTitle());
        this.date.setText(newsItem.getPublished_date());
        this.section.setText(newsItem.getSection() + " > " + newsItem.getSubsection());
        List<NewsImage> images=new ArrayList<>();
        images.addAll(newsItem.getMultimedia());
        String url=images.get(0).getUrl();
        Glide.with(context)
                .load(url)
                .into(thumbnail);
    }
}
