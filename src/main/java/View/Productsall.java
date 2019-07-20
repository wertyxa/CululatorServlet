package View;

import static View.GetHtml.getPartialHtml;

public class Productsall {

    private static String filename;
    private String path;

    public static String getFilename() {
        return filename;
    }

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "productsall");

    }

    private static Productsall ourInstance = new Productsall();

    public static Productsall getInstance() {
        return ourInstance;
    }

    private Productsall() {
    }
}
