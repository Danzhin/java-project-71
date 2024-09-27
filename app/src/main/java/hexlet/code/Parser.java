package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> toMap(String fileData, String extension) throws Exception {
        return getObjectMapper(extension).readValue(fileData, new TypeReference<>() { });
    }

    private static ObjectMapper getObjectMapper(String extension) throws Exception {
        return switch (extension) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new Exception();
        };
    }

}
