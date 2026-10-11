package Streams;

import java.util.*;
import java.util.stream.Collectors;

public class FindEven {
    public static void main(String[] args)
    {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbes = numbers.stream().filter(n->n%2==0).collect(Collectors.toList());
        System.out.println(evenNumbes);

        // if we have to find the square of even numbers

        List<Integer> evenSquare = numbers.stream().filter(n-> n%2==0).map(n->n*n)
                .collect(Collectors.toList());

        System.out.println(evenSquare);

        // find the duplicate elements

        List<Integer> withDuplicates = Arrays.asList(1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9);
//        List<Integer> noDuplicate = withDuplicates.stream().distinct().collect(Collectors.toList());
        List<Integer> withOutDuplicate = withDuplicates.stream().filter( n->withDuplicates.indexOf(n)==withDuplicates.lastIndexOf(n)).collect(Collectors.toList());
        System.out.println(withOutDuplicate);

        List<Integer> Anumbers =
                Arrays.asList(10, 20, 10, 30, 20, 40, 50, 30);

        Set<Integer> seen = new HashSet<>();

        List<Integer> duplicates = Anumbers.stream()
                .filter(n ->!seen.add(n))
                .collect(Collectors.toList());

        System.out.println(duplicates);

    }
}
