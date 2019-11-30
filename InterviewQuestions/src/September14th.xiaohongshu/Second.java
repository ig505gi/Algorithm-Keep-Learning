package September14th.xiaohongshu;

import java.util.Deque;
import java.util.LinkedList;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-09-14 - 15:49
 */
public class Second {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        Deque<Character> queue = new LinkedList<>();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c != '(' && c != ')' && c != '<') {
                queue.offer(c);
            } else if (c == '<') {
                if (!queue.isEmpty()) {
                    queue.pollLast();
                }
            } else if (c == '(') {
                int count = 1;
                int j = i + 1;
                for (; j < in.length(); j++) {
                    if (in.charAt(j) == ')') count--;
                    if (in.charAt(j) == '(') count++;
                    if (count == 0) break;
                }
                i = j;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : queue) {
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
