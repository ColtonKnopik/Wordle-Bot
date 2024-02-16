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

        return getRandomGuess();
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

}

