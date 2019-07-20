package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static View.GetHtml.getPartialHtml;

public class IndexView {

    private String filename;
    private String path;

    private static IndexView ourInstance = new IndexView();

    public static IndexView getInstance() {
        return ourInstance;
    }

    private IndexView() {
    }

    public String getFilename() {
        return filename;
    }

    public void setPath(String path) {
        this.path = path;
        filename = getPartialHtml(path, "index");
    }

    /**
     * Зчитує фай за адресою шляху, що є в змінній path + filename + .html
     *
     * @param path
     * @param filename
     * @return об'єкт String  в якому є текст з файлу
     */

}
