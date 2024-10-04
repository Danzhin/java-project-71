package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Formatter {

    public static String getChange(Map<String, Object> map1, Map<String, Object> map2, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.toStylish(map1, map2);
            case "plain" -> Plain.toPlain(map1, map2);
            case "json" -> Json.generate(map1, map2);
            default -> throw new Exception("incorrect format");
        };
    }

    private static Set<String> getAllKeys(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);
        return allKeys;
    }

}
