import java.util.Scanner;

public class main {
	
public static void main (String [] args ) {


	
	
	searchEngine search = new searchEngine();
	
	 int choice;
	 Scanner input = new Scanner(System.in);
	
	do { 
		System.out.println("==================================================================");
		System.out.println("===============================menu===============================");
		System.out.println("==================================================================");
		System.out.println("1. Boolean retrieval");
		System.out.println("2. Ranked retrieval");
		System.out.println("3. Indexed Documents");
		System.out.println("4. Indexed Tokens");
		System.out.println("5. Exit");
		System.out.print("enter your choice:");
		choice = input.nextInt();
		input.nextLine();// this is to clear the buffer

		switch (choice) {
			case 1:
				System.out.println("which boolean retrieval method you want to use?");
				System.out.println("1. Inverted Index");
				System.out.println("2. BST Inverted Index");
				System.out.println("3. using only index ");
				System.out.print("enter your choice: ");
				int choice2 = input.nextInt();
				input.nextLine();// this is to clear the buffer
				switch (choice2) {
					case 1:
						System.out.print("enter your query: ");
						String query = input.nextLine();
						search.processInverted(query);
						break;
					case 2:
						System.out.print("enter your query: ");
						String query2 = input.nextLine();
						search.processInvertedBST(query2);
						break;
					case 3:
						System.out.print("enter your query: ");
						String query3 = input.nextLine();
						search.processIndex(query3);
						break;
					default:
					System.out.println("Invalid choice");
						break;
				}
				break;
			case 2:
				System.out.println("enter the words you want to rank");
				String query = input.nextLine();
				search.processRanked(query);
				break;
			case 3:
				Index.printIndex();
				break;
			case 4:
				InvertedIndex.printInvertedIndex();
				break;
			case 5:
				input.close();
				break;
			default:
				System.out.println("Invalid choice");
				break;
		} 




	} while (choice != 5);



	}
}

