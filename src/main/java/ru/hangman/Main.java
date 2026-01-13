package main.java.ru.hangman;

public class Main {

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.playGame();
        }
        catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConsoleReader.close();
        }
    }
}