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

     btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dat = ""+dp.getDayOfMonth()+"/"+""+dp.getMonth()+"/"+""+dp.getYear();
                spin = (ProgressBar) findViewById(R.id.PB);
                spin.setVisibility(View.INVISIBLE);
                /*******************************TCP************************************************
                try{
                    //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                    Toast.makeText(getApplicationContext(),"Step 1", Toast.LENGTH_LONG).show();
                    Socket clientSocket = new Socket("192.168.43.22", 6789);
                    Toast.makeText(getApplicationContext(),"Step 2", Toast.LENGTH_LONG).show();
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    Toast.makeText(getApplicationContext(),"Step 3", Toast.LENGTH_LONG).show();
                    //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    //String sentence = inFromUser.readLine();
                    Toast.makeText(getApplicationContext(),"Crucial", Toast.LENGTH_LONG).show();
                    outToServer.writeBytes(dat + '\n');
                    Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_LONG).show();
                    //String modifiedSentence = inFromServer.readLine();
                    //System.out.println("FROM SERVER: " + modifiedSentence);
                    clientSocket.close();}catch(Exception e){Toast.makeText(getApplicationContext(),"Nothing", Toast.LENGTH_LONG).show();}
                //*******************************TCP************************************************/
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
                //**************************************UDP*******************************/
                Toast.makeText(getApplicationContext(),""+dat, Toast.LENGTH_LONG).show();
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getApplicationContext(),"New Spinner Thread", Toast.LENGTH_LONG).show();
                        spin.setVisibility(View.VISIBLE);
                    }
                }).start();*/
                while(score == "")
                {

                    //Toast.makeText(getApplicationContext(),"New Spinner Thread", Toast.LENGTH_LONG).show();
                    //spin.setVisibility(View.VISIBLE);
                }
               // spin.setVisibility(View.VISIBLE);
                Intent next = new Intent(lol.this, Next.class);
                Toast.makeText(getApplicationContext(),score, Toast.LENGTH_LONG).show();
                next.putExtra("give", score);
                startActivity(next);
            }
        });
        }
}
