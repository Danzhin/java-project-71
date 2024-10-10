package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferKey;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Map<String, DifferKey> differ) throws Exception {
        Map<String, Object> formattedDiffer = differ.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> formatDifferKey(entry.getValue()),
                        (existing, _) -> existing,
                        LinkedHashMap::new
                ));


        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
                .with(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .writeValueAsString(formattedDiffer)
                .replace("\r\n", "\n");
    }

    private static Map<String, Object> formatDifferKey(DifferKey differKey) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", differKey.status());
        switch (differKey.status()) {
            case "changed" -> {
                result.put("oldValue", differKey.oldValue());
                result.put("newValue", differKey.newValue());
            }
            case "added" -> result.put("value", differKey.newValue());
            case "removed", "unchanged" -> result.put("value", differKey.oldValue());
        }
        return result;
    }
}