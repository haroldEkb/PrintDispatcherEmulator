package document;

/**
 * @author haroldekb@mail.ru
 **/

public class PDFDocument extends AbstractDocument {
    private final String content;

    public PDFDocument(String content) {
        super(2000, "PDF", PageSize.A4);
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Содержание: " + content + "\n";
    }
}
