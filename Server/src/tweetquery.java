//**Not Able To Crawl Through Handles,as starts repeating after a while.

import java.util.*;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class tweetquery {

    LinkedHashSet<String> comm;

    public void setup(int numberOfTweets, String topic) {
        comm = new LinkedHashSet<String>();
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query(topic);
        
        long lastID = Long.MAX_VALUE;

        ArrayList<Status> tweets = new ArrayList<Status>();

        while (tweets.size() < numberOfTweets) {
            if (numberOfTweets - tweets.size() > 100) {
                query.setCount(100);
            } else {
                query.setCount(numberOfTweets - tweets.size());
            }
            try {
                QueryResult result = twitter.search(query);
                tweets.addAll(result.getTweets());
                System.out.println("Gathered " + tweets.size() + " tweets");

                for (Status t : tweets) {
                    if (t.getId() < lastID) {
                        lastID = t.getId();
                    }
                }

                if (result.nextQuery() == null) {
                    break;
                }
            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
            query.setMaxId(lastID - 1);
        }

        for (int i = 0; i < tweets.size(); i++) {

            Status t = (Status) tweets.get(i);
            if (t.getLang().equals("en")) {
                comm.add(t.getText());
            }
            /*System.out.println("*****************************************************************");
             System.out.println(t.getText());
             System.out.println("Author = "+t.getUser().getScreenName());
             System.out.println("*****************************************************************");*/
        }

    }

    public void display() {
        for (String s : comm) {
            System.out.println("*****************************************************************");
            System.out.println(s);
        }
    }

}
