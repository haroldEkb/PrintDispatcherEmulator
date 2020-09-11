package document;

/**
 * @author haroldekb@mail.ru
 **/

public abstract class AbstractDocument {
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
}
