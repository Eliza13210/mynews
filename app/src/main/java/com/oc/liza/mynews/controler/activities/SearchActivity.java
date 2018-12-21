package com.oc.liza.mynews.controler.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.oc.liza.mynews.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_button)
    Button searchButton;
    @BindView(R.id.query)
    EditText search_query;
    @BindView(R.id.begin_date)
    EditText search_begin_date;
    @BindView(R.id.end_date)
    EditText search_end_date;

    Context ctx = this;
    String query;
    String begin_date = "";
    String end_date = "";

    String url = ctx.getResources().getString(R.string.article_search);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initLayout();
    }

    private void initLayout() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        search_query.setSelection(0);
        Objects.requireNonNull(this.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = search_query.getText().toString();
                begin_date = search_begin_date.getText().toString();
                end_date = search_end_date.getText().toString();
                setUrl();
            }
        });

    }

    public void setUrl() {

        url += "&amp;q=" + query;

        if (!begin_date.isEmpty()) {
            url += "&amp;begin_date" + begin_date;
        } else if (end_date.isEmpty()) {
            url += "&amp;end_date" + end_date;
        }
    }

    public void getDate() {
    }
}
