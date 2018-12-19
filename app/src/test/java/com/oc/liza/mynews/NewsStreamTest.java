package com.oc.liza.mynews;

import android.os.Bundle;

import com.oc.liza.mynews.controler.activities.MainActivity;
import com.oc.liza.mynews.controler.fragments.TabOne;
import com.oc.liza.mynews.models.News;
import com.oc.liza.mynews.models.NewsImage;
import com.oc.liza.mynews.models.NewsObject;
import com.oc.liza.mynews.utils.NewsStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.oc.liza.mynews.utils.NewsStream.streamFetchNewslist;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)

public class NewsStreamTest {

    @Mock
    MainActivity main;
    TabOne tabOne;
    Bundle bundle;


    //Override the default AndroidSchedulers.mainThread() Scheduler since it can't be accessed from test

    @Before
    public void setUp() {
        main = new MainActivity();
        tabOne = new TabOne();
        bundle = new Bundle();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) {
                return Schedulers.trampoline();
            }
        });
    }

    @AfterClass
    public static void tearDownClass() {

        // Not strictly necessary because we can't reset the value set by setInitMainThreadSchedulerHandler,
        // but it doesn't hurt to clean up anyway.
        RxAndroidPlugins.reset();
    }


    @Test
    public void TestSubscribeObserverToObservable() {

        //1 - Get the stream
        Observable<NewsObject> observable = streamFetchNewslist("http://");
        //2 - Create a new TestObserver
        TestObserver<NewsObject> testObserver = new TestObserver<>();
        //3 - Launch observable
        observable.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

    }


    @Test
    public void FetchStreamTopStories() {

        final ArrayList<NewsObject> newsList = new ArrayList<>();

        //- Execute the stream subscribing to Observable defined inside NewsStream
        NewsStream.streamFetchNewslist("https://api.nytimes.com/svc/topstories/v2/home.json?&api-key=799e9f0e6e264b3a8e21b57f3f05dfd0")
                .subscribeWith(new TestObserver<NewsObject>() {
            @Override
            public void onNext(NewsObject news) {
                // - Update UI with list of news
                newsList.add(news);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Test
    public void FetchStreamMostPopular() {

    }

    @Test
    public void FetchStreamScience() {

    }

    @Test
    public void FetchStreamHealth() {

    }

    @Test
    public void FetchStreamMovies() {

    }
}