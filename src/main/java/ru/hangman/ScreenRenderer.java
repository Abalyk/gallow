package main.java.ru.hangman;

import java.util.List;

public class ScreenRenderer {

    public static void printGameState(SecretWord word, Hangman hangman, List<Character> errors) {
        System.out.println("\n".repeat(7));
        System.out.println("Ошибок: ("+ hangman.getStage()+") " + errors.toString());
        System.out.println(GallowsVisualizer.getGallow(hangman.getStage()));
        System.out.println("Слово: " + word.getMaskedWord());
    }

    public static void printStartMenu() {
        System.out.println(GallowsVisualizer.getFulGallow());
        System.out.println("Сыграем в виселицу?\n" + "Для начала игры введи \"" + GameConstants.YES + "\". Для выхода введи \"" + GameConstants.NO + "\"");
    }

    public static void printError(String error) {
        System.out.println(error);
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

}
