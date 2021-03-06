package com.oc.liza.mynews.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oc.liza.mynews.R;
import com.oc.liza.mynews.models.News;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_item_title)
    TextView title;
    @BindView(R.id.fragment_date)
    TextView date;
    @BindView(R.id.fragment_section)
    TextView section;
    @BindView(R.id.thumbnail)
    ImageView thumbnail;
    @Nullable
    @BindView(R.id.webView)
    WebView webView;


    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNewsItem(final News newsItem, final Context context) {
        this.title.setText(newsItem.getTitle());
        this.date.setText(newsItem.getPublished_date());
        this.section.setText(newsItem.toString());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView = new WebView(context);
                webView.loadUrl(newsItem.getUrl());
            }
        });
        try {
            String url = newsItem.getImageUrl();
            Glide.with(context)
                    .load(url)
                    .into(thumbnail);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("exception", "error " + e);
        }
    }


}
