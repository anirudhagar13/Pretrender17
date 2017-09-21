
import java.util.*;
import java.io.*;

public class run {

    String topic;
    String date;

    public run(String receive) {
        topic = receive.split("~", 2)[0];
        topic = topic.replaceAll("\\s", "");
        date = receive.split("~", 2)[1];
        
    }

    public float extreme() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("REQUEST RECEIVED :+ " + topic + "\nDATE RECEIVED :+ " + date);
        System.out.println("Enter Choice(+)\n[1] Timeline Tweets\n[2] HashTag Tweets\n");
        int choice = in.nextInt();
        float[] fin;
        float senti = (float) 0.0;

        switch (choice) {
            case 1:
                /*System.out.println("Enter Exact Twitter Handle !!");
                 topic = in.next();*/
                timeline prof = new timeline();
                prof.setup(topic);
                show(prof.writt);
                break;
            case 2:
                int no = 2000;
                tweetquery tweets = new tweetquery();
                tweets.setup(no, topic);
                float arr[] = show(tweets.comm);
                //*******************Sentiment File*******************
                float[] lol = new float[400];
                for (int i = 0; i < 400; ++i) {
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
                String dttobesent = date.split("/", 2)[0];
                Integer month = 1+Integer.parseInt(date.split("/", 3)[1]);
                Integer recvd = Integer.parseInt(dttobesent);
                int diff = 0;
                System.out.println("Month = "+month);
                if(month != 12)
                    recvd += month*30;                       

                diff = recvd - 11;
                System.out.println("No Of DAYS AHEAD :+ "+diff);
                predict p = new predict();
                senti = (float) p.predict(diff);
                System.out.println("Final Sentiment :+ " + senti);

                break;
            default:
                System.out.println("INVALID INPUT !!!\n");
                break;

        }
        return senti;
    }

    public float[] compute(float[] arr) throws IOException {
        float[] lol = new float[60];
        float acc = 0, count = 0;
        int temp = 0;
        int len = arr.length / 60;
        for (int i = 0; i < arr.length; ++i) {
            acc += arr[i];
            ++count;
            if (count == len) {
                lol[temp] = acc / len;
                acc = 0;
                count = 0;
                ++temp;
            }
            if (temp == 59) {
                break;
            }
        }

        count = 0;
        acc = 0;
        int dam = len * 60;
        while (dam < arr.length) {
            acc += arr[dam];
            ++dam;
            ++count;
        }
        lol[59] = acc / count;
        for (int i = 0; i < 60; ++i) {
            lol[i] += .25;
        }

        return lol;
    }

    public float[] show(LinkedHashSet<String> comm) throws IOException {
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
                fol.write(filter);//+ " :=> " + senti + "(Sentiment)\n");
                fol.write("\n*************************************************************************\n");
                arr[count] = senti;
                ++count;

            }
        } finally {
            fol.close();
        }
        return arr;
    }

}
