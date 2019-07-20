package View;

import static View.GetHtml.getPartialHtml;

public class Registration {
    private String filename;
    private String path;

    public String getFilename() {
        return filename;
    }

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "registration");
     }
    private static Registration ourInstance = new Registration();


    public static Registration getInstance() {
        return ourInstance;
    }
    private Registration (){
    }
}
