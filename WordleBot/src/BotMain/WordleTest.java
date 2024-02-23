package BotMain;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;


public class WordleTest {

    @Test
    public void testAverageAttemptsToSolve() throws FileNotFoundException {
        Wordle wordle = new Wordle();

        int totalAttempts = 0;
        int numberOfGames = 100;

        for (int i = 0; i < numberOfGames; i++) {
            totalAttempts += runGameAndGetAttempts(wordle);
        }

        double averageAttempts = (double) totalAttempts / numberOfGames;

        System.out.println("Average attempts to solve after " + numberOfGames + " games: " + averageAttempts);
    }

    private int runGameAndGetAttempts(Wordle wordle) throws FileNotFoundException {
        List<String> dictionary = Wordle.createDictionary();
        String targetWord = wordle.createTargetWord(dictionary);
        wordle.solveWordle(dictionary, targetWord);
        return wordle.getAttempts();
    }
}
