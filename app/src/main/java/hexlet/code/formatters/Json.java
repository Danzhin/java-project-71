package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferKey;

import java.util.LinkedHashMap;
import java.util.Map;

import java.util.stream.Collectors;

public class Json {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Map<String, DifferKey> differ) throws Exception {
        Map<String, Object> formattedMap = differ.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> formatDifferKey(entry.getValue()),
                        (existing, _) -> existing,
                        LinkedHashMap::new));

        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(formattedMap);
    }

    private static Map<String, Object> formatDifferKey(DifferKey differKey) {
        Map<String, Object> formattedKey = new LinkedHashMap<>();
        formattedKey.put("status", differKey.status());
        formattedKey.put("oldValue", differKey.oldValue());
        formattedKey.put("newValue", differKey.newValue());
        return formattedKey;
    }

}