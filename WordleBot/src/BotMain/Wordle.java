package BotMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Wordle {

    private String guess;
    private String targetWord;

    public static void main(String args[]) throws Exception {
        List<String> dictionary = createDictionary();
        Wordle wordle = new Wordle();
        wordle.solveWordle(dictionary);
    }

    public void solveWordle(List<String> dictionary) {
        Random random = new Random();
        targetWord = "fleet";
        //targetWord = dictionary.get(random.nextInt(dictionary.size())); // Pick a random word from the dictionary
        System.out.println("Target Word: " + targetWord);

        int attempts = 0;
        while (attempts < 6) {
            if (attempts == 0) {
                guess = "crane";

            } else {

                GuessGenerator guessGenerator = new GuessGenerator(dictionary, guess, targetWord);
                guessGenerator.reviseTime();
                guess = guessGenerator.generateGuess(); // Generate the next guess
            }

            attempts++;
            System.out.println("Guess " + attempts + ": " + guess);

            // Check if the guess matches the target word
            if (guess.equals(targetWord)) {
                System.out.println("Wordle solved in " + attempts + " attempts!");
                break;
            }
        }

        if (attempts > 6)
            System.out.println("I was unable to guess the word correctly");
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
    public static void reviseDictionaryHasLetter(List<String> dictionary, char letter) {
        Iterator<String> iterator = dictionary.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (!word.contains(String.valueOf(letter))) {
                iterator.remove();
            }
        }
        System.out.println("Dictionary Length: " + dictionary.toArray().length);
    }
    public static void reviseDictionaryNoLetter(List<String> dictionary, char letter) {
        Iterator<String> iterator = dictionary.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (word.contains(String.valueOf(letter))) {
                iterator.remove();
            }
        }
        System.out.println("Dictionary Length: " + dictionary.toArray().length);
    }
}
