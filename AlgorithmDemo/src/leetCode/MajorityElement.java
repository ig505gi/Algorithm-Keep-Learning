package leetCode;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element. 
 * The majority element is the element that appears more 
 * than ⌊ n/2 ⌋ times.
   You may assume that the array is non-empty and the majority 
   element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3

Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2


 * @author GaoYuan
 * 思路1: 排序，找到中间未知的值，一定是majority. 复杂度nlogn
 * 思路2：（剑指offer上看过的。。忘了）因为超过了一半，
 * 是maj的时候，count++，不是的时候count--,而且该思路只适用于有maj的时候，
 * 没有的话返回的不是maj
 */
public class MajorityElement {
	/**
	 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Majority Element.
Memory Usage: 40.6 MB, less than 96.12% of Java online submissions for Majority Element.
	 * @param nums
	 * @return
	 */
	public int majorityElement(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
		return nums[n/2];
    }
	/**
	 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Majority Element.
Memory Usage: 41.6 MB, less than 50.77% of Java online submissions for Majority Element.
	时间复杂度O(n)
	 * @param nums
	 * @return
	 */
	public int majorityElement2(int[] nums) {
        int n = nums.length;
        int maj = nums[0], count = 1;
        for (int i = 1; i < n; i++) {
        	if (count == 0) {
        		maj = nums[i];
        		count = 1;
        	} else if (maj == nums[i]) {
        		count++;
        	} else count--;
        }
		return maj;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
