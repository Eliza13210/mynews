package com.oc.liza.mynews.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oc.liza.mynews.R;
import com.oc.liza.mynews.models.News;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_item_title)
    TextView title;
    @BindView(R.id.fragment_date)
    TextView date;
    @BindView(R.id.fragment_section)
    TextView section;

    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNewsItem(News newsItem) {
        this.title.setText(newsItem.getTitle());
        this.date.setText(newsItem.getPublished_date().toString());
        this.section.setText(newsItem.getSection() + " > " + newsItem.getSubsection());
    }
}
