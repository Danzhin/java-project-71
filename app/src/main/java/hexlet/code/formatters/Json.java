package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferKey;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toString(Map<String, DifferKey> differ) throws JsonProcessingException {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
                .with(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .writeValueAsString(toJson(differ))
                .replace("\r\n", "\n");
    }

    private static Map<String, Object> toJson(Map<String, DifferKey> differ) {
        return differ.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> getFormatedDifferKey(entry.getValue()),
                        (existing, _) -> existing,
                        LinkedHashMap::new
                ));
    }

    private static Map<String, Object> getFormatedDifferKey(DifferKey differKey) {
        return switch (differKey.status()) {
            case "added" -> getAddedKey(differKey);
            case "removed", "unchanged" -> getRemovedOrUnchangedKey(differKey);
            case "changed" -> getChangedKey(differKey);
            default -> throw new IllegalStateException("Unexpected value: " + differKey.status());
        };
    }

    private static Map<String, Object> getChangedKey(DifferKey differKey) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", differKey.status());
        result.put("oldValue", differKey.oldValue());
        result.put("newValue", differKey.newValue());
        return result;
    }

    private static Map<String, Object> getAddedKey(DifferKey differKey) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", differKey.status());
        result.put("value", differKey.newValue());
        return result;
    }

    private static Map<String, Object> getRemovedOrUnchangedKey(DifferKey differKey) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", differKey.status());
        result.put("value", differKey.oldValue());
        return result;
    }

}
