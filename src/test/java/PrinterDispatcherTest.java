import document.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author haroldekb@mail.ru
 **/

public class PrinterDispatcherTest {
    private final Printer printer;
    private final PrintDispatcher dispatcher;
    private final List<AbstractDocument> docsToPrint = docsToPrint();
    private final List<AbstractDocument> shouldBePrinted = shouldBePrinted();
    private final List<AbstractDocument> shouldNotBePrinted = shouldNotBePrinted();

    public PrinterDispatcherTest() {
        BlockingQueue<AbstractDocument> queue = new ArrayBlockingQueue<AbstractDocument>(1000000);
        this.printer = new Printer(queue);
        this.dispatcher = new PrintDispatcher(queue, printer);
    }

    @Before
    public void setup(){
        printer.start();
    }

    @After
    public void close(){
        dispatcher.stop();
    }

    @Test
    public void printedListTest() throws InterruptedException {
        for (AbstractDocument document : docsToPrint) {
            dispatcher.print(document);
        }
        Thread.sleep(7000);
        Assert.assertEquals(dispatcher.getPrintedDocumentsList(), shouldBePrinted);
    }

    @Test
    public void notPrintedListTest() throws InterruptedException {
        for (AbstractDocument document: docsToPrint){
            dispatcher.print(document);
        }
        Thread.sleep(7000);
        Assert.assertEquals(dispatcher.stop(), shouldNotBePrinted);
    }

    @Test
    public void avgPrintTimeTest() throws InterruptedException {
        for (AbstractDocument document: docsToPrint){
            dispatcher.print(document);
        }
        Thread.sleep(7000);
        Assert.assertEquals(dispatcher.averagePrintTime(), 2000, 100);
    }

    @Test
    public void sortByPrintTimeTest() throws InterruptedException {
        for (AbstractDocument document: docsToPrint){
            dispatcher.print(document);
        }
        Thread.sleep(10000);
        Assert.assertEquals(dispatcher.getPrintedDocumentsList(DocumentSort.PRINT_TIME), sortedByPrintTime());
    }

    @Test
    public void sortByPageSizeTest() throws InterruptedException {
        for (AbstractDocument document: docsToPrint){
            dispatcher.print(document);
        }
        Thread.sleep(10000);
        Assert.assertEquals(dispatcher.getPrintedDocumentsList(DocumentSort.PAGE_SIZE), sortedByPageSize());
    }

    private List<AbstractDocument> docsToPrint(){
        List<AbstractDocument> list = new ArrayList<>();
        list.add(new SmallDocument("Some text"));
        list.add(new PDFDocument("Some scan"));
        list.add(new DWGDocument("Some blueprint"));
        list.add(new SmallDocument("Some text 2"));
        list.add(new SmallDocument("Some text 3"));
        return list;
    }

    private List<AbstractDocument> shouldBePrinted(){
        List<AbstractDocument> list = new ArrayList<>();
        list.add(new SmallDocument("Some text"));
        list.add(new PDFDocument("Some scan"));
        list.add(new DWGDocument("Some blueprint"));
        return list;
    }

    private List<AbstractDocument> shouldNotBePrinted(){
        List<AbstractDocument> list = new ArrayList<>();
        list.add(new SmallDocument("Some text 2"));
        list.add(new SmallDocument("Some text 3"));
        return list;
    }

    private List<AbstractDocument> sortedByPrintTime(){
        List<AbstractDocument> list = new ArrayList<>();
        list.add(new SmallDocument("Some text"));
        list.add(new SmallDocument("Some text 2"));
        list.add(new SmallDocument("Some text 3"));
        list.add(new PDFDocument("Some scan"));
        list.add(new DWGDocument("Some blueprint"));
        return list;
    }

    private List<AbstractDocument> sortedByPageSize() {
        List<AbstractDocument> list = new ArrayList<>();
        list.add(new DWGDocument("Some blueprint"));
        list.add(new PDFDocument("Some scan"));
        list.add(new SmallDocument("Some text"));
        list.add(new SmallDocument("Some text 2"));
        list.add(new SmallDocument("Some text 3"));
        return list;
    }

}
