package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {

    public static String toString(List<Map<String, Object>> differ) {
        return differ.stream()
                .map(Stylish::formatKeyData)
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String formatKeyData(Map<String, Object> keyData) {
        String key = (String) keyData.get("key");
        String status = (String) keyData.get("status");
        Object value1 = keyData.get("value1");
        Object value2 = keyData.get("value2");
        return switch (status) {
            case "removed" -> "  - " + key + ": " + value1;
            case "added" -> "  + " + key + ": " + value2;
            case "changed" -> "  - " + key + ": " + value1 + "\n" + "  + " + key + ": " + value2;
            default -> "    " + key + ": " + value1;
        };
    }

}
