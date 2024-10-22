package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Plain {

    public static String toString(List<Map<String, Object>> differ) {
        return differ.stream()
                .map(Plain::formatKeyData)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }

    private static String formatKeyData(Map<String, Object> keyData) {
        String key = (String) keyData.get("key");
        String status = (String) keyData.get("status");
        Object value1 = keyData.get("value1");
        Object value2 = keyData.get("value2");
        return switch (status) {
            case "removed" -> "Property '" + key + "' was removed";
            case "added" -> "Property '" + key + "' was added with value: " + getFormattedValue(value2);
            case "changed" -> "Property '" + key + "' was updated. From "
                    + getFormattedValue(value1) + " to " + getFormattedValue(value2);
            default -> null;
        };
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
