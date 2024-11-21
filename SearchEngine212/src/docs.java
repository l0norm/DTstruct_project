public class docs {

    LinkedList<String> doc;
    int docId;
    public docs(int docId){
        this.docId = docId;
        doc = new LinkedList<>();

    }
   

    public void printDoc(){
        while(!doc.last()){
            System.out.println(doc.retrieve());
            doc.findNext();
        }
    }
}
