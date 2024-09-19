package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {

    private static String getFixturesPath(String fileName) {
        return "src/test/resources/fixtures/" + fileName;
    }

    @Test
    public void testGenerate() throws Exception {
        String expected1 = Files.readString(Paths.get(getFixturesPath("R1.txt")));
        String expected2 = Files.readString(Paths.get(getFixturesPath("R2.txt")));

        String actual1 = Differ.generate(getFixturesPath("A1.json"), getFixturesPath("B1.json"));
        assertEquals(expected1, actual1);

        String actual2 = Differ.generate(getFixturesPath("A2.yaml"), getFixturesPath("B2.yaml"));
        assertEquals(expected1, actual2);

        String actual3 = Differ.generate(getFixturesPath("A3.json"), getFixturesPath("B3.json"));
        assertEquals(expected2, actual3);
    }
}