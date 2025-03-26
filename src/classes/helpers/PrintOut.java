package classes.helpers;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for printing content to a file.
 */
public class PrintOut {

    /**
     * Writes the specified content to a file with the given file name.
     *
     * @param content  The content to write to the file.
     * @param fileName The name of the file to create or overwrite.
     * @return {@code true} if the file was written successfully, {@code false} otherwise.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static boolean toFile(String content, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName + ".txt")) {
            writer.write(content);
            return true;
        } catch (Exception e) {
            System.out.println("Error writing Sale's Representatives pay stub file.");
            throw e;
        }
    }
}