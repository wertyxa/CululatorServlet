package View;

import static View.GetHtml.getPartialHtml;

public class Dayall {
    private static String filename;
    private String path;

    public static String getFilename() {
        return filename;
    }

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "productsdays");
    }

    private static Dayall ourInstance = new Dayall();

    public static Dayall getInstance() {
        return ourInstance;
    }

    private Dayall() {
    }
}
