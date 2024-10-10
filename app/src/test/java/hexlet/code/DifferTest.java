package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String FIX_PATH = "src/test/resources/fixtures/";

    private static String expected;
    private static String actual;

    @Test
    public void testGenerateJsonStylish() throws Exception {
        expected = FileReader.readFile(FIX_PATH + "E1.txt");
        actual =  Differ.generate(FIX_PATH + "A1.json", FIX_PATH + "B1.json", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYamlStylish() throws Exception {
        expected = FileReader.readFile(FIX_PATH + "E1.txt");
        actual =  Differ.generate(FIX_PATH + "A1.yaml", FIX_PATH + "B1.yaml", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateJsonPlain() throws Exception {
        expected = FileReader.readFile(FIX_PATH + "E2.txt");
        actual =  Differ.generate(FIX_PATH + "A1.json", FIX_PATH + "B1.json", "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYamlPlain() throws Exception {
        expected = FileReader.readFile(FIX_PATH + "E2.txt");
        actual =  Differ.generate(FIX_PATH + "A1.yaml", FIX_PATH + "B1.yaml", "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateJsonJson() throws Exception {
        expected = FileReader.readFile(FIX_PATH + "E3.txt");
        actual =  Differ.generate(FIX_PATH + "A1.json", FIX_PATH + "B1.json", "json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYamlJson() throws Exception {
        expected = FileReader.readFile(FIX_PATH + "E3.txt");
        actual =  Differ.generate(FIX_PATH + "A1.yaml", FIX_PATH + "B1.yaml", "json");
        assertEquals(expected, actual);
    }

}
