package gr.europeandynamics.xmlmanagement.fileconvertorservice;

public interface FileConvertor {

    /**
     * Converts an input file to an output file in a different format. The
     * specific conversion logic depends on the implementing class.
     *
     * @param inputFilePath
     * @param outputFilePath
     * @return
     */
    boolean convertFile(String inputFilePath, String outputFilePath);
}
