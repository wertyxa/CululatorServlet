package View;

import static View.GetHtml.getPartialHtml;

public class Calculator {
    private static String filename;
    private String path;

    public static String getFilename() {
        return filename;
    }

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "calculator");

    }

    private static Calculator ourInstance = new Calculator();

    public static Calculator getInstance() {
        return ourInstance;
    }

    private Calculator() {
    }


}