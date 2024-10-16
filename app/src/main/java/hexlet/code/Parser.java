package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> fileToMap(String filePath) throws Exception {
        String fileData = FileReader.readFile(filePath);
        String extension = getExtension(filePath);
        ObjectMapper objectMapper = getObjectMapper(extension);
        return objectMapper.readValue(fileData, new TypeReference<>() { });
    }

    public static String getExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }

    private static ObjectMapper getObjectMapper(String extension) {
        return switch (extension) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalStateException("Unexpected value: " + extension);
        };
    }

}
