package Streams;

import java.util.*;

public class SecondHighest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50);

        Optional<Integer> secondHighest = list.stream()
                .distinct()
                .sorted((Collections.reverseOrder()))
                .skip(1)
                .findFirst();

        System.out.println(secondHighest);

        // for find the secondLongest length word in strig

        String sentence = "Hi I m the superman here to fight";

        int secondLongest = Arrays.stream(sentence.split("\\s+"))
                .map(String::length)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);

        System.out.println(secondLongest);

        String secondLongeststring = Arrays.stream(sentence.split("\\s+"))
                .filter(word -> word.length() == secondLongest)
                .findFirst()
                .orElse(" ");
        System.out.println(secondLongeststring);
    }
}
