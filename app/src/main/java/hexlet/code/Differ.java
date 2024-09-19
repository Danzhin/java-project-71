package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {

    private static String readFile(String filePath) throws Exception {
        return Files.readString(Paths.get(filePath));
    }

    private static String getFileFormat(String filePath1, String filePath2) {
        String format = filePath1.substring(filePath1.lastIndexOf('.') + 1);
        return format.equals(filePath2.substring(filePath1.lastIndexOf('.') + 1)) ? format : "error";
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        String fileData1 = readFile(filePath1);
        String fileData2 = readFile(filePath2);

        String format = getFileFormat(filePath1, filePath2);

        Map<String, Object> map1 = Parser.fileDataToMap(fileData1, format);
        Map<String, Object> map2 = Parser.fileDataToMap(fileData2, format);

        Set<String> keys1 = new TreeSet<>(map1.keySet());
        Set<String> keys2 = new TreeSet<>(map2.keySet());

        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);

        return allKeys.stream()
                .map(key -> {
                    if (!keys2.contains(key)) {
                        return formatRemovedKey(key, map1.get(key));
                    } else if (!keys1.contains(key)) {
                        return formatAddedKey(key, map2.get(key));
                    } else if (map1.get(key).equals(map2.get(key))) {
                        return formatUnchangedKey(key, map1.get(key));
                    } else {
                        return formatChangedKey(key, map1.get(key), map2.get(key));
                    }
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String formatRemovedKey(String key, Object value) {
        return "  - " + key + ": " + value;
    }

    private static String formatAddedKey(String key, Object value) {
        return "  + " + key + ": " + value;
    }

    private static String formatChangedKey(String key, Object oldValue, Object newValue) {
        return "  - " + key + ": " + oldValue + "\n" + "  + " + key + ": " + newValue;
    }

    private static String formatUnchangedKey(String key, Object value) {
        return "    " + key + ": " + value;
    }

}
