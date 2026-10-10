package Stack;

/**
 * Finds the length of the longest well-formed parentheses substring.
 */
public class LongestValidParentheses {

    /**
     * Two-pass solution with constant extra space.
     */
    public static int longestValidParentheses(String s) {
        int longest = 0;
        int open = 0;
        int close = 0;

        // Left to right: catch prefixes with too many closing parentheses.
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                longest = Math.max(longest, 2 * close);
            } else if (close > open) {
                open = 0;
                close = 0;
            }
        }

        open = 0;
        close = 0;

        // Right to left: catch suffixes with too many opening parentheses.
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                longest = Math.max(longest, 2 * open);
            } else if (open > close) {
                open = 0;
                close = 0;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));     // 2
        System.out.println(longestValidParentheses(")()())"));  // 4
        System.out.println(longestValidParentheses(""));        // 0
    }
}
