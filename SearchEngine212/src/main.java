import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class main {
public static void main (String [] args ) {
	
	

//	String data =  ""; 
//	        File file = new File("/Users/salman/Downloads/639b4418-96e4-4c46-afbf-fcb96b896750.txt");
//	        try (Scanner scanner = new Scanner(file)) {
//	            while (scanner.hasNextLine()) {
//	                String line = scanner.nextLine();
//	                data += line ;
//	                System.out.println(line);
//	            }
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        }
//            System.out.println();
//            System.out.println(data);
//            System.out.println("hey"=="hey\n");

	File stopWords = new File("/Users/salman/Downloads/stop.txt");
	File file = new File("/Users/salman/Downloads/639b4418-96e4-4c46-afbf-fcb96b896750.txt");
	String newstr = "";
	String stopwordstr="";
	String line = "";
	boolean itisAstopWord = false ; 
    try (Scanner scanner = new Scanner(file)) {
    	Scanner stop = new Scanner(stopWords);
        while (scanner.hasNextLine()) {
        	stop = new Scanner(stopWords);
        	itisAstopWord = false ;
        	 line = scanner.next(); 
        	 System.out.println(line);
        	while(stop.hasNextLine()) {
        		stopwordstr = stop.nextLine();
        	    if( line.equalsIgnoreCase(stopwordstr)) {
        	    	
        	    	itisAstopWord = true ;
        	    	
        	    }     	
        	}
        	if(!itisAstopWord) {
        		newstr += line+" ";
        		//ADD THE WORD TO THE BST SINCE ITS NOT IN THE STOP WORDS 
        	}
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    System.out.println("\n\n"+newstr );
	        
	    }

	
	
	
	
}

