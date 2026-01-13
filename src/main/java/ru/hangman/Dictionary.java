package main.java.ru.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Dictionary {
    private static Dictionary instance;
    private final List<String> words;

    private Dictionary(String filePath) {
        Path path = Path.of(filePath).toAbsolutePath();
        try (var lines = Files.lines(path)) {
            words = lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(String::toLowerCase)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить слова по пути: " + path + ". Проверьте, существует ли этот файл и правильно ли указан путь.", e);
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("Словарь пуст");
        }
        return words.get(ThreadLocalRandom.current().nextInt(words.size()));
    }

    public static Dictionary getInstance() {
        return getInstance(GameConstants.WORDS_FILE);
    }

    public static Dictionary getInstance(String filePath) {
        if (instance == null) {
            instance = new Dictionary(filePath);
        }
        return instance;
    }

}


