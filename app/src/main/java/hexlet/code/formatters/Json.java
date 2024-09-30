package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Json {

    public static String generate(Set<String> allKeys, Set<String> keys1, Set<String> keys2, Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        Map<String, Object> change = allKeys.stream()
                .map(key -> getChange(key, keys1, keys2, map1, map2))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, _) -> existing,
                        LinkedHashMap::new));

        return toJson(change);
    }

    private static Map.Entry<String, Object> getChange(String key, Set<String> keys1, Set<String> keys2, Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        Map<String, Object> change = new LinkedHashMap<>();

        if (!keys2.contains(key)) {
            change.put("status", "removed");
            change.put("value", value1);
            return Map.entry(key, change);
        } else if (!keys1.contains(key)) {
            change.put("status", "added");
            change.put("value", value2);
            return Map.entry(key, change);
        } else if (!Objects.equals(value1, value2)) {
            change.put("status", "updated");
            change.put("oldValue", value1);
            change.put("newValue", value2);
            return Map.entry(key, change);
        }
        return null;
    }


    private static String toJson(Map<String, Object> result) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
