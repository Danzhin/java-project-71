package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String FIX_PATH = "src/test/resources/fixtures/";

    @Test
    public void testGenerate1() throws Exception {
        String expected = FileReader.readFile(FIX_PATH + "R1.txt");

        String filePathA = FIX_PATH + "A1.json";
        String filePathB = FIX_PATH + "B1.json";
        assertEquals(expected, Differ.generate(filePathA, filePathB, "stylish"));

        filePathB = FIX_PATH + "B1.yaml";
        assertEquals(expected, Differ.generate(filePathA, filePathB, "stylish"));

        filePathA = FIX_PATH + "A1.yaml";
        assertEquals(expected, Differ.generate(filePathA, filePathB, "stylish"));
    }

    @Test
    public void testGenerate2() throws Exception {
        String expected = FileReader.readFile(FIX_PATH + "R2.txt");

        String filePathA = FIX_PATH + "A1.json";
        String filePathB = FIX_PATH + "B1.json";
        assertEquals(expected, Differ.generate(filePathA, filePathB, "plain"));

        filePathB = FIX_PATH + "B1.yaml";
        assertEquals(expected, Differ.generate(filePathA, filePathB, "plain"));

        filePathA = FIX_PATH + "A1.yaml";
        assertEquals(expected, Differ.generate(filePathA, filePathB, "plain"));
    }

}