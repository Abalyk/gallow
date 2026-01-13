package main.java.ru.hangman;

import java.util.ArrayList;
import java.util.List;

public class GameIteration {
    private final SecretWord secretWord;
    private final Hangman hangman;
    private boolean isGameOver = false;
    private boolean isGameWon = false;
    private List<Character> wrongLetters = new ArrayList<>();

    public GameIteration(Dictionary dictionary) {
        secretWord = new SecretWord(dictionary.getRandomWord());
        hangman = new Hangman();
    }

    public void guessLetter() {
        gameState();
        char letter = getLetterFromConsole();
        if (!secretWord.guessLetter(letter)) {
            if (!wrongLetters.contains(letter)) {
                wrongLetters.add(letter);
                hangman.incrementStage();
            }
        }
        if (!hangman.isAlive()) {
            isGameOver = true;
        }
        if (secretWord.isGuessed()) {
            isGameWon = true;
        }
    }

    private char getLetterFromConsole() {
        while (true) {
            ScreenRenderer.printMessage("Введите букву:");
            String letter = ConsoleReader.readInput();
            if (validateInput(letter)) {
                return letter.toLowerCase().charAt(0);
            }
        }
    }

    private boolean validateInput(String input) {
        if (input == null || input.isEmpty()) {
            ScreenRenderer.printError("Введите букву:");
            return false;
        }
        if (input.length() != 1) {
            ScreenRenderer.printError("Введите ОДНУ букву:");
            return false;
        }

        char letter = input.toLowerCase().charAt(0);
        if (!Character.isLetter(letter)) {
            ScreenRenderer.printError("Введите букву а не символ:");
            return false;
        }
        if (!((letter >= 'А' && letter <= 'Я') ||
                (letter >= 'а' && letter <= 'я') ||
                letter == 'Ё' || letter == 'ё')) {
            ScreenRenderer.printError("Введите букву на русском языке:");
            return false;
        }
        if (secretWord.isLetterAlreadyGuessed(letter)) {
            ScreenRenderer.printError("Буква '" + letter + "' уже была угадана!");
            return false;
        }
        if (wrongLetters.contains(letter)) {
            ScreenRenderer.printError("Буква '" + letter + "' уже была ошибочной!");
            return false;
        }
        return true;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void gameState() {
        ScreenRenderer.printGameState(secretWord, hangman, wrongLetters);
    }
}
