package April10th.interviewHuawei;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
/**
 * as2{aqd3t2[io3(v)qe4(cr)]}
rcrcrcrceqvvvoircrcrcrceqvvvoitttdqarcrcrcrceqvvvoircrcrcrceqvvvoitttdqasa
 * @author GaoYuan
 * 又多花了40分钟才写完。。。如果不写最后一道题，这道可能还能得个满分。。
 */
public class Second {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String token = in.next();
        in.close();
        List<Character> num = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        HashMap<Character, Character> op = new HashMap<Character, Character>();
        op.put('}', '{');
        op.put(']', '[');
        op.put(')', '(');
        Stack<Character> stack = new Stack<Character>();     
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
        	// 如果是数字，下一个不是左括号，保证了没有数字+字母
            if (num.contains(c) && !op.containsValue(token.charAt(i + 1))) {
            	for (char n = '0'; n < c; n++) {
            		stack.push(token.charAt(i + 1));
            	}
            	i++;
            	continue;
            } 
            // 如果是右括号
            if (op.keySet().contains(c)) {
            	StringBuilder temp = new StringBuilder();
            	// stack的peek不为
            	while (op.get(c) != stack.peek()) {
            		temp.append(stack.pop());
            	}
            	temp.reverse();
            	// 吐出左括号
            	stack.pop();
            	char numb = '1';
            	// 如果peek为数字，需要
            	if (num.contains(stack.peek())) {
            		numb = stack.pop();	
            	}
            	// 将 temp 重复numb次放进去，如果上一个不是数字，就重复一次
        		for (char n = '0'; n < numb; n++) {
            		for (int j = 0; j < temp.length(); j++) {
            			stack.push(temp.charAt(j));
        			}
            	}
        		continue;
            }
            // 如果是字母或者数字后面是左括号，或者左括号 就放进去
            stack.push(c);
        }
        while (!stack.isEmpty()) {
        	System.out.print(stack.pop());
        }
    }
}
