package View;

import static View.GetHtml.getPartialHtml;

public class PlaningDayCollection {
      private String filename;
      private String path;

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "planingDayCollection");
    }
    public String getFilename() {
        return filename;
    }

    private static PlaningDayCollection ourInstance = new PlaningDayCollection();

    public static PlaningDayCollection getInstance() {
        return ourInstance;
    }

    private PlaningDayCollection() {
    }
}
