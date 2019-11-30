package leetCode;

import java.util.Stack;

/**
 * @author Gao Yuan
 * @date 2019-11-30 - 10:33
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
 * repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are
 * well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only
 * for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {

    public String decodeString(String s) {
        /*
        括号有可能嵌套，用栈
        改进1，最后的chars栈可以用String，每次重新放进去char可以变成放String 这样代码简单点。
        这样对于一个String的Stack，每次遇到一个char，需要这样：result.push(result.pop() + c);
        改进2：可以用递归
        改进3：Character有一个isDigit方法可以用来判断0~9
         */
        Stack<Integer> nums = new Stack<>();
        Stack<Character> chars = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (s.charAt(++i) >= '0' && s.charAt(i) <= '9') {
                    sb.append(s.charAt(i));
                }
                // 得到了这个数字，有可能不止一位此时i已经指向了下一个不是数字的位置
                nums.push(Integer.parseInt(sb.toString()));
            } else{
                if (c == ']') {
                    // 这里需要利用num去多次输入
                    int num = nums.pop();
                    StringBuilder revRepeatS = new StringBuilder();
                    while (!chars.isEmpty() && chars.peek() != '[') {
                        revRepeatS.append(chars.pop());
                    }
                    if (!chars.isEmpty()) chars.pop(); // 把[也吐出来
                    String repeatS = revRepeatS.reverse().toString();
                    for (; num >0 ; num--) {
                        for (int j = 0; j < repeatS.length(); j++) {
                            chars.push(repeatS.charAt(j));
                        }
                    }
                } else {
                    // 只能是数字或者[
                    chars.push(c);
                }
                i++;
            }
        }
        StringBuilder revRepeatString = new StringBuilder();
        while (!chars.isEmpty()) {
            revRepeatString.append(chars.pop());
        }
        return revRepeatString.reverse().toString();
    }

    public static void main(String[] args) {
        DecodeString solution = new DecodeString();
        System.out.println(solution.decodeString("2[a2[c]]"));
    }
}
