package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toString(List<Map<String, Object>> differ) throws JsonProcessingException {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
                .writeValueAsString(toJson(differ))
                .replace("\r\n", "\n");
    }

    private static Map<String, Object> toJson(List<Map<String, Object>> differ) {
        return differ.stream()
                .collect(Collectors.toMap(
                        keyData -> (String) keyData.get("key"),
                        Json::formatKeyData,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private static Map<String, Object> formatKeyData(Map<String, Object> keyData) {
        String status = (String) keyData.get("status");
        Object value1 = keyData.get("value1");
        Object value2 = keyData.get("value2");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", status);
        switch (status) {
            case "removed" -> {
                result.put("status", status);
                result.put("value", value1);
            }
            case "changed" -> {
                result.put("oldValue", value1);
                result.put("newValue", value2);
            }
            default -> {
                result.put("status", status);
                result.put("value", value2);
            }
        }
        return result;
    }

}

