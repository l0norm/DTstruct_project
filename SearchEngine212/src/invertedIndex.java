class InvertedIndex {
    public static  LinkedList<WordDocumentMapping> invertedIndex;

    public InvertedIndex(LinkedList<docs> index) {
        
        invertedIndex = new LinkedList<>();
        buildInvertedIndex(index);
    }


    // Method to build the inverted index
    private void buildInvertedIndex(LinkedList<docs> index) {
        int docID = 0;
        index.findFirst();
        while(!index.last()) {
            Node<docs> currentWordNode = index.getNode();

            while (currentWordNode != null) {
                String word = currentWordNode.data.doc.retrieve();
                addWordToInvertedIndex(word, docID);
                currentWordNode.data.doc.findNext();
            }
            docID++;
            index.findNext();
        }
    }

    // Method to add a word and its document ID to the inverted index
    private void addWordToInvertedIndex(String word, int docID) {
        invertedIndex.findFirst();
        // word in the list ( repeated )
        while (!invertedIndex.last()) { //looking for the word in the inverted index
            if (invertedIndex.retrieve().word.equals(word)) {
                Node<WordDocumentMapping> current = invertedIndex.getNode(); //if found get the node
                if (!current.data.docIDs.contains(docID)) {
                    current.data.docIDs.insert(docID); //if the docID is not in the list insert it
                }
                return;
            }
            invertedIndex.findNext();
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
    }

    public static Node<WordDocumentMapping> getHead(){
        return invertedIndex.getHead();
    }
}
