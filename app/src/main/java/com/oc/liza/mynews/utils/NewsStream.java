package com.oc.liza.mynews.utils;

import com.oc.liza.mynews.models.NewsObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsStream {

    public static Observable<NewsObject> streamFetchNewslist(String url) {
        NewsService newsService = NewsService.retrofit.create(NewsService.class);
        return newsService.getNews(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }
}
