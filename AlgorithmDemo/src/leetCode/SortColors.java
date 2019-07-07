package leetCode;
/**
 * Dutch national flag problem. [Edsger Dijkstra]
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Sort Colors.
Memory Usage: 37.3 MB, less than 56.91% of Java online submissions for Sort Colors.
 * @author GaoYuan
 *
 */
public class SortColors {
	
	public void sortColors(int[] nums) {
		int i = 0;
		int l = 0;
		int r = nums.length - 1;
		while (i <= r) {
			if (nums[i] == 2) {
				int temp = nums[i];
				nums[i] = nums[r];
				nums[r--] = temp;
			} else if (nums[i] == 0) {
				int temp = nums[l];
				nums[l++] = nums[i];
				nums[i++] = temp;
			} else {
				i++;
			}
		}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
