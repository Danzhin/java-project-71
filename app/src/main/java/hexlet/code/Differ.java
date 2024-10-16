package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.fileToMap(filePath1);
        Map<String, Object> map2 = Parser.fileToMap(filePath2);
        Map<String, DifferKey> differ = getDiffer(map1, map2);
        return Formatter.toFormat(differ, format);
    }

    public static Map<String, DifferKey> getDiffer(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = getKeys(map1);
        Set<String> keys2 = getKeys(map2);
        Set<String> allKeys = getAllKeys(keys1, keys2);
        return allKeys.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> DifferKey.getDifferKey(key, keys1, keys2, map1, map2),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public static Set<String> getKeys(Map<String, Object> map) {
        return new TreeSet<>(map.keySet());
    }

    public static Set<String> getAllKeys(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);
        return allKeys;
    }

}
