package hexlet.code;

import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String data1 = FileReader.readFile(filePath1);
        String data2 = FileReader.readFile(filePath2);

        String extension1 = FileReader.getExtension(filePath1);
        String extension2 = FileReader.getExtension(filePath2);

        Map<String, Object> map1 = Parser.toMap(data1, extension1);
        Map<String, Object> map2 = Parser.toMap(data2, extension2);

        return Formatter.getDiffer(map1, map2, format);
    }



}
