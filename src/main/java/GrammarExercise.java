import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Set<String> firstSet = new HashSet<>(splitWords(firstWordList));
        Set<String> secondSet = new HashSet<>(splitWords(secondWordList));

        firstSet.retainAll(secondSet);

        return firstSet.stream().sorted().collect(Collectors.toList());
    }

    private static List<String> splitWords(String wordsList) {
        String[] splitedWords = wordsList.split(",");

        return Arrays.stream(splitedWords).map((word) -> {
            validateWord(word);
            return formatWord(word);
        }).collect(Collectors.toList());
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
