package hexlet.code;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Comparator {

    public static List<Map<String, Object>> getDiffer(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys1 = getKeys(map1);
        Set<String> keys2 = getKeys(map2);
        Set<String> allKeys = getAllKeys(keys1, keys2);
        return allKeys.stream()
                .map(key -> getKeyData(key, keys1, keys2, map1.get(key), map2.get(key)))
                .collect(Collectors.toList());
    }

    private static Set<String> getKeys(Map<String, Object> map) {
        return new TreeSet<>(map.keySet());
    }

    private static Set<String> getAllKeys(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);
        return allKeys;
    }

    private static Map<String, Object> getKeyData(String key, Set<String> keys1, Set<String> keys2,
                                                  Object value1, Object value2) {
        Map<String, Object> keyData = new LinkedHashMap<>();
        keyData.put("key", key);
        keyData.put("status", getStatus(key, keys1, keys2, value1, value2));
        keyData.put("value1", value1);
        keyData.put("value2", value2);
        return keyData;
    }

    private static String getStatus(String key, Set<String> keys1, Set<String> keys2, Object value1, Object value2) {
        return !keys2.contains(key) ? "removed" : !keys1.contains(key) ? "added"
                : Objects.equals(value1, value2) ? "unchanged" : "changed";
    }

}
