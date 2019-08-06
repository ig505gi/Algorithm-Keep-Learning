package leetCode;

import java.util.Stack;

/**
 * @author Gao Yuan
 * @date 2019-08-06 - 14:56
 */
public class ValidParentheses {
    /*
    Runtime: 2 ms, faster than 61.12% of Java online submissions for Valid Parentheses.
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Valid Parentheses.
最简单的栈的方法，都要写三次成功。。。
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        //

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' ||c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                if ((c == ')' && stack.peek() == '(')||
                        (c == '}' && stack.peek() == '{')||
                        (c == ']' && stack.peek() == '[')){
                    stack.pop();
                } else return false;
            }
        }
        return stack.isEmpty();
    }

    /*
    Runtime: 1 ms, faster than 98.48% of Java online submissions for Valid Parentheses.
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Valid Parentheses.
竟然优化了这么多
     */
    public boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;
        Stack<Character> p = new Stack<>();
        for (char c: s.toCharArray()) {
            switch (c) {
                case '{': { p.push('}'); break; }
                case '(': { p.push(')'); break; }
                case '[': { p.push(']'); break; }
                case '}': case ']': case ')':
                    if (p.isEmpty() || p.peek() != c) return false;
                    else p.pop();
            }

        }
        return p.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        System.out.println(solution.isValid2("()"));
    }
}
