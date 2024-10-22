package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> toMap(String fileData, String fileExtension1) throws Exception {
        ObjectMapper objectMapper = switch (fileExtension1) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalStateException("Unexpected value: " + fileExtension1);
        };
        return objectMapper.readValue(fileData, new TypeReference<>() { });
    }

}
