
import java.io.*;
import java.net.*;

class server {

    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9896);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String topic = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + topic);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            //*********************COMPUTATION*****************************
            run obj = new run(topic);
            float[]fin = obj.extreme();
            //double dig[] = new double[30];
            //double lol[] = new double[30];
            float sum = 0;
            for(int i = 0 ; i < 30 ; ++i)
            {
                //dig[i] = i+1;
                //lol[i] = fin[i];
               sum += fin[i]; 
            }
            //double senti = predict.get_out(dig,lol,32);
            //System.out.println("NAVNEET + "+senti);
            float senti = sum / 30;
            String damn = ""+senti;   
           /* String damn = "";
            for(int i = 0 ; i < fin.length ; ++i)
            {
                damn += "\n"+""+fin[i];
            }*/
            //*************************************************************
          // String capitalizedSentence = "RECEIVED SENTIMENT + 3.0";
            //sendData = capitalizedSentence.getBytes();
            sendData = damn.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
