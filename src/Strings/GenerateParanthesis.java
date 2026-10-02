package Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//
//
//Example 1:
//
//Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
//Example 2:
//
//Input: n = 1
//Output: ["()"]
public class GenerateParanthesis {

    public static List<String> generateParenthesis(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        List<String> result = new ArrayList<>();
        build(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private static void build(List<String> result, StringBuilder current,
                              int openCount, int closeCount, int pairs) {
        if (current.length() == pairs * 2) {
            result.add(current.toString());
            return;
        }

        // We can add '(' while fewer than n opening brackets have been used.
        if (openCount < pairs) {
            current.append('(');
            build(result, current, openCount + 1, closeCount, pairs);
            current.deleteCharAt(current.length() - 1); // backtrack
        }

        // A ')' is valid only when it closes an existing '('.
        if (closeCount < openCount) {
            current.append(')');
            build(result, current, openCount, closeCount + 1, pairs);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(generateParenthesis(sc.nextInt()));
    }
}
