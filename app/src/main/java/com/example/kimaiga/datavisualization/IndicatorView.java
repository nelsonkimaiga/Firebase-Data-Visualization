package com.example.kimaiga.datavisualization;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

//import android.support.v7.widget.

public class IndicatorView extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_indicator_view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                findViewById(R.id.avloadingIndicatorView).setVisibility(View.VISIBLE);
                Intent data = new Intent(IndicatorView.this,MainActivity.class);
                startActivity(data);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}


