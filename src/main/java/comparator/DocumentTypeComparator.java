package comparator;

import document.AbstractDocument;

import java.util.Comparator;

/**
 * @author haroldekb@mail.ru
 **/

public class DocumentTypeComparator implements Comparator<AbstractDocument> {
    @Override
    public int compare(AbstractDocument o1, AbstractDocument o2) {
        return o1.getTypeName().compareTo(o2.getTypeName());
    }
}
