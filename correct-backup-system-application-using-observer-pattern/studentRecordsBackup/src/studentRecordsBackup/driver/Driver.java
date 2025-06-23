package studentRecordsBackup.driver;

import studentRecordsBackup.util.BSTBuilder;
import studentRecordsBackup.util.FileProcessor;

public class Driver {
    public static void main(String[] args) {
        FileProcessor errorFileProcessor = new FileProcessor();

        if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
                || args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

            System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
            errorFileProcessor.writeErrorToFile(args[2], "Error: Incorrect number of arguments. Program accepts 5 arguments.");
            System.exit(0);
        }

        String bstInputFile = args[0];
        String bstOutputFile = args[1];
        String errorLogFile = args[2];
        int DEBUG_LEVEL = 0;
        int UPDATE_VALUE = 0;
        try{
            DEBUG_LEVEL = Integer.parseInt(args[3]);
            UPDATE_VALUE = Integer.parseInt(args[4]);
        }catch (NumberFormatException nfe) {
            System.err.println("Invalid UPDATE_VALUE/DEBUG_LEVEL - NumberFormatException");
            nfe.printStackTrace();
            errorFileProcessor.writeErrorToFile(errorLogFile, "Invalid UPDATE_VALUE/DEBUG_LEVEL - NumberFormatException");
            System.exit(0); 
        }finally{}

        try {
            BSTBuilder bstBuilder = new BSTBuilder(errorLogFile);
            bstBuilder.buildBSTFromFile(bstInputFile, args);
            bstBuilder.writeOutputToFileAndStdout(bstOutputFile, UPDATE_VALUE, args);
        } catch (Exception e) {
            System.err.println("Error reading or writing files. " + e.getMessage());
            e.printStackTrace();
            errorFileProcessor.writeErrorToFile(errorLogFile, "Error reading or writing files. " + e.getMessage());
            System.exit(0); 
        }
        finally{
                errorFileProcessor.close();
        }
    }        
}
