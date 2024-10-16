package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static String readFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

}

