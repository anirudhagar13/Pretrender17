
import java.io.*;
import java.net.*;

class server {

    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9891);
        
        File f3 = new File("C:\\Users\\ABC\\Desktop\\PreTrender\\ConnectLog.txt");
        FileWriter f1 = new FileWriter(f3);
        
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        String receive,date;
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            receive = new String(receivePacket.getData());
                  
           //LOG FILE Creation 
            System.out.println("\n****************CLIENT DETAILS*********************\n");
            System.out.print(receivePacket.getAddress().toString()+"\n");
            System.out.print(receive+"\n");
            System.out.print(receivePacket.getLength()+"\n");
            System.out.println("\n***************************************************\n");
            //f1.close();
            
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();            
            //*********************COMPUTATION*****************************
            run obj = new run(receive);
            float fin = obj.extreme();
            String damn = ""+fin;  
            
            sendData = damn.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            
             return;
            
        } 
    }
}
