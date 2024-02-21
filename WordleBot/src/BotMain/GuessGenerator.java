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
        int maxCommonality = Integer.MIN_VALUE;
        String bestGuess = null;

        for (String word : dictionary) {
            int commonality = Alphabet.S.generateCommonality(word);

            // Update best guess if current word has higher commonality
            if (commonality > maxCommonality) {
                maxCommonality = commonality;
                bestGuess = word;
            }
        }

        this.guess = bestGuess;
        return bestGuess;
    }


    public void reviseTime(){

        if(guess == null)
            return;

        if (containsLettersFromTarget(guess, targetWord)) {
            for(char letter : guess.toCharArray()){
                int index = findLetterIndex(letter, guess);
                if (targetWord.contains(String.valueOf(letter))) {
                    if (hasSameLetterAtIndex(guess, targetWord, index)) { //FIX THIS
                        Wordle.reviseDictionaryHasLetterRightIndex(dictionary, letter, index);
                    }
                    else {
                        Wordle.reviseDictionaryHasLetterWrongIndex(dictionary, letter);
                        Wordle.reviseDictionaryRemoveAtIndex(dictionary, guess.toCharArray()[index], index);
                    }
                }

                else
                    Wordle.reviseDictionaryNoLetter(dictionary, letter);
            }
        }
        else
            for(char letter : guess.toCharArray()){
                Wordle.reviseDictionaryNoLetter(dictionary, letter);
            }
        dictionary.remove(guess);
        //System.out.println("possible words: " + dictionary.size());
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
        boolean[] encountered = new boolean[256]; // Assuming ASCII characters

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (encountered[c]) {
                return true;
            }
            encountered[c] = true;
        }

        return false;
    }

    private boolean hasSameLetterAtIndex(String guess, String targetWord, int index){

        if(guess.toCharArray()[index] == targetWord.toCharArray()[index]){
            return true;
        }
        return false;
    }

    private int findLetterIndex(char letter, String word){

        for(int i = 0; i < word.length(); i++){
            if(word.toCharArray()[i] == letter)
                return i;
        }

        System.out.println(letter + " is not in " + word);
        return -1;
    }

    private int indexAtLetter(String word, char letter){
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                return i;
            }
        }
        return -1;
    }



}

