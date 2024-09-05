package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        String data1 = Files.readString(Paths.get(filePath1));
        String data2 = Files.readString(Paths.get(filePath2));

        Map<String, Object> json1 = Utils.OBJECT_MAPPER.readValue(data1, new TypeReference<>() { });
        Map<String, Object> json2 = Utils.OBJECT_MAPPER.readValue(data2, new TypeReference<>() { });

        Set<String> keys1 = new TreeSet<>(json1.keySet());
        Set<String> keys2 = new TreeSet<>(json2.keySet());

        Set<String> allKeys = new TreeSet<>(keys1);
        allKeys.addAll(keys2);

        return allKeys.stream()
                .map(key -> {
                    if (!keys2.contains(key)) {
                        return formatRemovedKey(key, json1.get(key));
                    } else if (!keys1.contains(key)) {
                        return formatAddedKey(key, json2.get(key));
                    } else if (json1.get(key).equals(json2.get(key))) {
                        return formatUnchangedKey(key, json1.get(key));
                    } else {
                        return formatChangedKey(key, json1.get(key), json2.get(key));
                    }
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String formatRemovedKey(String key, Object value) {
        return "  - " + key + ": " + value;
    }

    private static String formatAddedKey(String key, Object value) {
        return "  + " + key + ": " + value;
    }

    private static String formatChangedKey(String key, Object oldValue, Object newValue) {
        return "  - " + key + ": " + oldValue + "\n" + "  + " + key + ": " + newValue;
    }

    private static String formatUnchangedKey(String key, Object value) {
        return "    " + key + ": " + value;
    }

}
