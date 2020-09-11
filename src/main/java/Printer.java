import document.AbstractDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author haroldekb@mail.ru
 **/

public class Printer extends Thread{
    private final BlockingQueue<AbstractDocument> queue;
    private final ExecutorService service = Executors.newSingleThreadExecutor();
    private final List<AbstractDocument> waitingDocuments = new ArrayList<AbstractDocument>();
    private final List<AbstractDocument> printedDocuments = new ArrayList<AbstractDocument>();
    private volatile boolean isWorking = true;

    public Printer(BlockingQueue<AbstractDocument> queue) {
        this.queue = queue;
    }

    private void print(final AbstractDocument document){
        service.submit(new Runnable() {
            public void run() {
                System.out.println("Печать документа...");
                System.out.println(document);
                try {
                    Thread.sleep(document.getPrintTime());
                    System.out.println("Печать завершена");
                    waitingDocuments.remove(document);
                    printedDocuments.add(document);
                } catch (InterruptedException e) {
                    System.out.println("Печать прервана");
                }
            }
        });
    }

    public void run(){
        AbstractDocument document = queue.poll();
        while (isWorking) {
            if (document == null) {
                document = queue.poll();
            } else {
                waitingDocuments.add(document);
                print(document);
                document = null;
            }
        }
    }

    public List<AbstractDocument> getPrintedDocuments() {
        return printedDocuments;
    }

    public List<AbstractDocument> getWaitingDocuments() {
        return waitingDocuments;
    }

    public void shutdown(){
        service.shutdownNow();
        isWorking = false;
    }
}
