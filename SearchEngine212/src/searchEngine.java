//last thing is to get everything together and make the search engine here
public class searchEngine{
    public Index index;
    public InvertedIndex invertedIndex;
    public query process;
    public BSTInvertedIndex bSTInvertedIndex;

    public searchEngine(){//initialize the index and the inverted index and the bst inverted index
        index = new Index("C:\\Users\\Lenovo\\Desktop\\GIT\\DTstruct_project\\dataset.csv", "C:\\Users\\Lenovo\\Desktop\\GIT\\DTstruct_project\\stop.txt");
        invertedIndex = new InvertedIndex(Index.index);//this is not an instance of index,,,, its a static list !!!!
        bSTInvertedIndex = new BSTInvertedIndex(Index.index);
        
    }

    public void processInverted(String query){
        process = new query(query);
        process.inverted();
        process.printResults();
    }



    public void processIndex(String query){
        process = new query(query);
        process.index();

    }

    public void processInvertedBST(String query){
        process = new query(query);
        process.invertedBST();

    }

    public void processRanked(String query){
        query = query.replace(" ", " OR ");
        
        process = new query(query);
        process.invertedBST();
        process.getRankedResult();
    }



}
