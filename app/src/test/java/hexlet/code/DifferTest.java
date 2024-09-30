package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String FIX_PATH = "src/test/resources/fixtures/";

    @Test
    public void testGenerate() throws Exception {
        String expected1 = FileReader.readFile(FIX_PATH + "E1.txt");
        String expected2 = FileReader.readFile(FIX_PATH + "E2.txt");

        String filePathA = FIX_PATH + "A1.json";
        String filePathB = FIX_PATH + "B1.json";
        assertEquals(expected1, Differ.generate(filePathA, filePathB, "stylish"));
        assertEquals(expected2, Differ.generate(filePathA, filePathB, "plain"));

        filePathB = FIX_PATH + "B1.yaml";
        assertEquals(expected1, Differ.generate(filePathA, filePathB, "stylish"));
        assertEquals(expected2, Differ.generate(filePathA, filePathB, "plain"));

        filePathA = FIX_PATH + "A1.yaml";
        assertEquals(expected1, Differ.generate(filePathA, filePathB, "stylish"));
        assertEquals(expected2, Differ.generate(filePathA, filePathB, "plain"));
    }

}