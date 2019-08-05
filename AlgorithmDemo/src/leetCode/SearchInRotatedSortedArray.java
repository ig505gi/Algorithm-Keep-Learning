package leetCode;

/**
 * @author Gao Yuan
 * @date 2019-08-01 - 12:25
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
 * Memory Usage: 39.4 MB, less than 16.50% of Java online submissions for Search in Rotated Sorted Array.
 * 错了快十次。。。。。。历史最惨，等于的情况考虑的实在是太周全了
 */
public class SearchInRotatedSortedArray {
    /*
    剑指offer原题，并且没有重复的，简单些，
    用二分法，如果
    !!!!!! 一开始用的迭代，有可能在两边都出现，但是target和mid比了之后，再和end比一下就能确定在哪边了！！！
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 当left 和 right只差 1 的时候 得到的mid是left，如果，因为我们每次都用的left和target比
            // 因此当 mid == target的情况，只能是在else里，必须是 right - 1， 而不是left + 1
            // 如果是left+1，就跳过了 == 的情况
            int mid = (right + left) / 2;
            if (nums[right] < nums[mid]) { // 表头在右边
                if (target > nums[mid] || target <= nums[right]) left = mid + 1;
                else right = mid;
            } else {
                if (target > nums[mid] && target <= nums[right]) left = mid + 1;
                else right = mid;
            }
        }
        if (left == right && nums[left] != target) return -1;
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int[] nums2 = {1,3};
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
        System.out.println(solution.search(nums2, 3));
    }
}
