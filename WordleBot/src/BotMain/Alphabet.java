package BotMain;

import java.util.HashSet;
import java.util.Set;

public enum Alphabet {
    E('e',26), A('a',25), R('r',24), I('i', 23), O('o', 22), T('t', 21), N('n', 20), S('s',19), L('l', 18),
    C('c',17), U('u', 16), D('d', 15), P('p', 14), M('m', 13), H('h', 12), G('g', 11), B('b', 10), F('f', 9),
    Y('y', 8), W('w', 7), K('k', 6), V('v', 5), X('x', 4), Z('z', 3), J('j', 2), Q('q', 1);
    ;
    private char letter;
    private int commonality;
    Alphabet(char letter, int commonality) {
        this.letter = letter;
        this.commonality = commonality;
    }

    public int generateCommonality(String word) {
        int common = 0;
        Set<Character> encounteredLetters = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            char letter = Character.toLowerCase(word.charAt(i));

            if (!encounteredLetters.contains(letter)) {
                for (Alphabet alphabet : Alphabet.values()) {
                    if (alphabet.letter == letter) {
                        common += alphabet.commonality;
                        break;
                    }
                }
                encounteredLetters.add(letter);
            }
        }
        return common;
    }


}
