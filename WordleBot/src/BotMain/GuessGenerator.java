package BotMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessGenerator {
    private List<String> dictionary;
    private String guess;
    private String targetWord;

    public GuessGenerator(List<String> dictionary, String guess, String targetWord) {
        this.dictionary = dictionary;
        this.guess = guess;
        this.targetWord = targetWord;
    }

    public String generateGuess() {
        int count = 0;
        String curGuess = dictionary.get(count);

        while(hasRepeatLetters(curGuess))
        {
            count++;
            curGuess = dictionary.get(count);
            if(count >= dictionary.size())
                break;
        }

        return curGuess;
    }

    public void reviseTime(){

        if (containsLettersFromTarget(guess, targetWord)) {
            for(char letter : guess.toCharArray()){
                if (targetWord.contains(String.valueOf(letter)))
                    Wordle.reviseDictionaryHasLetter(dictionary, letter);
                else
                    Wordle.reviseDictionaryNoLetter(dictionary, letter);
            }
        }
        else
            for(char letter : guess.toCharArray()){
                Wordle.reviseDictionaryNoLetter(dictionary, letter);
            }
    }


    private String getRandomGuess() {
        Random random = new Random();
        return this.dictionary.get(random.nextInt(this.dictionary.size()));
    }

    private boolean containsLettersFromTarget(String guess, String targetWord) {
        for (char letter : guess.toCharArray()) {
            if (targetWord.contains(String.valueOf(letter))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasRepeatLetters(String word) {
        // Create a boolean array to store whether a letter has been encountered before
        boolean[] encountered = new boolean[256]; // Assuming ASCII characters

        // Iterate through each character in the word
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // If the character has been encountered before, return true
            if (encountered[c]) {
                return true;
            }

            // Mark the character as encountered
            encountered[c] = true;
        }

        // If no repeating characters are found, return false
        return false;
    }

}

