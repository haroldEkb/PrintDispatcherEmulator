package comparator;

import document.AbstractDocument;

import java.util.Comparator;

/**
 * @author haroldekb@mail.ru
 **/

public class PrintTimeComparator implements Comparator<AbstractDocument> {
    @Override
    public int compare(AbstractDocument o1, AbstractDocument o2) {
        return (int) (o1.getPrintTime() - o2.getPrintTime());
    }
}
