package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Stylish {

    public static String generate(Set<String> allKeys,  Set<String> keys1, Set<String> keys2,
                                  Map<String, Object> map1, Map<String, Object> map2) {
        return allKeys.stream()
                .map(key -> getChange(key, keys1, keys2, map1, map2))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String getChange(String key, Set<String> keys1, Set<String> keys2,
                                    Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        if (!keys2.contains(key)) {
            return "  - " + key + ": " + value1;
        } else if (!keys1.contains(key)) {
            return "  + " + key + ": " + value2;
        } else if (Objects.equals(value1, value2)) {
            return "    " + key + ": " + value1;
        } else {
            return "  - " + key + ": " + value1 + "\n" + "  + " + key + ": " + value2;
        }
    }

}
