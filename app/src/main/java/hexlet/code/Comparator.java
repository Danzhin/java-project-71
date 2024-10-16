package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Comparator {

    public static Map<String, DifferKey> getDiffer(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> setOfKeys1 = getSetOfKeys(map1);
        Set<String> setOfKeys2 = getSetOfKeys(map2);
        Set<String> setOfAllKeys = getSetOfKeys(setOfKeys1, setOfKeys2);
        return setOfAllKeys.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> DifferKey.getDifferKey(key, setOfKeys1, setOfKeys2, map1, map2),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public static Set<String> getSetOfKeys(Map<String, Object> map) {
        return new TreeSet<>(map.keySet());
    }

    public static Set<String> getSetOfKeys(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);
        return allKeys;
    }

}
