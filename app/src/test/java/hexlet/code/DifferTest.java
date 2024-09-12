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
        String fixtures1 = getFixturesPath("file1.json");
        String fixtures2 = getFixturesPath("file2.json");
        String fixtures3 = getFixturesPath("result.txt");

        String expected = Files.readString(Paths.get(fixtures3)).trim();
        String actual = Differ.generate(fixtures1, fixtures2);

        assertEquals(expected, actual);
    }
}