package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static String toString(String filePath) throws Exception {
        return Files.readString(Paths.get(filePath));
    }

}
