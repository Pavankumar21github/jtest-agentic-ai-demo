package examples;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.ReentrantLock;

public class ExampleLogic
{
    private final ReentrantLock lock = new ReentrantLock();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String MSG_SEPARATOR = ": ";
    private static final String NEW_LINE = System.lineSeparator();

    public void appendMessageToFile(File file, String message) throws IOException {
        lock.lock();
        try {
            String datePrefix = LocalDateTime.now().format(DATE_FORMAT);
            String line = datePrefix + MSG_SEPARATOR + message + NEW_LINE;
            Path path = file.toPath();
            try (Writer writer = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                writer.write(line);
            }
        } finally {
            lock.unlock();
        }
    }
}
