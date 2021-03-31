package Game;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        welcome();
        playGame();
        goodbye();
    }

    /**
     * Plays hangman game
     * @throws FileNotFoundException If word file not found
     */
    public static void playGame() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String playAgain = "y";

        // Run game until user decides not to play anymore
        while (playAgain.equals("y")) {
            Hangman game = new Hangman();
            boolean endGame = false;
            // Ask for character until game ends
            while (!endGame) {
                // Show word to be guessed
                System.out.println(game.currentGuess());
                System.out.println();

                // Prompt user for character
                System.out.print("Enter a character: ");

                // Stores user guess
                char c = input.next().charAt(0);

                // Check if user guess is correct
                if (game.guess(c)) {
                    System.out.println("Correct!\n");
                } else {
                    System.out.println("Wrong\n");
                }

                // Print current status of hangman game and guessed letters
                System.out.println(game);
                System.out.println("Letters Guessed: " + game.getGuessedLetters());

                // Check if user has won game
                if (game.checkWin()) {
                    System.out.println("You guessed right!");
                    endGame = true;
                }

                // Check if player has ran out of guesses
                if (game.checkEndGame()) {
                    System.out.println("Not this time.. the word was: " + game.getWord());
                    System.out.println();
                    endGame = true;
                }

            }
            // Consume new line
            input.nextLine();
            // Prompt user if they want to play again
            System.out.print("Would you like to play again? (y/n): ");
            playAgain = input.nextLine();
        }
        input.close();
    }

    /**
     * Prints welcome message
     */
    public static void welcome() {
        System.out.println("---WELCOME TO HANGMAN---\n" +
                "Guess the following word: ");
    }

    /**
     * Prints goodbye message
     */
    public static void goodbye() {
        System.out.println("Thanks for playing!");
    }

}
