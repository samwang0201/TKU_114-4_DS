import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {

    public static void main(String[] args) {

        String[] sentences = {
            "Java is easy, Java is useful.",
            "I like Java, and I like programming.",
            "Programming is fun."
        };

        Map<String, Integer> wordCount =
                new HashMap<String, Integer>();

        Set<String> uniqueWords =
                new HashSet<String>();

        for (int i = 0; i < sentences.length; i++) {

            String sentence = sentences[i].toLowerCase();

            sentence = sentence.replace(".", "");
            sentence = sentence.replace(",", "");

            String[] words = sentence.split(" ");

            for (int j = 0; j < words.length; j++) {

                String word = words[j];

                uniqueWords.add(word);

                if (wordCount.containsKey(word)) {
                    int count = wordCount.get(word);
                    wordCount.put(word, count + 1);
                } else {
                    wordCount.put(word, 1);
                }
            }
        }

        System.out.println("所有不重複單字：");
        System.out.println(uniqueWords);
        System.out.println("--------------------");
        System.out.println("所有單字統計：");

        for (String word : uniqueWords) {
            System.out.println(
                    word + "：" + wordCount.get(word) + " 次"
            );
        }

        System.out.println("--------------------");
        System.out.println("出現至少兩次的單字：");

        for (String word : uniqueWords) {

            if (wordCount.get(word) >= 2) {
                System.out.println(
                        word + "：" + wordCount.get(word) + " 次"
                );
            }
        }
    }
}