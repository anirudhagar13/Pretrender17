//**Not Able To Crawl Through Handles,as starts repeating after a while.
import java.util.*;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
public class tweetquery 
{
	public static void main(String[] args)
	{
		setup();
	}
	public static void setup()
	{
            Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query("KFC");
            //Adding Date
            query.setSince("2015-09-29");
            int numberOfTweets = 500;
            long lastID = Long.MAX_VALUE;
            ArrayList<Status> tweets = new ArrayList<Status>();  
            while (tweets.size () < numberOfTweets) 
		{
		    if (numberOfTweets - tweets.size() > 100)
		      query.setCount(100);
		    else 
		      query.setCount(numberOfTweets - tweets.size());
		    try
		    {
		    	//System.out.println("############################tweets added1########################################");
		    	QueryResult result = twitter.search(query);
                        // System.out.println("############################tweets added2########################################");
                         tweets.addAll(result.getTweets());
                        //System.out.println("############################tweets added3########################################");
                         System.out.println("Gathered " + tweets.size() + " tweets");
                        for (Status t: tweets) 
                            if(t.getId() < lastID) lastID = t.getId();
                        
                        if(result.nextQuery() == null) break;
		    }
		    catch (TwitterException te)
		    {
		    	System.out.println("Couldn't connect: " + te);
		    } 
		    query.setMaxId(lastID-1);
		  }

             for (int i = 0; i < tweets.size(); i++)
		{
		    Status t = (Status) tweets.get(i);
		    System.out.println("*****************************************************************");
                    System.out.println(t.getText());
                    System.out.println("Author = "+t.getUser().getScreenName());
                    System.out.println("*****************************************************************");
		}
	}
	/*public static void getTweets(String term,Twitter twitter)
	{
	List<Status> tweets = new ArrayList<Status>();
	int wantedTweets = 50;
	long firstQueryID;
	long lastSearchID = Long.MAX_VALUE;
	int remainingTweets = wantedTweets;
	Query query = new Query(term);
	 try
	{ 
	  while(remainingTweets > 0)
	  {
	    remainingTweets = wantedTweets - tweets.size();
	    if(remainingTweets > 100)
	    {
	      query.count(100);
	    }
	    else
	    {
	     query.count(remainingTweets); 
	    }
	    QueryResult result = twitter.search(query);
	    tweets.addAll(result.getTweets());
	    System.out.println("Gathered " + tweets.size() + " tweets");
	    Status s = tweets.get(tweets.size()-1);
	    firstQueryID = s.getId();
	    query.setMaxId(firstQueryID);
	    remainingTweets = wantedTweets - tweets.size();
	  }
	  for (int i = 0; i < tweets.size(); i++)
	  {
	    Status t = (Status) tweets.get(i);
	    System.out.println("*****************************************************************");
		System.out.println(t.getText());
		System.out.println("Author = "+t.getUser().getScreenName());
		System.out.println("*****************************************************************");
	  }
	  //System.out.println("tweets.size() "+tweets.size() );
	}
	catch(TwitterException te)
	{
	  System.out.println("Failed to search tweets: " + te.getMessage());
	  System.exit(-1);
	}
	}*/
}
