package com.app.code.pretrender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;


public class SplashScreen extends ActionBarActivity {

    private static int SPLASH_TIME_OUT = 4000;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        spinner = (ProgressBar)findViewById(R.id.PB1);
        spinner.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,InfoActivity.class);
                startActivity(i);
                spinner.setVisibility(View.GONE);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
