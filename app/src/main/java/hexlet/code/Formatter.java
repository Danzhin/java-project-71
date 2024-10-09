package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String toFormat(Map<String, DifferKey> differ, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.toStylish(differ);
            case "plain" -> Plain.toPlain(differ);
            case "json" -> Json.toJson(differ);
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }

}
