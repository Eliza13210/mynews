package com.oc.liza.mynews;

import com.oc.liza.mynews.models.NewsObject;
import com.oc.liza.mynews.utils.NewsStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 */


public class ExampleUnitTest {

    //Override the default AndroidSchedulers.mainThread() Scheduler since it can't be accessed from test

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override public Scheduler apply(Callable<Scheduler> schedulerCallable) {
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
    public void FetchStreamTopStories() throws Exception {

        //1 - Get the stream
        Observable<NewsObject> observable = NewsStream.streamFetchNewslist("https://api.nytimes.com/svc/topstories/v2/home.json?&amp;api-key=799e9f0e6e264b3a8e21b57f3f05dfd0");
        //2 - Create a new TestObserver
        TestObserver<NewsObject> testObserver = new TestObserver<>();
        //3 - Launch observable
        observable.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

    }
    @Test
    public void FetchStreamMostPopular() {

        //1 - Get the stream
        Observable<NewsObject> observable = NewsStream.streamFetchNewslist("https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?&amp;api-key=799e9f0e6e264b3a8e21b57f3f05dfd0");
        //2 - Create a new TestObserver
        TestObserver<NewsObject> testObserver = new TestObserver<>();
        //3 - Launch observable
        observable.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

    }
}