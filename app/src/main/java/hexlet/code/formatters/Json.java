package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Json {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        Set<String> keys1 = Key.getKeys(map1);
        Set<String> keys2 = Key.getKeys(map2);
        Set<String> allKeys = Key.getAllKeys(keys1, keys2);
        Map<String, Object> change = allKeys.stream()
                .map(key -> getChange(key, keys1, keys2, map1, map2))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, _) -> existing,
                        LinkedHashMap::new));

        return OBJECT_MAPPER.findAndRegisterModules()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(change);
    }

    private static Map.Entry<String, Object> getChange(String key, Set<String> keys1, Set<String> keys2,
                                                       Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);
        String keyFormat = Key.getFormat(key, keys1, keys2, value1, value2);
        return getFormatedKey(key, value1, value2, keyFormat);
    }

    private static Map.Entry<String, Object> getFormatedKey(String key, Object value1, Object value2, String keyFormat) {
        Map<String, Object> change = new LinkedHashMap<>();
        switch (keyFormat) {
            case "removed" -> {
                change.put("status", "removed");
                change.put("value", value1);
                return Map.entry(key, change);
            }
            case "added" -> {
                change.put("status", "added");
                change.put("value", value2);
                return Map.entry(key, change);
            }
            case "changed" -> {
                change.put("status", "updated");
                change.put("oldValue", value1);
                change.put("newValue", value2);
                return Map.entry(key, change);
            }
        };
        return null;
    }

}
