package CST8110;

import java.util.Random;
import java.util.Scanner;

// Game class and main method
// Game class creates the game 
public class Game {
    private String playerName;
    private int player;
    private Scanner input;
    private Random rand;
    private File pdf, docx, xlsx, java;
    
    // Game constructor
    // Includes the rules for the range of files of each type to start the game. The number of
    // files is selected randomly.
    public Game() {
        input = new Scanner(System.in);
        rand = new Random();
        // Get Player 1's information
        player = 1;
        System.out.println("Please enter your name: ");
        playerName = input.next();
        
        // Get 3, 5, 7, or 9 from Random number generator
        int roll = rand.nextInt(7)+3;
        if (roll % 2 == 0) {
            roll++;
        }
        
        // Assign number of files of different types
        pdf = new File(roll);
        docx = new File(roll + 2);
        xlsx = new File(roll + 4);
        java = new File(roll + 6);
        
        System.out.printf("%nWelcome to the Game%n");
        System.out.printf("You'll need to first pick a file type%n");
        System.out.printf("Then select how many files you wish to remove%n");
    }
    
    // Displays the file types and number of files currently available for each type.
    public void displayGame() {
        System.out.printf("%nPDF  DOCX  XLSX  JAVA%n");
        System.out.printf("%3d %5d %5d %5d%n%n", pdf.getNumFiles(), docx.getNumFiles(), 
                xlsx.getNumFiles(), java.getNumFiles());
    }
    
    // Player 1 and Computer (Player 2) take turns removing files of the type they choose.
    public void playGame() {
        displayGame();
        File selectedFile = null;
        int numFilesToRemove = 0;
        
        // 2.e.iv If it is player 1's turn have them select which type of file to remove and how many.
        if (player == 1) {
            do {
                System.out.printf("%s, select a file type:%n", playerName);
                System.out.println("Press 1 for PDF");
                System.out.println("Press 2 for DOCX");
                System.out.println("Press 3 for XLSX");
                System.out.println("Press 4 for JAVA");
                int fileType = input.nextInt();
                
                // Process their selection
                switch(fileType) {
                case 1:
                    selectedFile = pdf;
                    break;
                case 2:
                    selectedFile = docx;
                    break;
                case 3:
                    selectedFile = xlsx;
                    break;
                case 4:
                    selectedFile = java;
                    break;
                default:
                    System.out.println("Pressed a wrong number");
                    File temp = new File(0);
                    selectedFile = temp;
                    continue;
                }
            } while(selectedFile.isEmpty());
            //2.e.iv.2
            System.out.print("Enter the number of files to remove: ");
            numFilesToRemove = input.nextInt();
        }
        
        //2.e.v. If it is player 2(Computer)'s turn make the selections and show them on the screen
        else if (player == 2) {
            System.out.print("Computer, select a file type: ");
            do {
                int randFileType = rand.nextInt(4);
                switch(randFileType) {
                case 0:
                    selectedFile = pdf;
                    if (selectedFile.isEmpty()) {
                        continue;
                    }
                    else {
                        System.out.println("PDF");;
                    }
                    break;
                case 1:
                    selectedFile = docx;
                    if (selectedFile.isEmpty()) {
                        continue;
                    }
                    else {
                        System.out.println("DOCX");;
                    }
                    break;
                case 2:
                    selectedFile = xlsx;
                    if (selectedFile.isEmpty()) {
                        continue;
                    }
                    else {
                        System.out.println("XLSX");;
                    }
                    break;
                case 3:
                    selectedFile = java;
                    if (selectedFile.isEmpty()) {
                        continue;
                    }
                    else {
                        System.out.println("JAVA");;
                    }
                    break;
                }
            } while(selectedFile.isEmpty());
            //2.e.v.3.
            System.out.print("Computer, choose number of files to remove: ");
            //2.e.v.4.
            if (selectedFile.getNumFiles()==1) {
                numFilesToRemove = 1;
            }
            //2.e.v.5
            else {
                numFilesToRemove = rand.nextInt(selectedFile.getNumFiles()/2) + 1;
            }
            System.out.println(numFilesToRemove);
        }
        
        //2.e.vi. Remove the number of files selected by the user or computer.
        boolean itWorked = selectedFile.removeFiles(numFilesToRemove);
        if (!itWorked) {
            return;
        }
        
        //2.e.vii. Swap players.  
        if (player == 1) {
            player = 2;
        }
        else {
            player = 1;
        }     
    }
    
    // 2.f. Determine if there is a winner (no files remaining of any type)
    public int determineWinner() {
        int winner = 0;
        int allFiles = pdf.getNumFiles() + docx.getNumFiles() + xlsx.getNumFiles()
                       + java.getNumFiles();
        if (allFiles == 0) {
            winner = player;
            if (player == 1) {
                System.out.printf("%s Wins%n", playerName);
            }
            else {
                System.out.println("Computer Wins!");
            }
        }
        return winner;
    }
    
    // Main method. The game is created here.
    public static void main(String[] args) {
        Game game = new Game();
        do {
            game.playGame();
        }while(game.determineWinner()==0);
        
        System.out.println("Thanks for playing!");
    }
    
}
