package leetCode;

/**
 * @author Gao Yuan
 * @date 2019-08-05 - 11:07
 */
public class MedianOfTwoSortedArrays {

    /*
    避免这种代码，陷入太多的判断，要找更加通用的方法
     */
    public double Wrong(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            int mid = nums2.length / 2;
            return 1.0 * (nums2[mid] + (nums2.length % 2 == 0 ? nums2[mid - 1] : nums2[mid])) / 2;
        }
        if (nums2 == null || nums2.length == 0) {
            int mid = nums1.length / 2;
            return 1.0 * (nums1[mid] + (nums1.length % 2 == 0 ? nums1[mid - 1] : nums1[mid])) / 2;
        }
        int i = 0;
        int j = 0;
        int n = nums1.length + nums2.length;
        for (int k = 0; k < n / 2; k++) {
            if (nums1[i] <= nums2[j]) {
                if (i < nums1.length - 1)i++;
            }
            else {
                if (j < nums2.length - 1)j++;
            }
        }
        if (nums1[i] <= nums2[j]) {
            int mid = nums1[i];
            if (n % 2 != 0) return mid;
            return 1.0 * (mid + (nums1[i - 1] <= nums2[j] ? nums2[j] : nums1[i - 1])) / 2;
        } else {
            int mid = nums2[j];
            if (n % 2 != 0) return mid;
            return 1.0 * (mid + (nums1[i] <= nums2[j - 1] ? nums2[j - 1] : nums1[i])) / 2;
        }
    }

    /*
    评论区方法
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        if(len % 2 == 0){
            // 写递归要明确一个含义，该函数是找到第k个数，因此使用两遍，代码上简单许多，
            // 通用性很强，可以找到两个数组中第k大的，二分
            // 避免了之前的复杂判断，复杂度并不会上升太多
            double left =  (double)findKthHelper(nums1, 0, nums2, 0, len/2);
            double right =  (double)findKthHelper(nums1, 0, nums2, 0, len/2 + 1);
            return (double)(left + right)/2;
        }else{
            return findKthHelper(nums1, 0, nums2, 0, len/2 + 1);
        }
    }
    private int findKthHelper(int[] A, int aStart, int[] B, int bStart, int k){
        if(aStart >= A.length){ // 其中一个到头的情况很简单
            return B[bStart + k - 1];
        }
        if(bStart >= B.length){
            return A[aStart + k - 1];
        }
        if(k == 1){
            return Math.min(A[aStart], B[bStart]);
        }
        // 因为减去的是k/2，所以必有一个val不是MAX_VALUE，
        // 相当于两个同时走k/2步，但是针对A和B的总和len，一开始只走了len/4
        int aMid = aStart + k/2 - 1;
        int bMid = bStart + k/2 - 1;
        int aVal = aMid >= A.length ? Integer.MAX_VALUE : A[aMid];
        int bVal = bMid >= B.length ? Integer.MAX_VALUE : B[bMid];
        if(aVal <= bVal){
            return findKthHelper(A, aMid + 1, B, bStart, k - k/2);
        }else{
            return findKthHelper(A, aStart, B, bMid + 1, k - k/2);
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
        System.out.println(solution.findMedianSortedArrays(new int[]{1,3,4,5,6}, new int[]{2,4,5,6,7}));
    }

}
