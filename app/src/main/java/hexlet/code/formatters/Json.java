package hexlet.code.formatters;

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

        Map<String, Object> differ = allKeys.stream()
                .map(key -> getFormatedKey(key, keys1, keys2, map1, map2))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, _) -> existing,
                        LinkedHashMap::new));

        return OBJECT_MAPPER.findAndRegisterModules()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(differ);
    }

    private static Map.Entry<String, Object> getFormatedKey(String key, Set<String> keys1, Set<String> keys2,
                                                       Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);
        String keyFormat = Key.getFormat(key, keys1, keys2, value1, value2);
        return switch (keyFormat) {
            case "removed" -> getRemovedKey(key, value1);
            case "added" -> getAddedKey(key, value2);
            case "changed" -> getChangedKey(key, value1, value2);
            case "unchanged" -> null;
            default -> throw new IllegalStateException("Unexpected value: " + keyFormat);
        };
    }

    private static Map.Entry<String, Object> getRemovedKey(String key, Object value) {
        Map<String, Object> formatedKey = new LinkedHashMap<>();
        formatedKey.put("status", "removed");
        formatedKey.put("value", value);
        return Map.entry(key, formatedKey);
    }

    private static Map.Entry<String, Object> getAddedKey(String key, Object value) {
        Map<String, Object> formatedKey = new LinkedHashMap<>();
        formatedKey.put("status", "added");
        formatedKey.put("value", value);
        return Map.entry(key, formatedKey);
    }

    private static Map.Entry<String, Object> getChangedKey(String key, Object value1, Object value2) {
        Map<String, Object> formatedKey = new LinkedHashMap<>();
        formatedKey.put("status", "updated");
        formatedKey.put("oldValue", value1);
        formatedKey.put("newValue", value2);
        return Map.entry(key, formatedKey);
    }

}
