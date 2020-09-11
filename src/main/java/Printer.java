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
    private final List<AbstractDocument> printedDocuments = new ArrayList<AbstractDocument>();
    private boolean isWorking = true;

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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Печать завершена");
                printedDocuments.add(document);
            }
        });
    }

    public void run(){
        AbstractDocument document = queue.poll();
        while (isWorking) {
            if (document == null) {
                document = queue.poll();
            } else {
                print(document);
                document = null;
            }
        }
    }

    public List<AbstractDocument> getPrintedDocuments() {
        return printedDocuments;
    }

    public void shutdown(){
        service.shutdown();
        isWorking = false;
    }


}
