package studentRecordsBackup.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileProcessor class for common file operations
 */ 
public class FileProcessor {
    private BufferedReader reader;
    private BufferedWriter writer;
    String errorLogFile = "";

    public FileProcessor() {}

    public FileProcessor(String filePath, String mode,String[] args) {
        try {
            if (mode.equals("read")) {
                reader = new BufferedReader(new FileReader(filePath));
            } else if (mode.equals("write")) {
                writer = new BufferedWriter(new FileWriter(filePath));
            } else {
                throw new IllegalArgumentException("Invalid mode specified. Use 'read' or 'write'.");
            }
        } catch (IOException e) {
            System.err.println("Error while initializing FileProcessor: File Not Found - " + e.getMessage());
            writeErrorToFile(args[2], "Error while initializing FileProcessor: File Not Found - " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } finally{}
    }

    /**
     * Read a line from the input file
     */ 
    public String readLine() {
        try {
            if (reader != null) {
                return reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error while reading a line from the input file: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } finally{}
        return null;
    }
    
    /**
     * @params Write a line to the output file
     */ 
    public void writeLine(String line) {
        try {
            if (writer != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error while writing a line to the output file: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } finally{}
    }

    /**
     * Close the file
     */ 
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Error while closing the file: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } finally{}
    }

    @Override
    public String toString() {
        String mode = " ";
        return "FileProcessor [mode=" + mode + "]";
    }

    public void writeErrorToFile(String filename, String msg) {
        try {
            BufferedWriter errorWriter = new BufferedWriter(new FileWriter(filename));
            errorWriter.write(msg);
            errorWriter.newLine();
            errorWriter.close();
        } catch (IOException e) {
            System.err.println("Error while writing an error message to the error file: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } finally{}
    }    
}
