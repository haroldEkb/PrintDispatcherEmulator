package comparator;

import document.AbstractDocument;

import java.util.Comparator;

/**
 * @author haroldekb@mail.ru
 **/

public class PageSizeComparator implements Comparator<AbstractDocument> {
    @Override
    public int compare(AbstractDocument o1, AbstractDocument o2) {
        return o1.getPageSize().compareTo(o2.getPageSize());
    }
}
