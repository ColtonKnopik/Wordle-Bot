package BotMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import BotMain.Alphabet;

public class Wordle {
    private String guess;
    private static String targetWord;
    private int attempts;

    public static void main(String[] args) throws Exception {
        List<String> dictionary = createDictionary();
        Wordle wordle = new Wordle();

        wordle.createTargetWord(dictionary);
        wordle.solveWordle(dictionary, targetWord);
    }

    public String createTargetWord(List<String> dictionary){
        Random random = new Random();
        //targetWord = "quoth";
        targetWord = dictionary.get(random.nextInt(dictionary.size())); // Pick a random word from the dictionary
        System.out.println("Target Word: " + targetWord);
        //System.out.println("Possible words: " + dictionary.size());
        return targetWord;
    }

    public void solveWordle(List<String> dictionary, String targetWord) {
        attempts = 0;

        while (attempts < 6) {
            GuessGenerator guessGenerator = new GuessGenerator(dictionary, guess); // Initialize guessGenerator outside the loop
            String guess = guessGenerator.generateGuess(); // Generate the next guess
            guessGenerator.reviseTime();
            attempts++;
            System.out.println("Guess " + attempts + ": " + guess);

            // Check if the guess matches the target word
            if (guess.equals(targetWord)) {
                System.out.println("Wordle solved in " + attempts + " attempts!");
                break;
            }
        }

        if(attempts >= 6){
            System.out.println("failed to guess word");
        }
    }

    public static List<String> createDictionary() throws FileNotFoundException {
        int count = 0;
        List<String> dictionary = new ArrayList<>();
        File words = new File("sgb-words.txt");
        Scanner scanner = new Scanner(words);
        while (scanner.hasNextLine()) {
            dictionary.add(scanner.nextLine());
            count++;
        }
        return dictionary;
    }

    public static boolean hasSameLetterAtIndex(String guess, int index){
        return guess.toCharArray()[index] == targetWord.toCharArray()[index];
    }

    public static void reviseDictionaryHasLetterWrongIndex(List<String> dictionary, char letter) {
        dictionary.removeIf(word -> !word.contains(String.valueOf(letter))); //removes every word without letter
    }
    public static void reviseDictionaryNoLetter(List<String> dictionary, char letter) {
        dictionary.removeIf(word -> word.contains(String.valueOf(letter))); //removes every word with letter
    }

    public static void reviseDictionaryHasLetterRightIndex(List<String> dictionary, char letter, int index) { //removes letters that dont have letter at index
        dictionary.removeIf(word -> word.charAt(index) != letter);
    }

    public static void reviseDictionaryRemoveAtIndex(List<String> dictionary, char letter, int index){ //removes words that do have letter at index
        dictionary.removeIf(word -> word.charAt(index) == letter);
    }

    public static boolean targetContainsLetter(char letter) {
        return targetWord.contains(String.valueOf(letter));
    }

    public int getAttempts() {
        return attempts;
    }
}
