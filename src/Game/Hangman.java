package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static int WORD_FILE_SIZE = 127142;  // num possible words
    public static String FILENAME = "words.txt";    // filename
    public static int MAX_GUESSES = 6;      // maximum num of guesses

    private final char[] answer;        // holds answer
    private final char[] playerGuess;   // holds player guess
    private final ArrayList<Character> alreadyGuessed;  // holds guessed letters
    private int numWrong;       // Num wrong guesses

    /**
     * Constructor
     * @throws FileNotFoundException If file not found
     */
    public Hangman() throws FileNotFoundException {
        // Read file
        File wordFile = new File(FILENAME);
        Scanner s = new Scanner(wordFile);

        // Initialize random word
        Random r  = new Random();
        int wordNum = r.nextInt(WORD_FILE_SIZE);
        for (int i = 0; i < wordNum - 1; i++) {
            s.nextLine();

        }
        answer = s.nextLine().toCharArray();

        alreadyGuessed = new ArrayList<>();
        numWrong = 0;
        playerGuess = new char[answer.length];
        s.close();

        for (int i = 0; i < answer.length; i++) {
            playerGuess[i] = '_';
        }
    }

    /**
     * Gets list of letters that have already been guessed
     * @return String Guessed letters
     */
    public String getGuessedLetters() {
        StringBuilder sb = new StringBuilder();
        for (Character c: alreadyGuessed) {
            sb.append(c).append(" ");
        }
        return sb.toString();
    }

    /**
     * Guess letter in word
     * @param c character guessed
     * @return true if correct guess, false if wrong
     */
    public boolean guess(char c) {
        alreadyGuessed.add(c);
        boolean correctGuess = false;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == c) {
                playerGuess[i] = c;
                correctGuess = true;
            }
        }

        if (correctGuess) {
            return true;
        } else {
            numWrong++;
            return false;
        }
    }

    /**
     * String representation of current game
     * @return String representation of current game
     */
    public String toString() {
        return switch (numWrong) {
            case 0 -> noneWrong;
            case 1 -> oneWrong;
            case 2 -> twoWrong;
            case 3 -> threeWrong;
            case 4 -> fourWrong;
            case 5 -> fiveWrong;
            default -> endGame;
        };
    }

    /**
     * Check if player has won the game
     * @return True if won, false if not
     */
    public boolean checkWin() {
        // Check if each letter in guess is the same as answer
        for (int i = 0; i < answer.length; i++) {
            if (playerGuess[i] != answer[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns correct word
     * @return answer
     */
    public String getWord() {
        return String.valueOf(this.answer);
    }

    /**
     * Check to see if player has used up all turns
     * @return True if end of game, false if not
     */
    public boolean checkEndGame() {
        return numWrong == MAX_GUESSES;
    }

    /**
     * Return current guessed letters along with blanks
     * @return String Current guess
     */
    public String currentGuess() {
        return String.valueOf(playerGuess);
    }

    public String noneWrong = "   _____________\n" +
            "   |           |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "          _____|_____";

    public String oneWrong = "   _____________\n" +
            "   |           |\n" +
            "  ( )          |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "          _____|_____";

    public String twoWrong = "   _____________\n" +
            "   |           |\n" +
            "  ( )          |\n" +
            "   |           |\n" +
            "   |           |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "          _____|_____";

    public String threeWrong = "   _____________\n" +
            "   |           |\n" +
            "  ( )          |\n" +
            "  /|           |\n" +
            "   |           |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "          _____|_____";

    public String fourWrong = "   _____________\n" +
            "   |           |\n" +
            "  ( )          |\n" +
            "  /|\\          |\n" +
            "   |           |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "          _____|_____";

    public String fiveWrong = "   _____________\n" +
            "   |           |\n" +
            "  ( )          |\n" +
            "  /|\\          |\n" +
            "   |           |\n" +
            "  /            |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "          _____|_____";

    public String endGame = "   _____________\n" +
            "   |           |\n" +
            "  ( )          |\n" +
            "  /|\\          |\n" +
            "   |           |\n" +
            "  / \\          |\n" +
            "               |\n" +
            "               |\n" +
            "               |\n" +
            "          _____|_____";


}
