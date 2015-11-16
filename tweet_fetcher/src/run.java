
import java.util.*;
import java.io.*;

public class run {
    String topic;
    
    public run(String topic)
    {
        this.topic = topic;
    }

    public float[] extreme() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Choice(+)\n[1] Timeline Tweets\n[2] HashTag Tweets\n");
        int choice = in.nextInt();
        float []fin = new float[30];
        
        switch (choice) {
            case 1:
                /*System.out.println("Enter Exact Twitter Handle !!");
                topic = in.next();*/
                timeline prof = new timeline();
                prof.setup(topic);
                show(prof.writt);
                break;
            case 2:
                /*System.out.println("Enter Topic & Number of Tweets !!");
                topic = in.next();*/
                int no = 500;
                tweetquery tweets = new tweetquery();
                tweets.setup(no, topic);
                float arr[] = show(tweets.comm);
                fin = compute(arr);
                for(int l = 0 ;l < 30;++l)
                {
                    System.out.println("Sentiment +> "+fin[l]);
                }
                break;
            default:
                System.out.println("INVALID INPUT !!!\n");
                break;

        }
        return fin;
    }
    public  float[] compute(float[]arr) throws IOException {
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

    public  float[] show(LinkedHashSet<String> comm) throws IOException {
        NLP.init();
        File f = new File("C:\\Users\\ABC\\Desktop\\Tweets.txt");
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
                //acc += senti;
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
