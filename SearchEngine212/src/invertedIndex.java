class InvertedIndex {
    private static  LinkedList<WordDocumentMapping> invertedIndex;

    public InvertedIndex(LinkedList<String>[] index, int docIDCounter) {
        invertedIndex = new LinkedList<>();
        buildInvertedIndex(index, docIDCounter);
    }


    // Method to build the inverted index
    private void buildInvertedIndex(LinkedList<String>[] index, int docIDCounter) {
        for (int docID = 1; docID <= docIDCounter; docID++) {
            Node<String> currentWordNode = index[docID].getHead();
            while (currentWordNode != null) {
                String word = currentWordNode.data;
                addWordToInvertedIndex(word, docID);
                currentWordNode = currentWordNode.next;
            }
        }
    }

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
        Node<WordDocumentMapping> current = invertedIndex.getHead();
        while (current != null) {
            System.out.print("Word: " + current.data.word + " -> Document IDs: ");
            current.data.docIDs.printList();
            current = current.next;
        }
    }
}
