//rather than indexiing a doc for every word, this class is to get every word and the docs that have that word
public class BSTInvertedIndex{
    private static BST<WordDocumentMapping> BSTInvertedIndex;

    public BSTInvertedIndex(LinkedList<String>[] index, int docIDCounter){
        BSTInvertedIndex = new BST();
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
        BSTNode<WordDocumentMapping> current = BSTInvertedIndex.find(Relative.Root);
        while(current != null){
            if(current.data.word.equals(word)){
                current.data.docIDs.insert(docID);
                return;
            }
            current = current.next;
        }
    }




}   

