package com.app.code.pretrender;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Next extends ActionBarActivity {

    Bundle damnit;
    String score;
    TextView sc;
    TextView pred;
    float actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_next);
        //damnit = getIntent().getExtras();
        //score = damnit.getString("take");
        actual = (float)0.5;
        sc = (TextView)findViewById(R.id.TV4);
        pred = (TextView)findViewById(R.id.TV5);
        sc.setText("0.5");

        if(actual > 2.5)
            pred.setText("The Future Is BRIGHT :P");
        else if(actual < 2.5 && actual > 2.0)
            pred.setText("The Future is GOOD :)");
        else if(actual < 2.0 && actual > 1.5)
            pred.setText("The Future is OK :|");
        else if(actual < 1.5 && actual > 1.0)
            pred.setText("The Future is DULL :(");
        else
            pred.setText("The Future is DARK ??");
    }

}
