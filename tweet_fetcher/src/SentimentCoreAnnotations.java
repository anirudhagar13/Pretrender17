

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.trees.Tree;

public class SentimentCoreAnnotations {

  public static class AnnotatedTree implements CoreAnnotation<Tree> {
    public Class<Tree> getType() {
      return Tree.class;
    }
  }

  public static class ClassName implements CoreAnnotation<String> {
    public Class<String> getType() {
      return String.class;
    }
  }
}

