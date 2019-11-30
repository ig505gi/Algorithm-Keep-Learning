package September10th.fanruan;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-09-10 - 18:57
 */
public class Main {

    public static void main1(String[] args) {
        // 读取
        Scanner sc = new Scanner(System.in);
        List<Long> arr = new ArrayList<>();
        while (sc.hasNextLong()) {
            arr.add(sc.nextLong());
        }
        Integer[] nums = new Integer[arr.size()];
        arr.toArray(nums);
        // 算法
        int maxPos = nums[0], maxNeg = nums[0], max, res = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(maxPos * nums[i], Math.max(maxNeg * nums[i], nums[i]));
            maxNeg = Math.min(maxPos * nums[i], Math.max(maxNeg * nums[i], nums[i]));
            maxPos = max;
            res = Math.max(maxPos, res);
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        // 读取
        Scanner sc = new Scanner(System.in);
        List<Integer> arr = new ArrayList<>();
        while (sc.hasNextInt()) {
            arr.add(sc.nextInt());
        }
        Integer[] nums = new Integer[arr.size()];
        arr.toArray(nums);
        // 算法
        int[] maxPos = new int[nums.length]; // maxPos[i],是指以i结尾，连成得到的最大正值
        int[] maxNeg = new int[nums.length]; // maxNeg[i],是指以i结尾，连成得到的最大负值
        if (nums[0] < 0) maxNeg[0] = nums[0];
        else maxPos[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) { // 如果这个数大于0
                maxPos[i] = maxPos[i - 1] == 0 ? nums[i] : (maxPos[i - 1] * nums[i]);
                maxNeg[i] = maxNeg[i - 1] == 0 ? 0 : (maxNeg[i - 1] * nums[i]);
            } else if (nums[i] < 0) {
                maxNeg[i] = maxPos[i - 1] == 0 ? nums[i] : (maxPos[i - 1] * nums[i]);
                maxPos[i] = maxNeg[i - 1] == 0 ? 0 : (maxNeg[i - 1] * nums[i]);
            } else {
                maxPos[i] = 0;
                maxNeg[i] = 0;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int m : maxPos) {
            max = Math.max(max, m);
        }
        System.out.println(max);
    }

    public static void main2(String[] args) {
        // 读取
        Scanner sc = new Scanner(System.in);
        List<Integer> arr = new ArrayList<>();
        while (sc.hasNextInt()) {
            arr.add(sc.nextInt());
        }
        Integer[] nums = new Integer[arr.size()];
        arr.toArray(nums);
        // 算法
        int[] left = new int[nums.length]; // 保存的是从1-i的乘积
        int[] right = new int[nums.length]; // 保存的是从i到n的乘积
        left[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i];
        }
        right[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0 ; i--) {
            right[i] = right[i + 1] * nums[i];
        }
        int max = Integer.MIN_VALUE;
        int res = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            res *= left[i] * right[i + 1];
            max = Math.max(max, res);
        }

        System.out.println(res);
    }

}
