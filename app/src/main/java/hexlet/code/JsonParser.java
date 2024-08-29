package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> parse(String data) throws Exception {
        return objectMapper.readValue(data, new TypeReference<>(){});
    }
    
}
