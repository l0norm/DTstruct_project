// helper class to build the inverted index
public class WordDocumentMapping {
	 String word;
     LinkedList<Integer> docIDs;
     BST<Integer> docIDsBST;

     public WordDocumentMapping(String word) {
         this.word = word;
         this.docIDs = new LinkedList<>();
     }

    
}
