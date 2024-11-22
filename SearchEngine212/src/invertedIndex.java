class InvertedIndex{//the inverted index extends index .. to get index info easily 
    public static  LinkedList<WordDocumentMapping> invertedIndex;
    

    public InvertedIndex(LinkedList<docs> index) {
        invertedIndex = new LinkedList<>();
        buildInvertedIndex(index);
    }


    // Method to build the inverted index
    private void buildInvertedIndex(LinkedList<docs> index) {
        int docID = 0; // starts with 0
        Node<docs> currentDoc;
        String word;
        index.findFirst();
        while(!index.last()) {
            currentDoc = index.getNode(); 
            currentDoc.data.doc.findFirst(); //starting from the first element in the inner document
            while (!currentDoc.data.doc.last()) {
                word = currentDoc.data.doc.retrieve();
                addWordToInvertedIndex(word, docID);
                currentDoc.data.doc.findNext();
            }
            word = currentDoc.data.doc.retrieve();
            addWordToInvertedIndex(word, docID);
            currentDoc.data.doc.findNext();
            
            docID++;
            index.findNext();
        }
    
        
     }// you dont need to check the last element cause its empty 

    // Method to add a word and its document ID to the inverted index
    private void addWordToInvertedIndex(String word, int docID) {
        Node<WordDocumentMapping> current = invertedIndex.getHead();
        // word in the list ( repeated )
        
        while (current != null) {
            if (current.data.word.equals(word)) {
                if (!current.data.docIDs.contains(docID)) {
                    current.data.docIDs.insert(docID);
                }
                return;
            }
            current = current.next;
        }

        // If the word is not in the inverted index, add it
        WordDocumentMapping newMapping = new WordDocumentMapping(word);
        newMapping.docIDs.insert(docID);
        invertedIndex.insert(newMapping);
    }

    // Method to print the inverted index
    public static void printInvertedIndex() {
        invertedIndex.findFirst();
        while (!invertedIndex.last()) {
            System.out.print("Word: " + invertedIndex.retrieve().word + " -> Document IDs: ");
           invertedIndex.retrieve().docIDs.printList();
            invertedIndex.findNext();
        }
        System.out.print("Word: " + invertedIndex.retrieve().word + " -> Document IDs: ");
        invertedIndex.retrieve().docIDs.printList(); // printing the last element
    }

    public static Node<WordDocumentMapping> getHead(){
        return invertedIndex.getHead();
    }
}
