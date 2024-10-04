package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String getChange(Map<String, Object> map1, Map<String, Object> map2, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.toStylish(map1, map2);
            case "plain" -> Plain.toPlain(map1, map2);
            case "json" -> Json.toJson(map1, map2);
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }

}
