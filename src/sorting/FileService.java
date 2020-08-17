package sorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    public static void save(String filename, String text) {
        try (FileWriter writer = new FileWriter(new File(filename))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
