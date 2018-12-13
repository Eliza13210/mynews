package com.oc.liza.mynews;

import org.junit.Test;


import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void FetchStreamTopStories() throws Exception {
        //1 - Get the stream
        Observable<NewsObject> observableUsers = NewsStream.streamFetchNews("https://api.nytimes.com/svc/topstories/v2/home.json?&amp;api-key=799e9f0e6e264b3a8e21b57f3f05dfd0");
        //2 - Create a new TestObserver
        TestObserver<NewsObject> testObserver = new TestObserver<>();
        //3 - Launch observable
        observableUsers.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

        // 4 - Get list of user fetched
        NewsObject newsFetched = testObserver.getStatus();

        // 5 - Verify if Jake Wharton follows only 12 users...
        assertThat("Ok",newsFetched.getStatus() == "Ok");
    }
}