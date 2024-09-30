package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Plain {

    public static String generate(Set<String> allKeys, Set<String> keys1, Set<String> keys2,
                                  Map<String, Object> map1, Map<String, Object> map2) {
        return allKeys.stream()
                .map(key -> getChange(key, keys1, keys2, map1, map2))
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    private static String getChange(String key, Set<String> keys1, Set<String> keys2,
                                    Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.getOrDefault(key, null);
        Object value2 = map2.getOrDefault(key, null);

        if (!keys2.contains(key)) {
            return "Property '" + key + "' was removed";
        } else if (!keys1.contains(key)) {
            return "Property '" + key + "' was added with value: " + formatValue(value2);
        } else if (!Objects.equals(value1, value2)) {
            return "Property '" + key + "' was updated. From " + formatValue(value1) + " to " + formatValue(value2);
        }
        return "";
    }

    private static String formatValue(Object value) {
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
