//**Crawls ony through handles authored by username entered

import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;

public class timeline {

    LinkedHashSet<String> writt;

    public void setup(String topic) {
        writt = new LinkedHashSet<String>();
        Twitter twitter = new TwitterFactory().getInstance();
        int pageno = 1;
        String user = topic;
        ArrayList<Status> statuses = new ArrayList<Status>();

        while (true) {
            try {
                int size = statuses.size();
                Paging page = new Paging(pageno++, 100);
                statuses.addAll(twitter.getUserTimeline(user, page));
                System.out.println("Gathered " + statuses.size() + " tweets");
                if (statuses.size() == size) {
                    break;
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("Size = "+statuses.size());
        for (int i = 0; i < statuses.size(); i++) {
            Status t = (Status) statuses.get(i);
            writt.add(t.getText());
            /*System.out.println("*****************************************************************");
             System.out.println(t.getText());
             System.out.println("Author = "+t.getUser().getScreenName());
             System.out.println("*****************************************************************");*/
        }
    }

    public void display() {
        for (String s : writt) {
            System.out.println("*****************************************************************");
            System.out.println(s);
        }
    }

}
