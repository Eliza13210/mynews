package com.oc.liza.mynews.controler.activities;


import android.text.Editable;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)



public class SearchActivityTest {

    SearchActivity searchActivity=new SearchActivity();
    @Mock
    EditText textEdit;

    @Before


    @Test
    public void setSearchUrl_WithSearchQueryAndDate_ReturnStringWithQuery(){
        String url="";

        searchActivity.setUrl();
    }

}