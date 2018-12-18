package com.oc.liza.mynews.controler.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


public class TabOne extends Fragment {
    // FOR DESIGN
    @BindView(R.id.fragment_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView


    //FOR DATA
    private Disposable disposable;
    // 2 - Declare list of news (News) & Adapter
    private List<News> newsList;
    private NewsAdapter adapter;
    private int position;
    private String url;

    public static TabOne newInstance(int position) {
        TabOne tabOne = new TabOne();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        tabOne.setArguments(bundle);
        return tabOne;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position", 0);
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
        // 3.3 - Attach the adapter to the recycler view to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // 1 - Execute our Stream
    private void executeHttpRequestWithRetrofit() {

        switch (position) {
            case 0:
                url = this.getResources().getString(R.string.topstories);
                break;
            case 1:
                url = this.getResources().getString(R.string.mostpopular);
                break;
        }

        //- Execute the stream subscribing to Observable defined inside NewsStream
        this.disposable = NewsStream.streamFetchNewslist(url).subscribeWith(new DisposableObserver<NewsObject>() {
            @Override
            public void onNext(NewsObject news) {
                // - Update UI with list of news
                updateUIWithListOfNews(news);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

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
