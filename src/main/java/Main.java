import document.AbstractDocument;
import document.SmallDocument;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author haroldekb@mail.ru
 **/

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<AbstractDocument> queue = new ArrayBlockingQueue<AbstractDocument>(1000000);
        Printer printer = new Printer(queue);
        PrintDispatcher dispatcher = new PrintDispatcher(queue, printer);
        printer.start();
        dispatcher.print(new SmallDocument("1234567890-sadfghjk"));
        dispatcher.print(new SmallDocument("234567890-sadfghjk"));
        dispatcher.print(new SmallDocument("34567890-sadfghjk"));
        dispatcher.print(new SmallDocument("4567890-sadfghjk"));
        dispatcher.print(new SmallDocument("567890-sadfghjk"));
        Thread.sleep(7000);
        System.out.println(dispatcher.getPrintedDocumentsList());
        System.out.println(dispatcher.stop());
    }
}
