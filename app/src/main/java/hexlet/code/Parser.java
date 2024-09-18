package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> jsonToMap(String data) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(data, new TypeReference<>() { });
    }
    
}
