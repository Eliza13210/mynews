package com.oc.liza.mynews.controler.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oc.liza.mynews.R;
import com.oc.liza.mynews.models.News;
import com.oc.liza.mynews.models.NewsObject;
import com.oc.liza.mynews.utils.NewsStream;
import com.oc.liza.mynews.view.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static java.util.Collections.addAll;

public class TabOne extends Fragment {
    // FOR DESIGN
    @BindView(R.id.fragment_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    //FOR DATA
    private Disposable disposable;
    // 2 - Declare list of news (News) & Adapter
    private List<News> newsList;
    private NewsAdapter adapter;

    public TabOne() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_one, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();
        this.executeHttpRequestWithRetrofit();
        return view;
    }


    //  Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // 3.1 - Reset list
        this.newsList = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new NewsAdapter(this.newsList);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    }


    private void updateUIWithListOfNews(NewsObject news) {
        newsList.addAll(news.getResults());

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }
}
