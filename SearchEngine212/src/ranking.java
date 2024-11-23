//class to rank the documents based on the query
public class ranking{
    public LinkedList<Integer> result;
    
    public LinkedList<docs> indexForRank;

    public LinkedList<String> words;
    

    public ranking(LinkedList<Integer> result,LinkedList<String> words){
        this.result = result;
        this.words = words;
        indexForRank = new LinkedList<>();
    }


    public void convertingToIndex(){
       Index.index.findFirst();

       while(!Index.index.last()){

        
        if(result.contains(Index.index.getNode().data.docId)){
            indexForRank.insert(Index.index.getNode().data);
        }
        
        Index.index.findNext();
       }
       if(result.contains(Index.index.getNode().data.docId)){
        indexForRank.insert(Index.index.getNode().data);
       }


    }

    public void calculateRank() {
        indexForRank.findFirst();
        while (!indexForRank.last()) {  // Process all documents including second-to-last
            int rank = 0;  // Reset rank for each document
            words.findFirst();
            while (!words.last()) {  // Process all words except last
                indexForRank.retrieve().doc.findFirst();
                while (!indexForRank.retrieve().doc.last()) {  // Process all words in doc except last
                    if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
                        rank++;
                    }
                    indexForRank.retrieve().doc.findNext();
                }
                // Check last word in document
                if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
                    rank++;
                }
                words.findNext();
            }
            // Check last query word against document
            indexForRank.retrieve().doc.findFirst();
            while (!indexForRank.retrieve().doc.last()) {
                if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
                    rank++;
                }
                indexForRank.retrieve().doc.findNext();
            }
            if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
                rank++;
            }
            
            indexForRank.retrieve().rank = rank;
            indexForRank.findNext();
        }
        
        // Process the last document (same logic as above)
        int rank = 0;
        words.findFirst();
        while (!words.last()) {
            indexForRank.retrieve().doc.findFirst();
            while (!indexForRank.retrieve().doc.last()) {
                if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
                    rank++;
                }
                indexForRank.retrieve().doc.findNext();
            }
            if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
                rank++;
            }
            words.findNext();
        }
        // Check last word for last document
        indexForRank.retrieve().doc.findFirst();
        while (!indexForRank.retrieve().doc.last()) {
            if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
                rank++;
            }
            indexForRank.retrieve().doc.findNext();
        }
        if (indexForRank.retrieve().doc.retrieve().equals(words.retrieve())) {
            rank++;
        }
        indexForRank.retrieve().rank = rank;
    }
    // public void printRank(){
    //     indexForRank.findFirst();
    //     while(!indexForRank.last()){
    //         System.out.println(indexForRank.retrieve().docId + " " + indexForRank.retrieve().rank);
    //         indexForRank.findNext();
    //     }
    //     System.out.println(indexForRank.retrieve().docId + " " + indexForRank.retrieve().rank);
    // }

    public void sort() {
        indexForRank = mergeSort(indexForRank);
    }

    private LinkedList<docs> mergeSort(LinkedList<docs> list) {
        // Base case: if list is empty or has one element, it's already sorted
        if (list.empty() || list.size() == 1) {
            return list;
        }
        
        // Split the list into two halves
        LinkedList<docs> left = new LinkedList<>();
        LinkedList<docs> right = new LinkedList<>();
        int mid = list.size() / 2;
        
        // Fill left half
        list.findFirst();
        for (int i = 0; i < mid; i++) {
            left.insert(list.retrieve());
            list.findNext();
        }
        
        // Fill right half
        while (!list.last()) {
            right.insert(list.retrieve());
            list.findNext();
        }
        right.insert(list.retrieve()); // Insert last element
        
        // Recursively sort both halves
        left = mergeSort(left);
        right = mergeSort(right);
        
        // Merge the sorted halves
        return merge(left, right);
    }

    private LinkedList<docs> merge(LinkedList<docs> left, LinkedList<docs> right) {
        LinkedList<docs> merged = new LinkedList<>();
        
        left.findFirst();
        right.findFirst();
        
        // Compare and merge while both lists have elements
        while (!left.empty() && !right.empty()) {
            if (left.retrieve().rank >= right.retrieve().rank) {
                merged.insert(left.retrieve());
                left.remove();
            } else {
                merged.insert(right.retrieve());
                right.remove();
            }
        }
        
        // Add remaining elements from left list
        while (!left.empty()) {
            merged.insert(left.retrieve());
            left.remove();
        }
        
        // Add remaining elements from right list
        while (!right.empty()) {
            merged.insert(right.retrieve());
            right.remove();
        }
        
        return merged;
    }





    public void printRankedResult(){
        indexForRank.findFirst();
        while(!indexForRank.last()){
            System.out.println(indexForRank.retrieve().docId + " " + indexForRank.retrieve().rank);
            indexForRank.findNext();
        }
        System.out.println(indexForRank.retrieve().docId + " " + indexForRank.retrieve().rank);
       

    }
 }