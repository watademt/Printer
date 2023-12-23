import java.util.ArrayList;
import java.util.Queue;

public class Dispatcher {

    private ArrayList<Document> Queue;
    private ArrayList<Document> GoodPrint;
    private ArrayList<Document> CancelPrint;
    private ThreadDocument ThreadDocument;

    public Dispatcher(){
        Queue = new ArrayList<>();
        GoodPrint = new ArrayList<>();
        CancelPrint = new ArrayList<>();
        ThreadDocument = new ThreadDocument(this);
        ThreadDocument.setDaemon(true);
    }

    public void AddDocument(Document document){Queue.add(document);}
    public  void RemoveDocument(Document document){Queue.remove(document);}
    public void CancelDocument(Document document){
        CancelPrint.add(document);
        RemoveDocument(document);
    }
    public void AddGoodPrint(Document document){GoodPrint.add(document);}

    public ThreadDocument GetThread(){return ThreadDocument;}
    public ArrayList<Document> GetCancel(){return CancelPrint;}
    public ArrayList<Document> GetQueue(){return Queue;}
    public ArrayList<Document> GetGoodPrint(){return GoodPrint;}


}
