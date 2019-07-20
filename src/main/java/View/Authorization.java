package View;

import static View.GetHtml.getPartialHtml;

public class Authorization {
    private String filename;
    private String path;

    public String getFilename() {
        return filename;
    }

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "authorization");

    }

    private static Authorization ourInstance = new Authorization();

    public static Authorization getInstance() {
        return ourInstance;
    }

    private Authorization() {
    }


}
