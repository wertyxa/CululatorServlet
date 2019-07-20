package View;

import static View.GetHtml.getPartialHtml;

public class Planing {

    private static String filename;
    private String path;

    public static String getFilename() {
        return filename;
    }

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "planing");
    }

    private static Planing ourInstance = new Planing();

    public static Planing getInstance() {
        return ourInstance;
    }

    private Planing() {
    }
}
