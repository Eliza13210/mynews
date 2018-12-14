package com.oc.liza.mynews.controler.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.liza.mynews.R;
import com.oc.liza.mynews.models.News;
import com.oc.liza.mynews.models.NewsObject;
import com.oc.liza.mynews.utils.NewsStream;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class TabOne extends Fragment {


    //FOR DATA
    private Disposable disposable;
    @BindView(R.id.textView)
    TextView fragmentText;

    public TabOne() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_one, container, false);
        ButterKnife.bind(this, view);
        this.executeHttpRequestWithRetrofit();
        return view;
    }

    // 1 - Execute our Stream
    private void executeHttpRequestWithRetrofit() {
        // 1.1 - Update UI
        this.updateUIWhenStartingHTTPRequest();
        // 1.2 - Execute the stream subscribing to Observable defined inside NewsStream
        this.disposable = NewsStream.streamFetchNewslist(this.getResources().getString(R.string.topstories)).subscribeWith(new DisposableObserver<NewsObject>() {
            @Override
            public void onNext(NewsObject news) {
                Log.e("TAG", "On Next");
                // 1.3 - Update UI with list of news
                updateUIWithListOfNews(news);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
                fragmentText.setText("error" + e);
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUIWhenStartingHTTPRequest() {
        this.fragmentText.setText("Downloading...");
    }

    private void updateUIWhenStoppingHTTPRequest(String response) {
        this.fragmentText.setText(response);
    }

    private void updateUIWithListOfNews(NewsObject news) {
        ArrayList<News> list = news.getResults();
        String i = list.get(0).getTitle();
        updateUIWhenStoppingHTTPRequest(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }
}
