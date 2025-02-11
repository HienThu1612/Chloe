/*
 * Class: CMSC203 
 * Instructor: Grigoriy Grinberg
 * Description: This program tests a user's ESP (extrasensory perception) skills by asking them to guess a randomly chosen color from a list.
 * Due: 02/10/2025
 * Platform/compiler: CodeHS Java
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. I have not given my code to any student.
 * Print your Name here: Ngoc Nguyen
 */

import java.io.File; // Allows working with file
import java.io.FileWriter; // Enables writing data to a file.
import java.io.IOException; // Handles exceptions related to file input/output operations.
import java.util.Random; // Generates random numbers
import java.util.Scanner; // Reads user input and file data

public class ESPGame {
    public static void main(String[] args) {
        
        final int COLORS_16 = 16; // Constants for the number of colors displayed in different menu options
        final int COLORS_10 = 10;
        final int COLORS_5 = 5;
        final int TOTAL_ROUNDS = 3;
        final String FILENAME_RESULTS = "EspGameResults.txt";
        
        
        // Scanner for reading user input from the keyboard
        Scanner scanner = new Scanner(System.in);
        
        // To start the while loop and later will become false if user wants to end game
        boolean playAgain = true;
        
        System.out.println("CMSC203 Assignment1: Test your ESP skills!");
        System.out.println("Welcome to ESP - extrasensory perception!");
        
        while (playAgain) {
            
            // Menu option for user
            System.out.println("Would you please choose one of the 4 options from the menu: ");
            System.out.println("1- Read and display the first 16 colors from a file.");
            System.out.println("2- Read and display the first 10 colors from a file.");
            System.out.println("3- Read and display the first 5 colors from a file.");
            System.out.println("4- Exit the program.");
            System.out.print("Enter the option: ");
            
            // Sets and resets intial guesses for each game
            int correctGuesses = 0;

            int option = scanner.nextInt(); 
            scanner.nextLine(); //Read in users option 1-4

            if (option == 4) break;

            int colorLimit;
            // Determine the number of colors to read based on the user's menu selection
            if (option == 1) {
                colorLimit = COLORS_16;  // Assign 16 if the user chose option 1
            } else if (option == 2) {
                colorLimit = COLORS_10;  // Assign 10 if the user chose option 2
            } else {
                colorLimit = COLORS_5;   // Assign 5 if the user chose any other valid option
            }

            System.out.print("Enter the filename: ");
            String filename = scanner.nextLine();

            try {
                // Create a File object to open the specified file
                File file = new File(filename);
                
                //Reads from the file
                Scanner fileScanner = new Scanner(file);
                
                // Keep track of number of colors
                int index = 0;
                
                // Variable to store the random color
                String selectedColor = "";
                
                //Creates random object to generate random index for color selection
                Random rand = new Random();
                
                // Generate a random number between 1 and colorLimit
                int randomColorIndex = rand.nextInt(colorLimit) + 1; 
                
                System.out.println("There are " + colorLimit + " colors from the file:");
                
                // Loop through the file reading colors line by line up to colorLimit
                while (fileScanner.hasNextLine() && index < colorLimit) {
                    index++;
                    String color = fileScanner.nextLine().trim(); //trim removes any whitespace
                    System.out.println(index + " " + color);
                    
                    if (index == randomColorIndex) {
                        selectedColor = color; // Save the randomly chosen color
                    }
                }
                fileScanner.close();

                // Keeps track of user guesses and correct guesses with a loop that goes 3 times
                for (int round = 1; round <= TOTAL_ROUNDS; round++) {
                    System.out.println("\nRound " + round);
                    System.out.println("\nI am thinking of a color.");
                    System.out.println("Is it one of the list of colors above?");
                    System.out.print("Enter your guess: ");
                    
                    String userGuess = scanner.nextLine().trim();

                    System.out.println("\nI was thinking of " + selectedColor + ".");
                    
                    if (userGuess.equalsIgnoreCase(selectedColor)) {
                        correctGuesses++;
                    }
                }

                System.out.println("\nGame Over");
                System.out.println("\nYou guessed " + correctGuesses + " out of " + TOTAL_ROUNDS + " colors correctly.");

            // Error handling if the file does not exist
            } catch (IOException e) {
                System.out.println("Error reading file. Exiting...");
                break;
            }
            
            // Play again option
            System.out.print("\nWould you like to continue the game? Type Yes/No: ");
            String response = scanner.nextLine().trim();
            playAgain = response.equalsIgnoreCase("yes");
            
            // Collect user information
            System.out.print("\nEnter your name: ");
            String username = scanner.nextLine();
            System.out.print("Describe yourself: ");
            String userDescription = scanner.nextLine();
            System.out.print("Due Date: ");
            String dueDate = scanner.nextLine();
    
            // Display final user information
            System.out.println("\nUsername: " + username);
            System.out.println("User Description: " + userDescription);
            System.out.println("Date: " + dueDate);
    
            // Save results to ESPGameResults file
            try {
                FileWriter writer = new FileWriter(FILENAME_RESULTS);
                writer.write("Game Over\n");
                writer.write("You guessed " + correctGuesses + " out of " + TOTAL_ROUNDS + " colors correctly.\n");
                writer.write("Due Date: " + dueDate + "\n");
                writer.write("Username: " + username + "\n");
                writer.write("User Description: " + userDescription + "\n");
                writer.write("Date: " + dueDate);
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
    
            System.out.println("\nResults saved to " + FILENAME_RESULTS);
            
            }
        scanner.close();
    }
}