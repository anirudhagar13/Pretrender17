
import java.io.*;
import java.net.*;

class server {

    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9891);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        String receive,date;
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            receive = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + receive);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            date = receive.split("~",2)[1]; 
            //*********************COMPUTATION*****************************
            run obj = new run(receive);
            //Constricted Version
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
            return;
        }
    }
}
