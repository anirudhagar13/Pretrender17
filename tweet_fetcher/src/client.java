import java.util.*;
import java.io.*;


public class client{

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Topic & no of Tweets");
        String topic = in.nextLine();
        int no = in.nextInt();
        tweetquery tweets = new tweetquery();
        tweets.setup(no,topic);
        tweets.display();
        
       /* NLP.init();
        for(String tweet : tweets) {
            System.out.println(tweet + " : " + NLP.findSentiment(tweet));
        }*/
        
     }
}