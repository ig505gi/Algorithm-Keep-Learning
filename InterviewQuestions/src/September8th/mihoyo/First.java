package September8th.mihoyo;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-09-07 - 18:12
 */
public class First {
    /*
    题没看清
    一定有数字！
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        // A 65-90 a 97-122 对应 0-25
        int[] freq = new int[26];
        int i = 0;
        while (i < str.length()) {
            char curC = str.charAt(i);
            List<int[]> list = new ArrayList<>(); // 保存此次的所有start和end
            while (!isNum(curC)) {
                int[] startEnd = new int[2];
                startEnd[0] = curC;
                startEnd[1] = curC;
                if (i + 1 < str.length() && str.charAt(i + 1) == '-') {
                    i = i + 2;
                    startEnd[1] = str.charAt(i);
                }
                list.add(startEnd);
                curC = str.charAt(++i);
            }
            StringBuffer sb = new StringBuffer();
            sb.append(curC);
            while ( i + 1 < str.length() && isNum(str.charAt(i + 1))) {
                sb.append(str.charAt(++i));
            }
            int num = Integer.parseInt(sb.toString());
            for (int[] startEnds : list) {
                add(freq, startEnds[0], startEnds[1], num);
            }
            i++;
        }
        int max = 0;
        int j = 0;
        int maxj = 0;
        for (; j < freq.length; j++) {
            if (freq[j] > max) {
                max = freq[j];
                maxj = j;
            }
        }
        System.out.println(""+ (char)(maxj + 'a') + max);
    }

    private static boolean isNum(char c) {
        return  c >= '0' && c <= '9';
    }

//    private static boolean isLetter(char c) {
//        return (c >= 65 && c <=90) || (c >=97 && c <=122);
//    }

    private static void add(int[] freq, int start, int end, int num) {
        if (start >= 97) {
            for (int i = start; i <= end; i++) {
                freq[i - 97] += num;
            }
        } else {
            if (end <= 90) {
                for (int i = start; i <= end; i++) {
                    freq[i - 65] += num;
                }
            } else {
                for (int i = start; i <= 90; i++) {
                    freq[i - 65] += num;
                }
                for (int i = 97; i <= end; i++) {
                    freq[i - 97] += num;
                }
            }
        }
    }
}
