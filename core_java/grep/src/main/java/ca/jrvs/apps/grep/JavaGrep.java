package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     *
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Transverse the directory and return all files
     * @param rootDir input directory
     * @return files under root directory.
     */
    List<File> listFiles(String rootDir) throws IOException;

    /**
     * Read a file and return all the lines
     *
     * @param inputFile
     * @return lines
     * @throws IllegalArgumentException if a given inputfile is not a file
     */
    List<String> readLines(File inputFile) throws IOException;

    /**
     * Check if a line contains the regex pattern passed
     * @param line input string
     * @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     *
     * @param lines matched line
     * @throws IOException if write failed
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);
    String getRegex();
    void setRegex(String regex);
    String getOutFile();
    void setOutFile(String outFile);
}
