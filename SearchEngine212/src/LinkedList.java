public class LinkedList<T>{
	private Node<T> head;
	private Node<T> current;
	
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
	// adding getter for head 
	public Node<T> getHead() {
		return head;
	}
	public Node<T> getNode(){
		return current;
	}
	public int length(){
		int count = 0;
		Node<T> current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
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
	public int size(){
		int count = 0;
		Node<T> current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}
	public boolean contains(T element) {
		Node<T> current = head;
		while (current != null) {
			if (current.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	
	//method for printing the elements of the list ( for checking )
	public void printList() {
        current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
	public void union(LinkedList<T> other){
		if(this.length() > other.length()){
			Node<T> tmp = this.head;
		while(tmp != null){
			if(!other.contains(tmp.data)){
				this.insert(tmp.data);
			}
			tmp = tmp.next;
		}
		}else{
			Node<T> tmp2 = other.head;
			while(tmp2 != null){
				if(!this.contains(tmp2.data)){
					other.insert(tmp2.data);
				}
				tmp2 = tmp2.next;
			}

		}	
	}
	public void intersection(LinkedList<T> other){
		Node<T> tmp = this.head;
		while(tmp != null){
			if(other.contains(tmp.data)){
				tmp = tmp.next;
			}else if(!other.contains(tmp.data)){
				this.remove();
			}
			
		}
	}
	
}

