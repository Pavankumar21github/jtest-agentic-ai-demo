package examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ExampleLogicTest
{
    @TempDir
    Path tempDir;

    @Test
    void appendString_writesDataToFile() throws IOException
    {
        ExampleLogic logic = new ExampleLogic();
        Path file = tempDir.resolve("test.txt");

        logic.appendMessageToFile(file.toFile(), "Hello");

        String content = Files.readString(file);
        assertTrue(content.contains("Hello"));
    }

    @Test
    void appendString_appendsMultipleLines() throws IOException {
        ExampleLogic logic = new ExampleLogic();
        Path file = tempDir.resolve("multi.txt");

        logic.appendMessageToFile(file.toFile(), "First");
        logic.appendMessageToFile(file.toFile(), "Second");

        String content = Files.readString(file);
        assertTrue(content.contains("First"));
        assertTrue(content.contains("Second"));
        long lines = Files.readAllLines(file).size();
        assertTrue(lines >= 2);
    }
}
