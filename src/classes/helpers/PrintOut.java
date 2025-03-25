package classes.helpers;

import java.io.FileWriter;
import java.io.IOException;

public class PrintOut {

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
