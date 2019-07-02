package leetCode;
/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 *  prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
 *  find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2

Example 2:

Input: [3,1,3,4,2]
Output: 3

Note:

    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n2).
    There is only one duplicate number in the array, but it could be repeated more than once.


 * @author GaoYuan
 *
 */
public class FindTheDuplicateNumber {
	/*
	 * 理解1：错误的！
	 * n+1个整数，而且存放的只有1到n，而且只有一个重复的
	 * 数学方法，求和，大于和的值就是重复的值
	 * 编程方法可以更简化，每次减去i，最后的结果就是
	 * 理解2：没看到最后一句but it could be repeated more than once.。。。
	 * 评论区看的：真的想不到。。
	 * 看成是一个链表，入口是0，因为元素没有0，因此没有指向0的node，只能从0开始
	 * index->element,如果出现了两次以上，那么肯定有环，所以就等价于链表找换的问题，太强了
	 * 理解3：还有答案用二分法做，竟然也能对，还不太理解
	 */
	
	public int wrongfindDuplicate(int[] nums) {
        int res = 0;
		for (int i = 0; i < nums.length; i++) {
        	res += nums[i];
        	res -= i;
        }
		return res;
    }
	/*
	 * 利用两个指针的速度关系可以算得从初始点到第一次相遇处的距离就是环的距离
	 * 因此从初始点到环入口的距离就等于相遇处到环出口的距离。
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find the Duplicate Number.
Memory Usage: 36.2 MB, less than 96.07% of Java online submissions for Find the Duplicate Number.
	 */
	public int findDuplicate(int[] nums) {
        int slow = 0, quick = 0;
        do {
        	slow = nums[slow];
        	quick = nums[nums[quick]];
        } while (slow != quick);
        quick = 0;
        while (slow != quick) {
        	slow = nums[slow];
        	quick = nums[quick];
        }
        return slow;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
