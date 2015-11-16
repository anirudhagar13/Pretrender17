package com.app.code.pretrender;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.logging.Handler;

public class lol extends ActionBarActivity {

    Button btn;
    Bundle damn;
    String dat;
    DatePicker dp;
    String topic;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lol);
        damn = getIntent().getExtras();
        topic = damn.getString("take");
        dp = (DatePicker)findViewById(R.id.DP);
        dat = ""+dp.getDayOfMonth()+"/"+""+dp.getMonth()+"/"+""+dp.getYear();

        btn=(Button)findViewById(R.id.BT2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //**********UDP************************************
                /*try {
                    DatagramSocket clientSocket = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName("192.168.43.22");
                    byte[] sendData = new byte[1024];
                    byte[] receiveData = new byte[1024];
                    sendData = topic.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9895);
                    Toast.makeText(getApplicationContext(),"Step 4", Toast.LENGTH_LONG).show();
                    clientSocket.send(sendPacket);
                    Toast.makeText(getApplicationContext(),"Step 5", Toast.LENGTH_LONG).show();
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    Toast.makeText(getApplicationContext(),"Step 6", Toast.LENGTH_LONG).show();
                    clientSocket.receive(receivePacket);
                    String modifiedSentence = new String(receivePacket.getData());
                    //System.out.println("FROM SERVER:" + modifiedSentence);
                    Toast.makeText(getApplicationContext(),modifiedSentence, Toast.LENGTH_LONG).show();
                    clientSocket.close();
                }catch(Exception e)
                {}*/
                //**************************************************
                Toast.makeText(getApplicationContext(),""+dat, Toast.LENGTH_LONG).show();
                Intent next = new Intent(lol.this, Next.class);
                next.putExtra("give", score);
                startActivity(next);
            }
        });
    }
}
