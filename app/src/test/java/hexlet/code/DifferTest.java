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
        String expected = Files.readString(Paths.get(getFixturesPath("R.txt")));
        String actual = Differ.generate(getFixturesPath("A1.json"), getFixturesPath("B1.json"));
        assertEquals(expected, actual);
    }
}