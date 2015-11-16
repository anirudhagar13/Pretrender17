package com.app.code.pretrender;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


public class InfoActivity extends ActionBarActivity {


    Button btn;
    AutoCompleteTextView auto;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info);
        auto = (AutoCompleteTextView) findViewById(R.id.TB1);
        btn=(Button)findViewById(R.id.BT1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = auto.getText().toString();
                Intent next = new Intent(InfoActivity.this,lol.class);
                next.putExtra("take",str);
                startActivity(next);
            }
        });
    }

    }

