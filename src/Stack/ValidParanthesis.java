package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParanthesis {
    public static void main(String[] args)
    {
        String s = "{([])}";
        System.out.println(isValidChars(s));
    }

    public static boolean isValidChars(String s) {

        if(s.length()%2!=0)
            return false;

        char[] stack = new char[s.length()];
        int top = -1;

        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='(' || s.charAt(i)=='[' || s.charAt(i)=='{')
            {
                stack[++top] = s.charAt(i);
            }
            else
            {
                if(top==-1)
                    return false;
                char c = stack[top--];

                if((c=='(' && s.charAt(i)!=')') || (c=='[' && s.charAt(i)!=']') || (c=='{' && s.charAt(i)!='}'))
                    return false;
            }
        }
        return top == -1;


    }

    public static boolean isValidStack(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char current : s.toCharArray()) {
            if (current == '(' || current == '[' || current == '{') {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char opening = stack.pop();
                if (!matches(opening, current)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean matches(char opening, char closing) {
        return (opening == '(' && closing == ')')
                || (opening == '[' && closing == ']')
                || (opening == '{' && closing == '}');
    }

}
