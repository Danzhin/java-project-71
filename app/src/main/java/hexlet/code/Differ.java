package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String fileData1 = readFile(filePath1);
        String fileData2 = readFile(filePath2);

        String fileExtension1 = getFileExtension(filePath1);
        String fileExtension2 = getFileExtension(filePath1);

        Map<String, Object> map1 = Parser.toMap(fileData1, fileExtension1);
        Map<String, Object> map2 = Parser.toMap(fileData2, fileExtension2);

        List<Map<String, Object>> differ = Comparator.getDiffer(map1, map2);

        return Formatter.getDifferInFormat(differ, format);
    }

    public static String readFile(String filePath) throws Exception {
        return Files.readString(Paths.get(filePath));
    }

    private static String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }

}
