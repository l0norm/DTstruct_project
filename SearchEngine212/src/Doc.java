import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//this class is used to store the doc data and remove the stop words
public class Doc{
    public static int docIDCounter = 0; // made it static to track the number of doc
    private LinkedList<String> docData;
    private LinkedList<String> stopWords;
    public static LinkedList<String> index[] = new LinkedList[10]; // array of linked list for indexing
    
    public Doc(String csvPath, String stopWordsPath){
        docIDCounter++;
        removeStopWords(stopWordsPath);
        readDoc(csvPath);
    }

    public void readDoc(String csvPath){
        try {
            File csvFile = new File(csvPath);
            Scanner scanner = new Scanner(csvFile);
            
            docData = new LinkedList<String>();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                String[] words = line.split("\\s+"); 
                for(String word : words) {
                    word = word.toLowerCase().trim();
                    word = word.replaceAll("[^a-zA-Z0-9]", "");
                    
                    if(!word.isEmpty() && !stopWords.contains(word)) {
                        docData.insert(word);
                    }
                } 
            }
            index[docIDCounter] = docData;
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not read CSV file");
            e.printStackTrace();
        }
    }

    public void removeStopWords(String stopWordsPath){
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
    	for(int i = 1 ; i <=docIDCounter ; i++) {
    		System.out.print("Document "+i+": ");
    		index[i].printList();
    	}
    }
    public static void buildInvert() {
    	InvertedIndex invert = new InvertedIndex(index , docIDCounter);
    	InvertedIndex.printInvertedIndex();
    }

}