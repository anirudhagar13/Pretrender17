package com.app.code.pretrender;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
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
    String score ="";
    ProgressBar spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lol);
        damn = getIntent().getExtras();
        topic = damn.getString("take");
        dp = (DatePicker) findViewById(R.id.DP);
        btn = (Button) findViewById(R.id.BT2);
        spin = (ProgressBar) findViewById(R.id.PB);
        spin.setVisibility(View.GONE);

     btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin.setVisibility(View.VISIBLE);
                dat = ""+dp.getDayOfMonth()+"/"+""+dp.getMonth()+"/"+""+dp.getYear();
                new experiment().execute(topic+"~"+dat);
                /**************************************WORKS****************************************
                //*******************************UDP************************************************
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                            DatagramSocket clientSocket = new DatagramSocket();
                            InetAddress IPAddress = InetAddress.getByName("192.168.1.6");
                            byte[] sendData = new byte[1024];
                            byte[] sendData2 = new byte[1024];
                            byte[] receiveData = new byte[1024];
                            sendData = (topic+"~"+dat).getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9891);
                            clientSocket.send(sendPacket);
                            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            clientSocket.receive(receivePacket);
                            score = new String(receivePacket.getData());
                        }catch(Exception e)
                        {Toast.makeText(getApplicationContext(),"Not Able To Send", Toast.LENGTH_LONG).show();
                            android.util.Log.e("err","",e);}
                    }
                }
                ).start();
                //**************************************UDP*******************************

                Toast.makeText(getApplicationContext(),""+dat, Toast.LENGTH_LONG).show();
                while(score == "")
                {/***To TRAP UI THREAD***}

                Intent next = new Intent(lol.this, Next.class);
                Toast.makeText(getApplicationContext(),score, Toast.LENGTH_LONG).show();
                next.putExtra("give", score);
                startActivity(next);
                 ***************************************WORKS****************************************/
            }
        });
        }

    private class experiment extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... uri)
        {
            try{
                DatagramSocket clientSocket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("192.168.43.22");
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];
                sendData = (topic+"~"+dat).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9891);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                score = new String(receivePacket.getData());
            }catch(Exception e)
            {Toast.makeText(getApplicationContext(),"Not Able To Send", Toast.LENGTH_LONG).show();
                android.util.Log.e("err","",e);}
            return "Chodu";
        }
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Intent next = new Intent(lol.this, Next.class);
            next.putExtra("give", score);
            startActivity(next);
        }

    }


}
