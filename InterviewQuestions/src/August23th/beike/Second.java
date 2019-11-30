package August23th.beike;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-23 - 19:29
 */
public class Second {
    /*
    求next数组的变体
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int n = in.nextInt();
        in.nextLine();
        String s = in.nextLine();
        int[] next = new int[s.length() + 1];
        next[0] = -1;
        if (next.length > 1) next[1] = 0;
        for (int i = 2; i < next.length; i++) {
            int idx = i - 1;
            int preIdx = next[i - 1];
            while (preIdx >= 0) {
                if (s.charAt(preIdx) == s.charAt(idx)) {
                    next[i] = next[preIdx] + 1;
                    break;
                } else {
                    // idx = preIdx;
                    preIdx = next[preIdx];
                }
            }
            if (preIdx < 0) next[i] = 0;
        }
        int start = next[next.length - 1];
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        for (int i = 1; i < n; i++) {
            sb.append(s.substring(start));
        }
        System.out.println(sb.toString());
    }
}
