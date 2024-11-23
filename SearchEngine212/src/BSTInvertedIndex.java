//rather than indexiing a doc for every word, this class is to get every word and the docs that have that word
public class BSTInvertedIndex{
    public static BST<WordDocumentMapping> bSTInvertedIndex;

    public BSTInvertedIndex(LinkedList<docs> index){
        
        bSTInvertedIndex = new BST<>();
        buildBSTInvertedIndex(index);
    }


    public void buildBSTInvertedIndex(LinkedList<docs> index){
        int docID = 0;
        Node<docs> currentDocNode;
        String word;
        index.findFirst();
        while(!index.last()){
           currentDocNode = index.getNode();
           currentDocNode.data.doc.findFirst();
            while(!currentDocNode.data.doc.last()){
                word = currentDocNode.data.doc.retrieve();
                addWordToBSTInvertedIndex(word, docID);
                currentDocNode.data.doc.findNext();
            }
            word = currentDocNode.data.doc.retrieve();
            addWordToBSTInvertedIndex(word, docID);
            currentDocNode.data.doc.findNext();

            docID++;
            index.findNext();
        }
        currentDocNode = index.getNode();
        currentDocNode.data.doc.findFirst();
        while(!currentDocNode.data.doc.last()){
            word = currentDocNode.data.doc.retrieve();
            addWordToBSTInvertedIndex(word, docID);
            currentDocNode.data.doc.findNext();
        }
        word = currentDocNode.data.doc.retrieve();
        addWordToBSTInvertedIndex(word, docID);

    }

    public void addWordToBSTInvertedIndex(String word, int docID){
        int key = wordToKey(word);

        if(bSTInvertedIndex.findkey(key)){//if the key is found just add the docID to the BSTinvertedindex
            bSTInvertedIndex.retrieve().docIDs.insert(docID);
            return;
        }   
        bSTInvertedIndex.find(Relative.Root);
         // If the word is not in the inverted index, add it
         WordDocumentMapping newMapping = new WordDocumentMapping(word);
         newMapping.docIDs.insert(docID);
         bSTInvertedIndex.insert(key, newMapping);//probllemo

    }
    
    
    private int wordToKey(String word){// we cant use the word as a key so we use the hashcode of the word  as a key
        return word.hashCode();
    }


    
    public static void printAllInvertedBstIndex(){

        if(bSTInvertedIndex.empty()){
            System.out.println("BSTInvertedIndex is empty");
            return;
        }
        //method to print all nodes using 
        printInvertedIndex(bSTInvertedIndex.getRoot());

    }

    public static void printInvertedIndex(BSTNode<WordDocumentMapping> current) {//method to print the BSTinvertedindex  
        //recursive method to print the BSTinvertedindex
        if(current == null){
            return;
        }
       
        printInvertedIndex(current.left);
        System.out.println("Word: " + current.data.word + " -> Document IDs: ");

        current.data.docIDs.printList();
        printInvertedIndex(current.right);
    }
    public static BSTNode<WordDocumentMapping> getRoot(){
        return bSTInvertedIndex.getRoot();
    }



}   

