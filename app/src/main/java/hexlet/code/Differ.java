package hexlet.code;

import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.fileToMap(filePath1);
        Map<String, Object> map2 = Parser.fileToMap(filePath2);
        Map<String, DifferKey> differ = Comparator.getDiffer(map1, map2);
        return Formatter.getDifferInFormat(differ, format);
    }

}
