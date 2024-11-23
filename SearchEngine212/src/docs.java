public class docs {

    LinkedList<String> doc;
    int docId;
    int rank;
    public docs(int docId){
        this.docId = docId;
        doc = new LinkedList<>();

    }
   

    public void printDoc(){
    	doc.findFirst(); // here making the current points to the head
    	System.out.print("Docuement "+docId+": ");
        while(!doc.last()){
            System.out.print(doc.retrieve()+" ");
            doc.findNext();
        }
        // here for printing the last element
        System.out.println(doc.retrieve());
    }
}
