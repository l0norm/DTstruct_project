import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//this class is used to store the doc data and remove the stop words
public class Index{
    public static int docIDCounter = 0; // made it static to track the number of doc , starts with 1
    
    private LinkedList<String> stopWords;
    public static LinkedList<docs> index; // linked list of type docs to store a list inside every element in the list 



    public Index(String csvPath, String stopWordsPath){
        index = new LinkedList<docs>(); 
        loadStopWords(stopWordsPath);
        readDoc(csvPath);
    }

    public void readDoc(String csvPath){
        try {
            File csvFile = new File(csvPath);
            Scanner scanner = new Scanner(csvFile);
            
            
            while (scanner.hasNextLine()) {
                boolean hasValidLine = false;
                docs data = new docs(docIDCounter);//adding the id of the doc ,,, and initializing the list that has the words
                String line = scanner.nextLine();
                
                

                if (line.length() > 1 && Character.isDigit(line.charAt(0))) {
                    line = line.substring(2);
                    String[] words = line.split("\\s+"); 

                for(String word : words) {
                    word = word.toLowerCase().trim();
                    word = word.replaceAll("[^a-zA-Z0-9]", "");
                
                    if(!word.isEmpty() && !stopWords.contains(word)) {
                        data.doc.insert(word);
                        hasValidLine = true;
                    }
            
                }
                }
                
            

                if(hasValidLine){
                    index.insert(data);//inserting the list of words inside the index ,,,, so every index has a list called doc
                    docIDCounter++;
                }
            }

            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not read CSV file");
            e.printStackTrace();
        }
    }

    public void loadStopWords(String stopWordsPath){
        try {
            File stopWordsFile = new File(stopWordsPath);
            Scanner scanner = new Scanner(stopWordsFile);
            stopWords = new LinkedList<String>();
            while (scanner.hasNextLine()) {
                String stopWord = scanner.nextLine();
                stopWords.insert(stopWord);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not read stop words file");
            e.printStackTrace();
        }
    }
    public static  void printIndex() { // method for printing the docs 
    	index.findFirst(); // here making the current points to the head
        while(!index.last()){
            index.retrieve().printDoc();
            index.findNext();
        }
        
        index.retrieve().printDoc(); 
    }
   

}