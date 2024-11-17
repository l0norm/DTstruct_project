import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//this class is used to store the doc data and remove the stop words
public class Doc{
    private int docID;
    private LinkedList<String> docData;
    private LinkedList<String> stopWords;
    
    public Doc(String csvPath, String stopWordsPath){
        removeStopWords(stopWordsPath);
        readDoc(csvPath);
        

    }






    public void readDoc(String csvPath){//only after adding the stop words to the list
        try {
            File csvFile = new File(csvPath);
            Scanner scanner = new Scanner(csvFile);
            
            docData = new LinkedList<String>();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); //read the line        
                docID++; //assign the docid to the line
                
                String[] words = line.split("\\s+"); 
                for(String word : words) {
                    word = word.toLowerCase().trim(); //put in lower case  
                    if(!word.isEmpty()) {
                        if(!stopWords.contains(word)){
                            word = word.replaceAll("[^a-zA-Z0-9]", "");
                            docData.insert(word); //add each word to docData only if its not in the stop words list 
                            
                        }
                    }
                }
            }
            
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not read CSV file");
            e.printStackTrace();
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