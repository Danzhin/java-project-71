package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Key {

    public static Set<String> getKeys(Map<String, Object> map) {
        return new TreeSet<>(map.keySet());
    }

    public static Set<String> getAllKeys(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);
        return allKeys;
    }

    public static String getFormat(String key, Set<String> keys1, Set<String> keys2, Object value1, Object value2) {
        if (!keys2.contains(key)) {
            return "removed";
        } else if (!keys1.contains(key)) {
            return "added";
        } else if (Objects.equals(value1, value2)) {
            return "unchanged";
        } else {
            return "changed";
        }
    }

}
