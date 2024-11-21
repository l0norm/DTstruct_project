//class to handle the query 
public class query {
    
    LinkedList<Integer> result;
    private final String[] words;

    private LinkedStack<WordDocumentMapping> invertedIndexForQuery;

    private LinkedStack<Boolean> indexForQuery;
    
    

    private LinkedStack<String> operators;
    private LinkedList<String> postfix;




    public query(String query){
        words = query.split(" ");
        initializeInvertedIndexQuery();
        initializeIndexQuery();
        
        
        
        

    }
    private void initializeInvertedIndexQuery(){
    	new InvertedIndex(Index.index); // building the inverted index first
    	result = new LinkedList<>();
        postfix = new LinkedList<>();
        operators = new LinkedStack<>();
        invertedIndexForQuery = new LinkedStack<>();
        convertingToPostfix(words);
        processQuery();
        result.printList();
        
    }

    public void convertingToPostfix(String[] words){//convert the query to postfix
        for(int i = 0; i < words.length; i++){//inserting the words to the postfix
            String word = words[i];
            if(word.equals("AND") || word.equals("OR")){//if the word is an operator
                operators.push(word);
            }else{
                postfix.insert(word);//inserting the word to the postfix
               
          }
            if (i%2==0 && i > 0 /*no operator in the index 0*/ ) {//if the index is even
                String temp = operators.pop();
                if(temp.equals("AND")){//if the operator is AND ad it to the postfix cause it has higher precedence
                    postfix.insert(temp);
                }else{//if its other than and push it back to the stack
                    operators.push(temp);
                }
            }
        }
        while (!operators.empty()) {//push all the remaining operators to the postfix
           postfix.insert(operators.pop());
        }
    }


    public void processQuery(){
    	postfix.findFirst(); // here
      
      while (!postfix.last()) {
        if(postfix.retrieve().equals("AND") || postfix.retrieve().equals("OR")){//if its an operator process it 
           if(postfix.retrieve().equals("AND")){
            andProcess();
           }else{
            orProcess();
           }
           postfix.findNext();




        }else{//if its not an operator add it to the invertedindexforquery and its docIDs
            String word = postfix.retrieve();
            WordDocumentMapping newMapping = new WordDocumentMapping(word);
            Node<WordDocumentMapping> current = InvertedIndex.getHead();
            while (current != null) {//adding the docIDs to the newMapping
                if(current.data.word.equals(word)){
                    newMapping.docIDs = current.data.docIDs;
                    break;
                }
                current = current.next;
            }
            invertedIndexForQuery.push(newMapping);//insert the newMapping to the invertedindexforquery
            postfix.findNext();
        }

      }
      // last element always will always be an operator
      if(postfix.retrieve().equals("AND")){
          andProcess();
         }else{
          orProcess();
         }
      addToResult(invertedIndexForQuery);

    }




    public void andProcess(){//process the and operator a method in the linkedlist class
        WordDocumentMapping first = invertedIndexForQuery.pop();
        WordDocumentMapping second = invertedIndexForQuery.pop();

        first.docIDs.intersection(second.docIDs);
        
        if(first.docIDs.size() > 0){//if the result has docIDs
            invertedIndexForQuery.push(first);//push the result to the inverted index
        }
        
        

    }

    public void orProcess(){//process the or operator a method in the linkedlist class
        WordDocumentMapping first = invertedIndexForQuery.pop();
        WordDocumentMapping second = invertedIndexForQuery.pop();
        first.docIDs = first.docIDs.union(second.docIDs);
        invertedIndexForQuery.push(first);

    }

    public void addToResult(LinkedStack<WordDocumentMapping> invertedIndexForQuery){//add the docIDs to the result ,,, to a linkedlist rather than a stack
        WordDocumentMapping current = invertedIndexForQuery.pop();
        current.docIDs.findFirst(); // always make sure to be on the first element
        while(!current.docIDs.last()){
            result.insert(current.docIDs.retrieve());
            current.docIDs.findNext();
        }
        result.insert(current.docIDs.retrieve());

    }
    //=======================================================================
    //boolean query using only index 
    //=======================================================================


    public void initializeIndexQuery(){
    	result = new LinkedList<>();
        postfix = new LinkedList<String>();
        operators = new LinkedStack<String>();
        indexForQuery = new LinkedStack<>();
        convertingToPostfix(words); //convert the query to postfix
        processIndexQuery(); 
        result.printList();


    }

    public void processIndexQuery(){
        int n = Index.index.length();
        Index.index.findFirst();
        
        for(int i =0; i<n; i++){//taking each index at a time
           
            postfix.findFirst();
            while (!postfix.last()) {//processing the whole postfix for each index,,,, first store the boolean to indexforquery then process it
                if(postfix.retrieve().equals("AND") || postfix.retrieve().equals("OR")){
                   if(postfix.retrieve().equals("AND")){
                    andIndexProcess();
                   }else{
                    orIndexProcess();
                   }
                   
                }else{
                    String word = postfix.retrieve();
                    indexForQuery.push(Index.index.retrieve().doc.contains(word)); //if the word is in the index then push true else false
                    
                }
                postfix.findNext();
        
            }
            // last element will always be a operator
                if(postfix.retrieve().equals("AND")){
                 andIndexProcess();
                }else{
                 orIndexProcess();
                }

            if(indexForQuery.pop()){// at last we will have only one boolean in the stack ,,, if its true than the query is true for that index
                result.insert(Index.index.retrieve().docId);//then store the docID in the result
            }

            

            Index.index.findNext();
        }
        
    }


    public void andIndexProcess(){ //now we have 2 boolean to compare them 
        boolean first = indexForQuery.pop();
        boolean second = indexForQuery.pop();
        indexForQuery.push(first && second);

    }

    public void orIndexProcess(){//we have 2 boolean to compare them 
        boolean first = indexForQuery.pop();
        boolean second = indexForQuery.pop();
        indexForQuery.push(first || second);

    }
 


}
