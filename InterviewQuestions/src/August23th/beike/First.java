package August23th.beike;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-23 - 19:29
 */
public class First {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        Arrays.sort(nums);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] + sum <= s){
                sum += nums[i];
                res = i + 1;
            }
        }
        System.out.println(res);
    }
}
