// helper class to build the inverted index
public class WordDocumentMapping {
	 String word;
     LinkedList<Integer> docIDs;

     public WordDocumentMapping(String word) {
         this.word = word;
         this.docIDs = new LinkedList<>();
     }
}
