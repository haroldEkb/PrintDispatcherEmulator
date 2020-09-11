package document;

/**
 * @author haroldekb@mail.ru
 **/

public class SmallDocument extends AbstractDocument {
    private final String content;

    public SmallDocument(String content) {
        super(3000, ".DOCX", PageSize.A5);
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Содержание: " + content;
    }
}
