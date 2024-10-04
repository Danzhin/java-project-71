package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static String getExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }

    public static String readFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

}

