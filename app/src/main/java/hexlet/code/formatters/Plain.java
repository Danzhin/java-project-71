package hexlet.code.formatters;

import hexlet.code.DifferKey;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Plain {

    public static String toString(Map<String, DifferKey> differ) {
        return differ.entrySet().stream()
                .map(entry -> getFormatedDifferKey(entry.getKey(), entry.getValue()))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }

    private static String getFormatedDifferKey(String key, DifferKey differKey) {
        return switch (differKey.status()) {
            case "removed" -> getRemovedKey(key);
            case "added" -> getAddedKey(key, differKey.newValue());
            case "unchanged" -> null;
            case "changed" -> getChangedKey(key, differKey.oldValue(), differKey.newValue());
            default -> throw new IllegalStateException("Unexpected value: " + differKey.status());
        };
    }

    private static String getRemovedKey(String key) {
        return "Property '" + key + "' was removed";
    }

    private static String getAddedKey(String key, Object newValue) {
        return "Property '" + key + "' was added with value: " + getFormattedValue(newValue);
    }

    private static String getChangedKey(String key, Object oldValue, Object newValue) {
        return "Property '" + key + "' was updated. From "
                + getFormattedValue(oldValue) + " to " + getFormattedValue(newValue);
    }

    private static String getFormattedValue(Object value) {
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
