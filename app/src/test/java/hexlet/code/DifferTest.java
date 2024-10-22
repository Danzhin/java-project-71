package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String FIX_PATH = "src/test/resources/fixtures/";

    public static String readFile(String filePath) throws Exception {
        return Files.readString(Paths.get(filePath));
    }

    @Test
    public void testGenerateJsonStylish() throws Exception {
        String expected = readFile(FIX_PATH + "E1.txt");
        String actual =  Differ.generate(FIX_PATH + "A1.json", FIX_PATH + "B1.json", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYamlStylish() throws Exception {
        String expected = readFile(FIX_PATH + "E1.txt");
        String actual =  Differ.generate(FIX_PATH + "A1.yaml", FIX_PATH + "B1.yaml", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateJsonPlain() throws Exception {
        String expected = readFile(FIX_PATH + "E2.txt");
        String actual =  Differ.generate(FIX_PATH + "A1.json", FIX_PATH + "B1.json", "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYamlPlain() throws Exception {
        String expected = readFile(FIX_PATH + "E2.txt");
        String actual =  Differ.generate(FIX_PATH + "A1.yaml", FIX_PATH + "B1.yaml", "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateJsonJson() throws Exception {
        String expected = readFile(FIX_PATH + "E3.txt");
        String actual =  Differ.generate(FIX_PATH + "A1.json", FIX_PATH + "B1.json", "json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYamlJson() throws Exception {
        String expected = readFile(FIX_PATH + "E3.txt");
        String actual =  Differ.generate(FIX_PATH + "A1.yaml", FIX_PATH + "B1.yaml", "json");
        assertEquals(expected, actual);
    }

}
