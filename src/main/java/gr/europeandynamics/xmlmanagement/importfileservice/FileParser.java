
package gr.europeandynamics.xmlmanagement.importfileservice;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileParser {
    
  void  readFile(String filePath) throws IOException, OutOfMemoryError, FileNotFoundException;
}
