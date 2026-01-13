package main.java.ru.hangman;

public class GallowsVisualizer {

    private static final String[] STAGES = {
            """
               -----
                    |
                    |
                    |
            ________|
            """,
            """
               -----
               O    |
                    |
                    |
            ________|
            """,
            """
               -----
               O    |
               |    |
                    |
            ________|
            """,
            """
               -----
               O    |
             --|    |
                    |
            ________|
            """,
            """
               -----
               O    |
             --|--  |
                    |
            ________|
            """,
            """
               -----
               O    |
             --|--  |
              /     |
            ________|
            """,
            """
               -----
               O    |
             --|--  |
              / \\   |
            ________|
            """
    };

    public static String getGallow(int stage) {
        validateStage(stage);
        return STAGES[stage];
    }

    private static void validateStage(int stage) {
        if (stage < 0 || stage >= STAGES.length) {
            throw new IllegalArgumentException("Stage должен быть в диапазоне от 0 до " + (STAGES.length - 1) + ". Пришло значение: " + stage);
        }
    }

    public static String getFulGallow(){
        return STAGES[STAGES.length-1];
    }
}
