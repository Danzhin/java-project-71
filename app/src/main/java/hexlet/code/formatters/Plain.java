package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Plain {

    public static String toPlain(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = Key.getKeys(map1);
        Set<String> keys2 = Key.getKeys(map2);
        Set<String> allKeys = Key.getAllKeys(keys1, keys2);
        return allKeys.stream()
                .map(key -> getChange(key, keys1, keys2, map1, map2))
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    private static String getChange(String key, Set<String> keys1, Set<String> keys2,
                                    Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);
        String keyFormat = Key.getFormat(key, keys1, keys2, value1, value2);

        if (!keys2.contains(key)) {
            return "Property '" + key + "' was removed";
        } else if (!keys1.contains(key)) {
            return "Property '" + key + "' was added with value: " + getFormatedValue(value2);
        } else if (!Objects.equals(value1, value2)) {
            return "Property '" + key + "' was updated. From " + getFormatedValue(value1) + " to " + getFormatedValue(value2);
        }
        return "";
    }

    private static String getFormatedKey(String key, Object value1, Object value2, String keyFormat) {
        return switch (keyFormat) {
            case "remote" -> getRemovedKey(key);
            case "added" -> getAddedKey(key, value2);
            case "changed" -> getChangedKey(key, value1, value2);
            case "unchanged" -> "";
            default -> null;
        };
    }

    private static String getRemovedKey(String key) {
        return "Property '" + key + "' was removed";
    }

    private static String getAddedKey(String key, Object value2) {
        return "Property '" + key + "' was added with value: " + getFormatedValue(value2);
    }

    private static String getChangedKey(String key, Object value1, Object value2) {
        return "Property '" + key + "' was updated. From " + getFormatedValue(value1) + " to " + getFormatedValue(value2);
    }

    private static String getFormatedValue(Object value) {
        if (value instanceof Map || value instanceof Iterable) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }

}
