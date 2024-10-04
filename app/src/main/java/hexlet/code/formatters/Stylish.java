package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Stylish {

    public static String toStylish(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = Key.getKeys(map1);
        Set<String> keys2 = Key.getKeys(map2);
        Set<String> allKeys = Key.getAllKeys(keys1, keys2);

        return allKeys.stream()
                .map(key -> getFormatedKey(key, keys1, keys2, map1, map2))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String getFormatedKey(String key, Set<String> keys1, Set<String> keys2,
                                    Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);
        String keyFormat = Key.getFormat(key, keys1, keys2, value1, value2);
        return switch (keyFormat) {
            case "removed" -> getRemovedKey(key, value1);
            case "added" -> getAddedKey(key, value2);
            case "unchanged" -> getUnchangedKey(key, value1);
            case "changed" -> getChangedKey(key, value1, value2);
            default -> throw new IllegalStateException("Unexpected value: " + keyFormat);
        };
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

    private static String getChangedKey(String key, Object value1, Object value2) {
        return "  - " + key + ": " + value1 + "\n" + "  + " + key + ": " + value2;
    }

}
