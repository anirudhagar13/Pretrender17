//**Crawls ony through handles authored by username entered
import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;
public class timeline
{
	public static void main(String s[])
	{
		gettweet();
	}
	public static void gettweet()
	{
          Twitter twitter = new TwitterFactory().getInstance();
            int pageno = 1;
            String user = "Pulkit_em";
            //List statuses = new ArrayList();
	 ArrayList<Status> statuses = new ArrayList<Status>();

            while (true) 
            {
                try 
                {
                    int size = statuses.size(); 
                    Paging page = new Paging(pageno++, 100);
                    statuses.addAll(twitter.getUserTimeline(user, page));
                    System.out.println("Gathered " + statuses.size() + " tweets");
                    if (statuses.size() == size)
                        break;
                }
                catch(TwitterException e)
                {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < statuses.size(); i++)
            {
                Status t = (Status)statuses.get(i);
                System.out.println("*****************************************************************");
                System.out.println(t.getText());
                System.out.println("Author = "+t.getUser().getScreenName());
                System.out.println("*****************************************************************");
            }
	}

}
