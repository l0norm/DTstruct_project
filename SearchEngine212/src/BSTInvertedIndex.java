//rather than indexiing a doc for every word, this class is to get every word and the docs that have that word
public class BSTInvertedIndex{
    private static BST<WordDocumentMapping> BSTInvertedIndex;

    public BSTInvertedIndex(LinkedList<String>[] index, int docIDCounter){
        BSTInvertedIndex = new BST<>();
        buildBSTInvertedIndex(index, docIDCounter);
    }

    public void buildBSTInvertedIndex(LinkedList<String>[] index, int docIDCounter){
        for(int docID = 0; docID <= docIDCounter; docID++){
            Node<String> currentWordNode = index[docID].getHead();
            while(currentWordNode != null){
                String word = currentWordNode.data;
                addWordToBSTInvertedIndex(word, docID);
                currentWordNode = currentWordNode.next;
            }
        }
    }

    public void addWordToBSTInvertedIndex(String word, int docID){
        

        if(BSTInvertedIndex.findkey(docID)){//if the key is found just add the docID to the BSTinvertedindex
            BSTInvertedIndex.current.data.docIDs.insert(docID);
            return;
        }   
        
         // If the word is not in the inverted index, add it
         WordDocumentMapping newMapping = new WordDocumentMapping(word);
         newMapping.docIDs.insert(docID);
         BSTInvertedIndex.insert(docID , newMapping);




    }
    

    public static void printAllInvertedIndex(){
        //method to print all nodes using 
        printInvertedIndex(BSTInvertedIndex.getRoot());

    }

    public static void printInvertedIndex(BSTNode<WordDocumentMapping> current) {//method to print the BSTinvertedindex  
        //recursive method to print the BSTinvertedindex
       
        printInvertedIndex(current.left);
        System.out.println("Word: " + current.data.word + " -> Document IDs: ");

        current.data.docIDs.printList();


        printInvertedIndex(current.right);
    }



}   

