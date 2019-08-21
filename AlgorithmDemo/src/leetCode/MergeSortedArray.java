package leetCode;

/**
 * @author Gao Yuan
 * @date 2019-08-21 - 16:21
 */
public class MergeSortedArray {
    /*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Sorted Array.
Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Merge Sorted Array.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        for (int k = m + n - 1; k > i; k--) {
            if ((i < 0 ? Integer.MIN_VALUE : nums1[i]) >=
                    (j < 0 ? Integer.MIN_VALUE : nums2[j])) {
                nums1[k] = nums1[i--];
            } else {
                nums1[k] = nums2[j--];
            }
        }
    }
}
