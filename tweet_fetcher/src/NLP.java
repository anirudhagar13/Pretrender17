import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.util.Properties;

public class NLP {
    
    static StanfordCoreNLP pipeline;
    static Properties props;

    public static void init() 
    {
        System.out.println("Entry Point -1");
         props  = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        System.out.println("Entry Point -2");
        pipeline = new StanfordCoreNLP(props);
        System.out.println("Entry Point -3");
    }

    public static int findSentiment(String tweet) 
    {
        System.out.println("Entry Point 2");
        int mainSentiment = 0;
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(tweet);
            System.out.println("Entry Point 3");
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class))
            {
                Tree tree = sentence
                        .get(SentimentCoreAnnotations.AnnotatedTree.class);
                System.out.println("Entry Point 4");
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                System.out.println("Entry Point 5");
                String partText = sentence.toString();
                if (partText.length() > longest) 
                {
                    System.out.println("Entry Point 6");
                    mainSentiment = sentiment;
                    System.out.println("Entry Point 7");
                    longest = partText.length();
                    System.out.println("Entry Point 8");
                }

            }
        }
        return mainSentiment;
    }
}