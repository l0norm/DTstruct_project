import java.util.Scanner;


public class interface {
public static void main (String [] args ) {





	int choice;
	Scanner input = new Scanner(System.in);
	do { 
		System.out.println("1. Boolean retrieval");
		System.out.println("2. Ranked retrieval");
		System.out.println("3. Indexed Documents");
		System.out.println("4. Indexed Tokens");
		System.out.println("5. Exit");
		choice = input.nextInt();

		switch (choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
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

