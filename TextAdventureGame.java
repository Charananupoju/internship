import java.util.Scanner;

public class TextAdventureGame {
    public static void main(String[] args) {
        // Initialize Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Introduction
        System.out.println("Welcome to the Text Adventure Game!");

        // Start the game
        startGame(scanner);

        // Close the scanner
        scanner.close();
    }

    private static void startGame(Scanner scanner) {
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("\nYou find yourself in a mysterious forest. Choose your path:");
            System.out.println("1. Follow the path deeper into the forest.");
            System.out.println("2. Climb a tree to get a better view.");
            System.out.println("3. Quit the game.");

            // Get user input
            int choice = getUserChoice(scanner, 3);

            // Process user choice
            switch (choice) {
                case 1:
                    System.out.println("\nAs you walk deeper, you encounter a friendly squirrel. You made a new friend!");
                    break;
                case 2:
                    System.out.println("\nClimbing the tree, you see a hidden treasure chest. You found a valuable item!");
                    break;
                case 3:
                    System.out.println("\nQuitting the game. Thanks for playing!");
                    continueGame = false;
                    break;
            }
        }
    }

    private static int getUserChoice(Scanner scanner, int maxChoice) {
        int choice = 0;
        boolean validInput = false;

        // Keep prompting until valid input is provided
        while (!validInput) {
            System.out.print("\nEnter your choice (1-" + maxChoice + "): ");

            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                // Check if the input is within the valid range
                if (choice >= 1 && choice <= maxChoice) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + maxChoice + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }

        return choice;
    }
}
