package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String getDifferInFormat(Map<String, DifferKey> differ, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.toString(differ);
            case "plain" -> Plain.toString(differ);
            case "json" -> Json.toString(differ);
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }

}
