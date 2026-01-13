package main.java.ru.hangman;

public class Game {

    public void playGame() {
        while (true) {
            ScreenRenderer.printStartMenu();
            switch (getMenuChoice()) {
                case 1:
                    startGameIteration();
                    break;
                case 0:
                    ScreenRenderer.printMessage("Игра отменена");
                    return;
            }
        }
    }

    private int getMenuChoice() {
        while (true) {
            String choice = ConsoleReader.readInput();
            if (choice.equals(GameConstants.YES)) {
                return 1;
            }
            if (choice.equals(GameConstants.NO)) {
                return 0;
            }
            ScreenRenderer.printError("Введите либо " + GameConstants.YES + " для начала игры, либо " + GameConstants.NO + " для прекращения!");
        }
    }

    private void startGameIteration() {
        try {
            ScreenRenderer.printMessage("Игра началась");
            GameIteration gameIteration = new GameIteration(Dictionary.getInstance());
            while (!gameIteration.isGameOver() && !gameIteration.isGameWon()) {
                gameIteration.guessLetter();
            }
            if (gameIteration.isGameOver()) {
                gameOver(gameIteration);
            }
            if (gameIteration.isGameWon()) {
                youWin(gameIteration);
            }

        } catch (Exception e) {
            ScreenRenderer.printError(e.getMessage());
        }
    }

    private void gameOver(GameIteration gameIteration) {
        gameIteration.gameState();
        ScreenRenderer.printMessage("Тебя повесили!");
    }

    private void youWin(GameIteration gameIteration) {
        gameIteration.gameState();
        ScreenRenderer.printMessage("Ты победил!");
    }

}
