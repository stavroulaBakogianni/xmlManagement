package gr.europeandynamics.xmlmanagement.importfileservice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TXTParser implements FileParser {

    @Override
    public void readFile(String filePath) throws IOException, OutOfMemoryError, FileNotFoundException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Επεξεργασία κάθε γραμμής εδώ
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());

        }
    }
}
