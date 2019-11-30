package September4th.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Second {

    /*

     */
    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static String resolve(String expr) {
        Stack<Character> stack = new Stack<>();
        // 遇到 右括号则反转
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == ')') {
                while (stack.peek() != '(') {
                    queue.offer(stack.pop());
                    if (stack.isEmpty()) return null; // 左括号没有匹配到合适的右括号，无效
                }
                stack.pop(); // 吐出来 (
                while (!queue.isEmpty()) {
                    stack.push(queue.poll());
                }
            } else {
                stack.push(c);
            }
        }
        // 把括号已经除去，如果还有括号，说明出错
        Stack<Character> res = new Stack<>();
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') return null;
            res.push(stack.pop());
        }
        StringBuffer sb = new StringBuffer();
        while (!res.isEmpty()) {
            sb.append(res.pop());
        }
        return sb.toString();
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String res;

        String _expr;
        try {
            _expr = in.nextLine();
        } catch (Exception e) {
            _expr = null;
        }

        res = resolve(_expr);
        System.out.println(res);
    }
}

