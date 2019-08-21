package August20th.bilibili;

import java.util.Arrays;

/**
 * @author Gao Yuan
 * @date 2019-08-20 - 20:33
 */
public class Second {
    public static void main(String[] args) {
        String[] strs = {"232", "22", "122", };
        Arrays.sort(strs, (a, b) ->{
            long res = Long.parseLong(a + b) - Long.parseLong(b + a);
            if (res >= 0) return 1;
            else return -1;
        });

        System.out.println();
    }
}
