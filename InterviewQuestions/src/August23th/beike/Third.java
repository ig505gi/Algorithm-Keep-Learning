package August23th.beike;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-23 - 19:29
 */
public class Third {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long total = in.nextLong();
        int[] nums = new int[n];
        int[] volums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            volums[i] = in.nextInt();
        }
        long sum = 0;
        long res = 0;
        long temp = 0;
        for (int i = 0; i < n; i++) {
            temp = nums[i] * volums[i];
            if (temp + sum > total){
                res += ((total - sum) / volums[i]);
                sum += ((total - sum) / volums[i]) * volums[i];
                continue;
            }else{
                sum += temp;
                res += nums[i];
            }
        }
        System.out.println(res);
    }
}
