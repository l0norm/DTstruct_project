import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//this class is used to store the doc data and remove the stop words
public class Doc{
    private int docID;
    private LinkedList<String> docData;
    private LinkedList<String> stopWords;
    
    public Doc(String csvPath, String stopWordsPath){
        docID = 0;
        removeStopWords(stopWordsPath);
        readDoc(csvPath);
    }

    public void readDoc(String csvPath){
        try {
            File csvFile = new File(csvPath);
            Scanner scanner = new Scanner(csvFile);
            
            docData = new LinkedList<String>();
            
            while (scanner.hasNextLine()) {
                docID++;
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

}