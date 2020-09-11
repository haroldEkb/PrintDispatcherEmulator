package document;

/**
 * @author haroldekb@mail.ru
 **/

public class DWGDocument extends AbstractDocument {
    private final String content;

    public DWGDocument(String content) {
        super(3000, "DWG", PageSize.A3);
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Содержание: " + content + "\n";
    }
}
