import document.AbstractDocument;
import document.DocumentSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author haroldekb@mail.ru
 **/

public class PrintDispatcher {

    private final BlockingQueue<AbstractDocument> queue;
    private final Printer printer;

    public PrintDispatcher(BlockingQueue<AbstractDocument> queue, Printer printer) {
        this.queue = queue;
        this.printer = printer;
    }

    public void print(AbstractDocument document){
        queue.add(document);
    }

    public void cancelPrinting(AbstractDocument document){
        if (queue.contains(document)) {
            queue.remove(document);
        }
    }

    public List<AbstractDocument> getPrintedDocumentsList(){
        return getPrintedDocumentsList(DocumentSort.PRINT_ORDER);
    }

    public List<AbstractDocument> getPrintedDocumentsList(DocumentSort sort){
        return printer.getPrintedDocuments();
    }

    public double averagePrintTime(){
        List<AbstractDocument> documents = getPrintedDocumentsList();
        double sumPrintTime = 0;
        for (AbstractDocument document : documents) {
            sumPrintTime += document.getPrintTime();
        }
        return sumPrintTime/documents.size();
    }

    public List<AbstractDocument> stop(){
        printer.shutdown();
        return new ArrayList<AbstractDocument>(queue);
    }
}
