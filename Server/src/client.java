
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import edu.stanford.nlp.time.SUTime.TimeUnit;

public class client {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        String topic = "";
        String clientDate = "";
        System.out.println("Enter Choice(+)\n[1] Timeline Tweets\n[2] HashTag Tweets\n");
        int choice = in.nextInt();
        if(choice == 1){
        	System.out.println("Enter Exact Twitter Handle, whose timeline has to be analyzed");
        	topic = in.next();	
        }
        if(choice == 2){
        	System.out.println("Enter the hashtag without the hash, that needs to be analyzed");
        	topic = in.next();	
        }
        System.out.println("Enter the date of prediction on which prediction needed as dd-mm-yyyy");
        clientDate = in.next();
        

        switch (choice) {
            case 1:
                timeline prof = new timeline();
                prof.setup(topic);
                float arr_1[] = show(prof.writt);
                
                Plot(clientDate, arr_1);
                
                break;
            case 2:
                int no = 500;
                tweetquery tweets = new tweetquery();             
                tweets.setup(no, topic);
            	float arr_2[] = show(tweets.comm);

                Plot(clientDate, arr_2);

                break;
            default:
                System.out.println("INVALID INPUT !!!\n");
                break;

        }
    }
    
    public static void Plot(String clientDate, float arr[]) throws IOException, ParseException {
    	
        float[] fin;
        float senti = (float) 0.0;
        
        //*******************Sentiment File*******************
        float[] lol = new float[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            lol[i] = arr[i];
        }
        
        String absolutePath = new File("").getAbsolutePath();
        String filePath = absolutePath + "\\src\\files\\SentiMents.txt";
        File f3 = new File(filePath);
        FileWriter fl = new FileWriter(f3);
        for (int j = 0; j < lol.length; ++j) {

            if (j % 10 == 0 && lol[j] <= 2.0) {
                lol[j] += 1;
            }

            fl.write(lol[j] + "\n");
        }
        fl.close();
                        
        //*******************Graph*******************             
        System.out.println("Constricted Version of Sentiment Calculation Plotted:+");
        fin = compute(arr);
        GraphDraw.createAndShowGui(fin);
        
        //******************************MAIN PREDICTION*********************
        Date currDate = new Date();
        Date futureDate = new SimpleDateFormat("dd-MM-yyyy").parse(clientDate);
        long diff = futureDate.getTime() - currDate.getTime();
        float dayDiff = diff / (1000*60*60*24);
        System.out.println("No Of DAYS AHEAD :+ "+Math.round(dayDiff));
        predict p = new predict();
        senti = (float) p.predict(Math.round(dayDiff));
        System.out.println("Final Sentiment :+ " + senti);
    }
    
    public static float[] compute(float[]arr) throws IOException {
        float[] lol = new float[30];
        float acc = 0,count = 0;
        int temp = 0;
        int len = arr.length/30;
        for(int i = 0 ; i < arr.length ;++i)
        {
            acc += arr[i];
            ++count;
            if(count == len)
            {
                lol[temp] = acc/len;
                acc = 0;
                count = 0;
                ++temp;
            }
            if(temp == 29)
                break;
        }
       
        count = 0;
        acc = 0;
        int dam = len*30;
        while(dam<arr.length)
        {
            acc += arr[dam];
            ++dam;
            ++count;
        }
        lol[29] = acc/count;
        System.out.println("LAST VALUE + "+lol[29]);
        return lol;
        
    }

    public static float[] show(LinkedHashSet<String> comm) throws IOException {
        NLP.init();
        String absolutePath = new File("").getAbsolutePath();
        String filePath = absolutePath + "\\src\\files\\Tweets.txt";
        File f = new File(filePath);
        FileWriter fol = new FileWriter(f);
        int len = comm.size();
        float arr[] = new float[len];
        
        try {
            int count = 0;
            String filter;
            float senti, acc = 0;
            int l = 0;
            for (String tweet : comm) {
                if (len == 0) {
                    System.out.println("No TWEET");
                    break;
                }
                filter = tweet.replaceAll("RT|@.*?\\s|(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                        + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                        + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", "");
                senti = NLP.findSentiment(filter);
                
                fol.write("*****************************TWEET:" + count + "*****************************\n");
                fol.write(filter + " :=> " + senti + "(Sentiment)\n");
                fol.write("*************************************************************************\n");
                arr[count] = senti;

                ++count;

            }
        } finally {
            fol.close();
        }
        return arr;
    }

}
