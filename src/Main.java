import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String YES = "1";
    private static final String NO = "0";
    private static List<String> words;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String MENU = "Сыграем в виселицу?\n" +
            "Для начала игры введи \"" + YES + "\". Для выхода введи \"" + NO + "\"";

    public static void main(String[] args) throws IOException {
        words = getWords();
        Gallow.pringFullGallow();  //Печатаем виселицу для красоты
        start(); //стартовое меню
    }

    private static void start() {
        boolean wannaPlay = true;
        while (wannaPlay) {
            System.out.println(MENU);
            String answer = scanner.next().toLowerCase();
            switch (answer){
                case YES:
                    game();
                    break;
                case NO:
                    System.out.println("Игра отменена");
                    System.exit(0);//пробовал обходиться только  wannaPlay = false; Но оно плохо отрабатывало, так как сканер шалит. Выключалось только со второго-третьего раза. Поэтому решил вырубать более радикально
                    wannaPlay = false;
                    break;
                default:
                    System.out.println("Введите либо 1 либо 0!");
                    break;
            }
        }
    }

    private static void game() {
        Gallow gallow = new Gallow();
        String word = words.get(ThreadLocalRandom.current().nextInt(words.size()));
        boolean[] lettersFlags = new boolean[word.length()];
        boolean win = false;
        boolean haveValidationErrors = false;
        while (gallow.getErrors() < 6 && !win) {
            printScreen(gallow, word, lettersFlags);
            if (haveValidationErrors) {
                System.out.println("При вводе была допущена ошибка. ВАЖНО! Вводить нужно только одну букву на русском языке!");
                haveValidationErrors = false;
            }
            System.out.println("Введи букву в консоль:");
            String answer = scanner.next();
            if (validateAnswer(answer)) {
                char letter = answer.toLowerCase().charAt(0);
                if (word.indexOf(letter) != -1) {
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == letter) {
                            lettersFlags[i] = true;
                        }
                    }
                    if (checkAllTrue(lettersFlags)) {
                        System.out.println("Поздравляю! Ты победил! Слово: " + word);
                        win = true;
                    }
                } else {
                    gallow.makeAnError();
                }
            } else {
                haveValidationErrors = true;
            }
        }
        if (gallow.getErrors() >= 6) {
            printScreen(gallow, word, lettersFlags);
            System.out.println("Тебя повесили!");
        }
        start();
    }

    private static void printScreen(Gallow gallow, String word, boolean[] lettersFlags) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Ошибок: " + gallow.getErrors());
        gallow.printGallow();
        printWord(word, lettersFlags);
    }

    private static boolean checkAllTrue(boolean[] flags) {
        boolean checkAllTrue = true;
        for (boolean b : flags) {
            if (!b) {
                checkAllTrue = false;
                break;
            }
        }
        return checkAllTrue;
    }

    private static boolean validateAnswer(String answer) {
        if (answer.length() > 1) {
            System.out.println("Нужно указать только одну букву!");
            return false;
        } else {
            return true;
        }
    }

    private static void printWord(String word, boolean[] flags) {
        System.out.print("Слово: ");
        for (int i = 0; i < word.length(); i++) {
            if (flags[i]) {
                System.out.print(word.charAt(i) + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private static List<String> getWords() throws IOException {
        try (var lines = Files.lines(Path.of("src/resources/words.txt"))) {
            return lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(String::toLowerCase)
                    .toList();
        }
    }
}