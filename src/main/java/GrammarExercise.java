import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 需要从命令行读入
        String firstWordList = reader.readLine();
        String secondWordList = reader.readLine();

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        // 按要求输出到命令行

        result.forEach(System.out::println);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        // 在这编写实现代码
        List<String> sameWords = new ArrayList<>(16);
        Map<String, Integer> wordsCount = new HashMap<>(16);

        List<String> firstList = splitAndValidate(firstWordList);
        List<String> secondList = splitAndValidate(secondWordList);

        firstList.forEach(word -> wordsCount.put(word.toLowerCase(), 1));
        secondList.forEach(word -> {
            String lowerCaseWord = word.toLowerCase();
            int count = wordsCount.getOrDefault(lowerCaseWord, 0);

            if (count == 1) {
                sameWords.add(lowerCaseWord);
                wordsCount.put(lowerCaseWord, count + 1);
            }
        });

        return sameWords.stream().sorted().map(GrammarExercise::formatWord).collect(Collectors.toList());
    }

    private static List<String> splitAndValidate(String wordsList) {
        List<String> result = new ArrayList<>(16);
        String[] splitedWords = wordsList.split(",");

        Arrays.stream(splitedWords).forEach((word) -> {
            validateWord(word);
            result.add(word);
        });

        return result;
    }

    private static void validateWord(String word) {
        if ("".equals(word)) {
            throw new RuntimeException("input not valid");
        }

        word.chars().forEach(character -> {
            if (!((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z'))) {
                throw new RuntimeException("input not valid");
            }
        });
    }

    private static String formatWord(String word) {
        StringBuilder stringBuilder = new StringBuilder(2 * word.length());

        word.toUpperCase().chars().forEach(character -> {
            stringBuilder.append((char) character);
            stringBuilder.append(' ');
        });

        stringBuilder.setLength(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}
