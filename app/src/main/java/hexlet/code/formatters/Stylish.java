package hexlet.code.formatters;

import hexlet.code.DifferKey;

import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {

    public static String toStylish(Map<String, DifferKey> differMap) {
        return differMap.entrySet().stream()
                .map(entry -> formatDifferKey(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String formatDifferKey(String key, DifferKey differKey) {
        return switch (differKey.status()) {
            case "removed" -> getRemovedKey(key, differKey.oldValue());
            case "added" -> getAddedKey(key, differKey.newValue());
            case "unchanged" -> getUnchangedKey(key, differKey.oldValue());
            default -> getChangedKey(key, differKey.oldValue(), differKey.newValue());
        };
    }

    private static String getRemovedKey(String key, Object oldValue) {
        return "  - " + key + ": " + oldValue;
    }

    private static String getAddedKey(String key, Object newValue) {
        return "  + " + key + ": " + newValue;
    }

    private static String getUnchangedKey(String key, Object value) {
        return "    " + key + ": " + value;
    }

    private static String getChangedKey(String key, Object oldValue, Object newValue) {
        return "  - " + key + ": " + oldValue + "\n" + "  + " + key + ": " + newValue;
    }
}
