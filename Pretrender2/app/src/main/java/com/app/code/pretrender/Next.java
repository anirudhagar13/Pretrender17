package com.app.code.pretrender;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Next extends ActionBarActivity {

    Bundle damnit;
    String score;
    TextView sc;
    TextView pred;
    Float actual;
    ImageView img;
    String uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_next);

        Toast.makeText(getApplicationContext(), "PredicTion Made !!", Toast.LENGTH_LONG).show();
        img = (ImageView)findViewById(R.id.IMG);
        damnit = getIntent().getExtras();
        score = damnit.getString("give");
        actual = Float.parseFloat(score);
        sc = (TextView)findViewById(R.id.TV4);
        pred = (TextView)findViewById(R.id.TV5);
        sc.setText(score);

        if(actual > 2.5){
            pred.setText("The Future Is BRIGHT :P");
            uri = "@drawable/vgood";}
        else if(actual < 2.5 && actual > 2.0){
            pred.setText("The Future is GOOD :)");
            uri = "@drawable/good";}
        else if(actual < 2.0 && actual > 1.5){
            pred.setText("The Future is OK :|");
            uri = "@drawable/ok";}
        else if(actual < 1.5 && actual > 1.0){
            pred.setText("The Future is DULL :(");
            uri = "@drawable/bad";}
        else{
            pred.setText("The Future is DARK ??");
            uri = "@drawable/vbad";}

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);
    }

}
