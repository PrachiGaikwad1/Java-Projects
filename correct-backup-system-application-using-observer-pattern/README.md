## Name: Prachi Rajesh Gaikwad

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentRecordsBackup/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentRecordsBackup/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:
Instruction: To run the program please make sure that you are under the project folder and then use the build and run command, if you are in some other directory then it will give file not found exception.

####Command: ant -buildfile studentRecordsBackup/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:
Instruction: To run the program please make sure that you are under the project folder and then use the build and run command, if you are in some other directory then it will give file not found exception. Also the input file should be stored under Project_folder/studentRecordsBackup/

####Command: ant -buildfile studentRecordsBackup/src/build.xml run -Darg0=<input_file.txt> -Darg1=<output_file.txt> -Darg2=<errorlog_file.txt> -Darg3=<DEBUG_LEVEL> -Darg4=<UPDATE_VALUE>

## Replace <fileName.txt> with real file names. For example, if the files are available in the path,
## you can run it in the following manner:

 ant -buildfile studentRecordsBackup/src/build.xml run -Darg0=bstInput.txt -Darg1=bstOutput.txt -Darg2=errorLog.txt -Darg3=1 -Darg4=1

Note: Arguments accept the absolute path of the files.
I have not used Logger Class my " -Darg3=<DEBUG_LEVEL> ---> value can be any integer "
-----------------------------------------------------------------------
## Description: 
Developed a project, using Java, to implement Backup System for Student Records


>>>>Choice of Data Structures:

List<ObserverI> observers: This list is used to store the observers interested in changes to the node. When an observer needs to be notified of a change in the node, the system iterates over this list and notifies each observer. The time complexity of adding, removing, and notifying observers is O(1) on average since ArrayList is used, which provides constant-time operations for these operations.

Map<FilterI, List<ObserverI>> filterObserverMap: This map is used to associate filters with lists of observers. Different filters may be interested in changes to the same node, and this data structure allows efficient organization of the observers based on their associated filter. When an update occurs, the system iterates over the filterObserverMap and checks each filter to determine which observers should be notified. The time complexity of adding a filter and an observer to this map is O(1) on average for HashMap. The time complexity of notifying observers is O(N), where N is the number of distinct filters. In the worst case, all observers need to be notified, leading to O(N) time complexity.

The choice of data structures in this code is appropriate for managing the relationships between nodes, filters, and observers. ArrayList and HashMap offer efficient methods for adding and retrieving elements, which are the primary operations required in this context. The time complexity analysis assumes that the hashing function used by HashMap distributes keys uniformly, and that the average number of observers per filter is relatively small, making O(N) notifications an acceptable cost.

-----------------------------------------------------------------------
## Output File Format: 

 // Inorder traversal
BST: 5:CC, 7:AA, 10:DD, 13:BB, 14:EE
Backup-1: 5:CC, 7:AA, 10:DD, 13:BB, 14:EE
Backup-2: 5:CC, 7:AA, 10:DD, 13:BB, 14:EE
Backup-3: 5:CC, 7:AA, 10:DD, 13:BB, 14:EE

// Sum of all the B-Numbers in each tree
BST: 49
Backup-1: 49
Backup-2: 49
Backup-3: 49

// Sum of all the B-Numbers after increment
BST: 54
Backup-1: 54
Backup-2: 54
Backup-3: 50

-----------------------------------------------------------------------
Date: 20 Oct, 2023 
