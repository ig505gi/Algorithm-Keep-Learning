package leetCode;

import java.util.Random;

/**
 * @author Gao Yuan
 * @date 2019-07-24 - 20:26
 */
public class FindKthLargest {

    /*
    记得剑指Offer做过，用快排的思想来做
    Runtime: 38 ms, faster than 14.91% of Java online submissions for Kth Largest Element in an Array.
Memory Usage: 38.5 MB, less than 40.14% of Java online submissions for Kth Largest Element in an Array.

加入了随机之后。。
Runtime: 2 ms, faster than 89.15% of Java online submissions for Kth Largest Element in an Array.
Memory Usage: 36.8 MB, less than 93.82% of Java online submissions for Kth Largest Element in an Array.
     */
    public int findKthLargest(int[] nums, int k) {
        // 快排要随机！！
        shuffle(nums);
        // 这点要注意 k - 1的下标才是nums中第几大的
        return quickFind(nums, k - 1, 0, nums.length - 1);
    }

    public int quickFind(int[] nums, int k, int start, int end) {
        if (start == end) return nums[start];
        int flag = nums[start];
        int firstRight = start + 1;
        for (int i = start + 1; i <= end; ++i) {
            // 这里要注意从前往后是由大到小
            if (nums[i] > flag) {
                swap(nums, i, firstRight++);
            }
        }
        int mid = firstRight - 1;
        swap(nums, start, mid);
        if (k == mid) {
            return nums[mid];
        } else if (k > mid){
            return quickFind(nums, k, mid + 1, end);
        } else {
            return quickFind(nums, k, start, mid - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    // 快排的关键 需要洗牌！
    private void shuffle(int a[]) {
        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            swap(a, ind, r);
        }
    }

    public static void main(String[] args) {
        FindKthLargest solution = new FindKthLargest();
        int[] nums = {3,2,1,5,6,4,5,6};
        System.out.println(solution.findKthLargest(nums, 2));
    }
}
