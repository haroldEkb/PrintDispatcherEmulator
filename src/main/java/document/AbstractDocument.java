package document;

import java.util.Objects;

/**
 * @author haroldekb@mail.ru
 **/

public abstract class AbstractDocument{
    private final long printTime;
    private final String typeName;
    private final PageSize pageSize;

    public AbstractDocument(long printTime, String typeName, PageSize pageSize) {
        this.printTime = printTime;
        this.typeName = typeName;
        this.pageSize = pageSize;
    }

    public long getPrintTime() {
        return printTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "Тип документа: " + typeName +
                ", Время печати: " + printTime +
                ", Размер листа:" + pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDocument document = (AbstractDocument) o;
        return getPrintTime() == document.getPrintTime() &&
                Objects.equals(getTypeName(), document.getTypeName()) &&
                getPageSize() == document.getPageSize();
    }
}
