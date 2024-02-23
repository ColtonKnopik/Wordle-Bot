package BotMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessGenerator {
    private List<String> dictionary;
    private String guess;

    public GuessGenerator(List<String> dictionary, String guess) {
        this.dictionary = dictionary;
        this.guess = guess;
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

        for(char letter : guess.toCharArray()){
            int index = findLetterIndex(letter, guess);

            if (Wordle.targetContainsLetter(letter)) {

                if (Wordle.hasSameLetterAtIndex(guess, index)) { //letter is in the correct spot (green)
                    Wordle.reviseDictionaryHasLetterRightIndex(dictionary, letter, index);
                }

                else {
                    Wordle.reviseDictionaryHasLetterWrongIndex(dictionary, letter); //letter in word in wrong spot (yellow)
                    Wordle.reviseDictionaryRemoveAtIndex(dictionary, guess.toCharArray()[index], index);
                }
            }
                else
                    Wordle.reviseDictionaryNoLetter(dictionary, letter); // letter not in the word (grey)
            }
        dictionary.remove(guess);
    }
    private String getRandomGuess() {
        Random random = new Random();
        return this.dictionary.get(random.nextInt(this.dictionary.size()));
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

