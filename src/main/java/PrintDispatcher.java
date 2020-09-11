import comparator.DocumentTypeComparator;
import comparator.PageSizeComparator;
import comparator.PrintTimeComparator;
import document.AbstractDocument;
import document.DocumentSort;

import java.util.Collections;
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
        queue.remove(document);
    }

    public List<AbstractDocument> getPrintedDocumentsList(){
        return getPrintedDocumentsList(DocumentSort.PRINT_ORDER);
    }

    public List<AbstractDocument> getPrintedDocumentsList(DocumentSort sort){
        List<AbstractDocument> list = printer.getPrintedDocuments();
        switch (sort){
            case DOC_TYPE:
                Collections.sort(list, new DocumentTypeComparator());
                break;
            case PRINT_TIME:
                Collections.sort(list, new PrintTimeComparator());
                break;
            case PAGE_SIZE:
                Collections.sort(list, new PageSizeComparator());
                break;
        }
        return list;
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
        return printer.getWaitingDocuments();
    }
}
