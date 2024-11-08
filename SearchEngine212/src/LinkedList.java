import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.IOException;
import java.util.Scanner;
	
	public class LinkedList<T> implements List<T>{
		private Node<T> head;
		private Node<T> current;
		private String docData ;
		
		public void ReadDocument(String path ) {
			File file = new File("/Users/salman/Downloads/639b4418-96e4-4c46-afbf-fcb96b896750.txt");
	        try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine(); //line here is one word out of time until doc is empty 
	                docData += line ; //docdata will store the file without taken care of the lines /salman 
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	
			
			
		
		
		
		
		
		
		
		
		
		
		
	    public LinkedList() {
			head = current = null;
		}
		public boolean empty() {
			return head == null;
		}
		public boolean last() {
			return current.next == null;
		}
	    public boolean full() {
	        return false;
	    }
	    public void findFirst() {
	        current = head;
	    }
	    public void findNext() {
	        current = current.next;
	    }
	    public T retrieve() {
	        return current.data;
	    }
	    public void update(T e) {
	        current.data = e;
	    }
	    public void insert(T e) {
	        if (empty()) {
	            current = head = new Node<T>(e);
	        } else {
	            Node<T> tmp = current.next;
	            current.next = new Node<T>(e);
	            current = current.next;
	            current.next = tmp;
	        }
	    }
	    public void remove() {
	        if (current == head) {
	            head = head.next;
	        } else {
	            Node<T> tmp = head;
	            while (tmp.next != current) {
	                tmp = tmp.next;
	            }
	            tmp.next = current.next;
	        }
	        if (current.next == null) {
	            current = head;
	        } else {
	            current = current.next;
	        }
	    }
	    public T mostFrequentElement() {
	       //         Write the method mostFrequentElement, member of the class LinkedList, that returns
	        // the most frequent element in the list. The most frequent element is the element
	        // appearing the highest number of times. If one or more element appear the same
	        // number of times, the one encountered earlier is returned.
	        // Example 1.1. Given the list l : A, B, C, B, C, D, E, mostFrequentElement() returns
	        // B.
			if(empty()){
			return null;}

		    Node<T> e = null;
		    Node<T> tmp1 = head;
		    Node<T> tmp2 = head;
			int o = 0;
			while(tmp1 != null){
				int t = 0 ;
				while(tmp2!=null){
					if(tmp1.data==tmp2.data){
						t++;
						
					}
					tmp2=tmp2.next;
					
				}
				if(t>o){
					o=t;
					e = tmp1;

				}
				tmp1 = tmp1.next;
				tmp2=head;


			}
			return e.data;



	    
		}
	}

