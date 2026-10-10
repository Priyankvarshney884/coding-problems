package HashMap;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyWord {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        Map<String, Integer> map = new HashMap<>();

        String[] words = sentence.toLowerCase().split("\\s+");

//        for(String word : words)
//        {
//            map.put(word, map.getOrDefault(word, 0)+1);
//        }
//        System.out.println(map);

        Map<String, Long> wordCount = Arrays.stream(sentence.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println(wordCount);
    }

    // with the help of streams


}
