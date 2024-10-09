package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Comparator {

    public static DifferKey getDifferKey(String key, Set<String> keys1, Set<String> keys2,
                                          Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);
        if (!keys2.contains(key)) {
            return new DifferKey("removed", value1, null);
        } else if (!keys1.contains(key)) {
            return new DifferKey("added", null, value2);
        } else if (Objects.equals(value1, value2)) {
            return new DifferKey("unchanged", value1, value2);
        } else {
            return new DifferKey("changed", value1, value2);
        }
    }

}
