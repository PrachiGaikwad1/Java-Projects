package studentRecordsBackup.util;

public class BSTBuilder {
    public BST mainBST;
    public BST backup1BST;
    public BST backup2BST;
    public BST backup3BST;
    private FileProcessor errorFileProcessor;

    public BSTBuilder(String errorLogFile) {
        mainBST = new BST();
        backup1BST = new BST();
        backup2BST = new BST();
        backup3BST = new BST();
        errorFileProcessor = new FileProcessor();
    }

    /**
     * Builds Binary Search Trees (BSTs) from the input file.
     * Reads each line from the input file, parses the data, and inserts 
     * it into the main BST and its backups.
     * It also registers observers for the main BST node.
     *
     * @param filePath The path to the input file.
     */
    public void buildBSTFromFile(String filePath, String[] args) {
        FileProcessor fileProcessor = new FileProcessor(filePath, "read", args);
        try {
            String line;
    
            while ((line = fileProcessor.readLine()) != null) {
                line = line.replaceAll(";$", "").trim();
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int bNumber = Integer.parseInt(parts[0]);
                    String firstName = parts[1];
                    mainBST.insert(bNumber, firstName);
                    backup1BST.insert(bNumber, firstName);
                    backup2BST.insert(bNumber, firstName);
                    backup3BST.insert(bNumber, firstName);
    
                    Node mainNode = mainBST.getNode(bNumber);
                    FilterI filterAll = new FilterAllImpl(); 
                    FilterI filterPrime = new FilterPrimeImpl(); 
                    mainNode.registerObserver(backup1BST.getNode(bNumber), filterAll);
                    mainNode.registerObserver(backup2BST.getNode(bNumber), filterAll);
                    mainNode.registerObserver(backup3BST.getNode(bNumber), filterPrime);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input - NumberFormatException: " + e.getMessage());
            e.printStackTrace();
            errorFileProcessor.writeErrorToFile(args[2], "Invalid input - NumberFormatException: " + e.getMessage());
            System.exit(0); 
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            errorFileProcessor.writeErrorToFile(args[2], "An error occurred: " + e.getMessage());
            System.exit(0); 
        } finally {
            fileProcessor.close();
        }
    }

    public void writeOutputToFileAndStdout(String outputPath, int updateValue, String[] args){
        FileProcessor fileProcessor = new FileProcessor(outputPath, "write",args);
        try{
            String mainInOrder = mainBST.inorderTraversal();
            String backup1InOrder = backup1BST.inorderTraversal();
            String backup2InOrder = backup2BST.inorderTraversal();
            String backup3InOrder = backup3BST.inorderTraversal();
            String sumOfBNumbers = mainBST.printSumOfBNumbers();
            String incrementBST = incrementMainBST(updateValue,outputPath);

            fileProcessor.writeLine("// Inorder traversal");
            fileProcessor.writeLine("BST: " + mainInOrder);
            fileProcessor.writeLine("Backup-1: " + backup1InOrder);
            fileProcessor.writeLine("Backup-2: " + backup2InOrder);
            fileProcessor.writeLine("Backup-3: " + backup3InOrder);
            fileProcessor.writeLine("");

            fileProcessor.writeLine("// Sum of all the B-Numbers in each tree" + sumOfBNumbers);
            fileProcessor.writeLine("");

            fileProcessor.writeLine("// Sum of all the B-Numbers after increment" + incrementBST);

            System.out.println("// Inorder traversal");
            System.out.println("BST: " + mainInOrder);
            System.out.println("Backup-1: " + backup1InOrder);
            System.out.println("Backup-2: " + backup2InOrder);
            System.out.println("Backup-3: " + backup3InOrder);
            System.out.println("\n// Sum of all the B-Numbers in each tree" + sumOfBNumbers);
            System.out.println("\n// Sum of all the B-Numbers after increment" + incrementBST);
        } catch(Exception e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            errorFileProcessor.writeErrorToFile(args[2],"An error occurred while writing to the file: \" + e.getMessage()");
            e.printStackTrace();
            System.exit(0);
        } finally{
            fileProcessor.close();
        }
    }
 
    /**
     * @param incrementValue The value to increment the nodes in the main BST.
     * @param outputPath The path to the output file.
     * @return A formatted string containing the sum of B-Numbers for the main and backup BSTs.
     */
    public String incrementMainBST(int incrementValue, String outputPath) {
        try {
            mainBST.updateNodeValues(incrementValue);
            
            StringBuilder result = new StringBuilder();
    
            int mainSum = mainBST.calculateSum1();
            int backup1Sum = backup1BST.calculateSum1();
            int backup2Sum = backup2BST.calculateSum1();
            int backup3Sum = backup3BST.calculateSum1();
            
            result.append("\nBST: " + mainSum + "\n");
            result.append("Backup-1: " + backup1Sum + "\n");
            result.append("Backup-2: " + backup2Sum + "\n");
            result.append("Backup-3: " + backup3Sum);
            
            return result.toString();
        } catch (Exception e) {
            System.err.println("An error occurred during BST increment: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
            return "Error: Unable to increment BST.";
        } finally{} 
    }   
}
