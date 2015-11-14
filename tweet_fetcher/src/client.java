
import java.util.*;
import java.io.*;

public class client {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Choice(+)\n[1] Timeline Tweets\n[2] HashTag Tweets\n");
        int choice = in.nextInt();
        String topic;
        switch (choice) {
            case 1:
                System.out.println("Enter Exact Twitter Handle !!");
                topic = in.next();
                timeline prof = new timeline();
                prof.setup(topic);
                show(prof.writt);
                break;
            case 2:
                System.out.println("Enter Topic & Number of Tweets !!");
                topic = in.next();
                int no = in.nextInt();
                tweetquery tweets = new tweetquery();
                tweets.setup(no, topic);
                show(tweets.comm);
                break;
            default:
                System.out.println("INVALID INPUT !!!\n");
                break;
        }
        System.out.println("Enter DATE for Prediction");
        String date = in.next();   
    }

    public static void show(LinkedHashSet<String> comm) throws IOException {
        NLP.init();
        File f = new File("C:\\Users\\ABC\\Desktop\\Tweets.txt");
        FileWriter fol = new FileWriter(f);
        try {
            int count = 1;
            String filter;
            for (String tweet : comm) {
                filter = tweet.replaceAll("RT|@.*?\\s|(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                        + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                        + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", "");
                fol.write("*****************************TWEET:" + count + "*****************************\n");
                fol.write(filter + " :=> " + NLP.findSentiment(filter) + "(Sentiment)\n");
                fol.write("*************************************************************************\n");
                ++count;
            }
        } finally {
            fol.close();
        }
    }

    /* public static void trysenti()                //Function for Testing Stanford Core NLP
     {
     ArrayList<String> tweets = new ArrayList<String>();
     tweets.add("CHOCOLATE ALMOND BREAD PUDDING Really nice recipes.");/*
     tweets.add(" Radek is an OK football player");
     tweets.add("Radek is a bad football player") ;
     tweets.add(" Radek is a really bad football player");
     tweets.add(" Mark is a really good football player");
     tweets.add(" Mark is a good football player");
     tweets.add(" Mark is an OK football player");
     tweets.add(" Mark is a bad football player");
     NLP.init();
     for(String tweet : tweets) 
     System.out.println(tweet + " : " + NLP.findSentiment(tweet));
     }*/
}
