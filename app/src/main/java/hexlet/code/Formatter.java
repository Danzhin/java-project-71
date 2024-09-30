package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Formatter {

    public static String getDiffer(Map<String, Object> map1, Map<String, Object> map2, String format) throws Exception {
        Set<String> keys1 = new TreeSet<>(map1.keySet());
        Set<String> keys2 = new TreeSet<>(map2.keySet());
        Set<String> allKeys = getAllKeys(keys1, keys2);
        return switch (format) {
            case "stylish" -> Stylish.generate(allKeys, keys1, keys2, map1, map2);
            case "plain" -> Plain.generate(allKeys, keys1, keys2, map1, map2);
            case "json" -> Json.generate(allKeys, keys1, keys2, map1, map2);
            default -> throw new Exception();
        };
    }

    private static Set<String> getAllKeys(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);
        return allKeys;
    }

}
