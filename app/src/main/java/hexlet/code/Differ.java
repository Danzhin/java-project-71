package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        String extension = getExtension(filePath1, filePath2);

        String data1 = FileReader.toString(filePath1);
        String data2 = FileReader.toString(filePath2);

        Map<String, Object> map1 = Parser.toMap(data1, extension);
        Map<String, Object> map2 = Parser.toMap(data2, extension);

        return getDiffer(map1, map2);
    }

    private static String getExtension(String filePath1, String filePath2) throws Exception {
        String extension = filePath1.substring(filePath1.lastIndexOf('.') + 1);
        if (extension.equals(filePath2.substring(filePath2.lastIndexOf('.') + 1))) {
            return extension;
        } else {
            throw new Exception();
        }
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
