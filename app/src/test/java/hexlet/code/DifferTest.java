package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    private static String getFixturesPath(String fileName) {
        return "src/test/resources/fixtures/" + fileName;
    }

    @Test
    public void testGenerate() throws Exception {
        String expected1 = FileReader.toString(getFixturesPath("R1.txt"));
        String actual1 = Differ.generate(getFixturesPath("A1.json"), getFixturesPath("B1.json"));
        String actual2 = Differ.generate(getFixturesPath("A1.yaml"), getFixturesPath("B1.yaml"));

        assertEquals(expected1, actual1);
        assertEquals(expected1, actual2);

        String expected2 = FileReader.toString(getFixturesPath("R2.txt"));
        String actual3 = Differ.generate(getFixturesPath("A2.json"), getFixturesPath("B2.json"));
        String actual4 = Differ.generate(getFixturesPath("A2.yaml"), getFixturesPath("B2.yaml"));

        assertEquals(expected2, actual3);
        assertEquals(expected2, actual4);

        assertThrows(Exception.class, () -> {
            Differ.generate(getFixturesPath("C1.json"), getFixturesPath("B1.json"));
        });
        assertThrows(Exception.class, () -> {
            Differ.generate(getFixturesPath("A1.json"), getFixturesPath("B1.yaml"));
        });
        assertThrows(Exception.class, () -> {
            Differ.generate(getFixturesPath("A1.txt"), getFixturesPath("B1.txt"));
        });
        assertThrows(Exception.class, () -> {
            Differ.generate(getFixturesPath("A3.json"), getFixturesPath("B1.json"));
        });

    }
}