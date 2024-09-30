package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Stylish {

    public static String generate(Set<String> allKeys,  Set<String> keys1, Set<String> keys2, Map<String, Object> map1, Map<String, Object> map2) {
        return allKeys.stream()
                .map(key -> compareKeys(key, keys1, keys2, map1, map2))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String compareKeys(String key, Set<String> keys1, Set<String> keys2, Map<String, Object> map1, Map<String, Object> map2) {
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
