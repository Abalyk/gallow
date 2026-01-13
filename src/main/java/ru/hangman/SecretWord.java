package main.java.ru.hangman;

public class SecretWord {
    private final String word;
    private boolean[] wordFlags;

    public SecretWord(String word) {
        this.word = word;
        wordFlags = new boolean[word.length()];
    }

    public boolean isGuessed() {
        for (boolean b : wordFlags) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public String getMaskedWord() {
        StringBuilder maskedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (wordFlags[i]) {
                maskedWord.append(word.charAt(i)).append(" ");
            } else {
                maskedWord.append("_ ");
            }
        }
        return maskedWord.toString();
    }

    public boolean isLetterAlreadyGuessed(char letter){
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i)==letter && wordFlags[i]){
                return true;
            }
        }
        return false;
    }

    public boolean guessLetter(char letter) {
        if (word.indexOf(letter) != -1) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    wordFlags[i] = true;
                }
            }
            return true;
        }
        return false;
    }

}
