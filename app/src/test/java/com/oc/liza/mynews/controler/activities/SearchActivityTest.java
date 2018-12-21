package com.oc.liza.mynews.controler.activities;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.widget.EditText;

import com.oc.liza.mynews.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SearchActivityTest {

    @Mock
    private Context mockContext;
    @Mock
    private Resources mockContextResources;

    @Before
    public void setupTests() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // During unit testing sometimes test fails because of your methods
        // are using the app Context to retrieve resources, but during unit test the Context is null
        // so we can mock it.

        when(mockContext.getResources()).thenReturn(mockContextResources);
        when(mockContextResources.getString(R.string.article_search)).thenReturn("https://api.nytimes.com/svc/search/v2/articlesearch.json?&amp;api-key=799e9f0e6e264b3a8e21b57f3f05dfd0");

    }

    @Test
    public void setSearchUrl_WithSearchQueryAndDate_ReturnStringWithQuery(){
        SearchActivity searchActivity=Mockito.spy(SearchActivity.class);
        searchActivity.ctx=mockContext;
        searchActivity.query="Electric cars";
        searchActivity.begin_date="20000101";

        searchActivity.setUrl();
        String url=searchActivity.url;
        assertEquals("https://api.nytimes.com/svc/search/v2/articlesearch.json?&api-key=799e9f0e6e264b3a8e21b57f3f05dfd0&q=electric%20cars&begin_date=20000101", url);


    }

}