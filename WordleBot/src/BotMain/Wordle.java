package BotMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import BotMain.Alphabet;

public class Wordle {

    private String guess;
    private String targetWord;
    private int attempts;

    public static void main(String args[]) throws Exception {
        List<String> dictionary = createDictionary();
        Wordle wordle = new Wordle();
        wordle.solveWordle(dictionary);
    }

    public void solveWordle(List<String> dictionary) {
        Random random = new Random();
        //targetWord = "match";
        targetWord = dictionary.get(random.nextInt(dictionary.size())); // Pick a random word from the dictionary
        System.out.println("Target Word: " + targetWord);
        //System.out.println("Possible words: " + dictionary.size());

        GuessGenerator guessGenerator = new GuessGenerator(dictionary, null, targetWord); // Initialize guessGenerator outside the loop
        attempts = 0;

        while (attempts < 6) {
            guessGenerator.reviseTime();
            String guess = guessGenerator.generateGuess(); // Generate the next guess
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
    public static void reviseDictionaryHasLetterWrongIndex(List<String> dictionary, char letter) {
        dictionary.removeIf(word -> !word.contains(String.valueOf(letter))); //removes every word without letter
    }
    public static void reviseDictionaryNoLetter(List<String> dictionary, char letter) {
        dictionary.removeIf(word -> word.contains(String.valueOf(letter))); //removes every word with letter
    }

    public static void reviseDictionaryHasLetterRightIndex(List<String> dictionary, char letter, int index){
        char[] word;
        for(int i = 0; i < dictionary.size() - 1; i ++){
            word = dictionary.get(i).toCharArray();
            if(letter != word[index]){
                dictionary.remove(i);
            }
        }
    }

    public static void reviseDictionaryRemoveAtIndex(List<String> dictionary, char letter, int index){
        char[] word;
        for(int i = 0; i < dictionary.size() - 1; i ++){
            word = dictionary.get(i).toCharArray();
            if(letter == word[index]){
                dictionary.remove(i);
            }
        }
    }

    public int getAttempts() {
        return attempts;
    }
}
