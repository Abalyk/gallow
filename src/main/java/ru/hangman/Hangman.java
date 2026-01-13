package main.java.ru.hangman;

public class Hangman {
    private boolean isAlive = true;
    private int stage = 0;

    public boolean isAlive() {
        return isAlive;
    }

    public void incrementStage() {
        if (stage == GameConstants.MAX_ATTEMPTS) {
            throw new IllegalStateException("Лимит ошибок исчерпан. Вы повешены!");
        }
        stage++;
        if (stage == GameConstants.MAX_ATTEMPTS) {
            isAlive = false;
        }
    }

    public int getStage(){
        return stage;
    }
}
