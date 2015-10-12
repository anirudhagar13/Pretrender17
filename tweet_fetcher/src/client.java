import java.util.*;
import java.io.*;
public class client
{
    /*public static void main(String[] args) 
    {
        //Scanner in = new Scanner(System.in);
        //System.out.println("Enter Topic & no of Tweets");
        //String topic = in.nextLine();
        //int no = in.nextInt();
       // tweetquery tweets = new tweetquery();
       // tweets.setup(no,topic);
        //tweets.display();
        System.out.println("Entry Point 0");
        NLP.init();
        //for(String tweet : tweets.comm)
        
            System.out.println("Entry Point 1");
            //System.out.println(tweet + " : " + NLP.findSentiment(tweet));
     }*/
    
    public static void main(String[] args) {
        ArrayList<String> tweets = new ArrayList<String>();
        tweets.add("That was good!!");
        NLP.init();
        for(String tweet : tweets) {
            System.out.println(tweet + " : " + NLP.findSentiment(tweet));
        }
    }
}