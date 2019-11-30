package August22th.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-22 - 14:55
 */
public class First {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        List<Integer> res = new ArrayList<>();
        int[] hash = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i ++){
            hash[chars[i] - 'a'] = i;//记录每个字符最后出现的下标
        }
        int start = 0;
        int end = 0;
        for (int i = 0;i < chars.length;i ++){
            end = Math.max(end,hash[chars[i] - 'a']);//记录当前子串的结尾
            if (end == i){
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            System.out.print(res.get(i));
            if (i != s.length() - 1) System.out.print(" ");
        }
    }
}
