package CST8110;

// 1. File class. There is a number of files as well as methods to get and set file numbers. 
// There are also checks to determine if there are any files left, and a method to remove files.
public class File {
    // Number of files of a given type. 
    private int numFiles;
    
    // Constructor - must pass in the number of files of type File.
    public File(int numFiles) {
        this.numFiles = numFiles;
    }
    
    // Getter method, to provide access to the number of files from outside the class.
    public int getNumFiles() {
        return numFiles;
    }
    
    // Check to see if the number of files is zero.
    public boolean isEmpty() {
        return (numFiles == 0);
    }
    
    // Remove files if they are between 1 and half the number of files available.
    public boolean removeFiles(int filesToRemove) {
        if (filesToRemove < 1 ) {
            System.out.println("Sorry, you must remove at least one file!");
            return false;
        }
        
        if (numFiles > 1 && filesToRemove > numFiles / 2 ) {
            System.out.println("Sorry, that's too many files!");
            return false;
        }
        else {
            numFiles -= filesToRemove;
            return true;
        }
    }
}
