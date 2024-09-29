package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        String data1 = FileReader.readFile(filePath1);
        String data2 = FileReader.readFile(filePath2);

        String extension1 = FileReader.getExtension(filePath1);
        String extension2 = FileReader.getExtension(filePath2);

        Map<String, Object> map1 = Parser.toMap(data1, extension1);
        Map<String, Object> map2 = Parser.toMap(data2, extension2);

        return getDiffer(map1, map2);
    }

    private static String getDiffer(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = new TreeSet<>(map1.keySet());
        Set<String> keys2 = new TreeSet<>(map2.keySet());
        Set<String> allKeys = getAllKeys(keys1, keys2);
        return allKeys.stream()
                .map(key -> compareKeys(key, map1, map2, keys1, keys2))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static Set<String> getAllKeys(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);
        return allKeys;
    }

    private static String compareKeys(String key, Map<String, Object> map1, Map<String, Object> map2, Set<String> keys1, Set<String> keys2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        if (!keys2.contains(key)) {
            return getRemovedKey(key, value1);
        } else if (!keys1.contains(key)) {
            return getAddedKey(key, value2);
        } else if (Objects.equals(value1, value2)) {
            return getUnchangedKey(key, value1);
        } else {
            return getChangedKey(key, value1, value2);
        }
    }

    private static String getRemovedKey(String key, Object value) {
        return "  - " + key + ": " + value;
    }

    private static String getAddedKey(String key, Object value) {
        return "  + " + key + ": " + value;
    }

    private static String getUnchangedKey(String key, Object value) {
        return "    " + key + ": " + value;
    }

    private static String getChangedKey(String key, Object oldValue, Object newValue) {
        return "  - " + key + ": " + oldValue + "\n" + "  + " + key + ": " + newValue;
    }

}
