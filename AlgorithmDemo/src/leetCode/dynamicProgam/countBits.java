package leetCode.dynamicProgam;
/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]

Example 2:

Input: 5
Output: [0,1,1,2,1,2]

 * @author GaoYuan
 *
 */
public class countBits {

	public int[] soulution1(int num) {
        if (num == 0) {
            int[] c = {0};
            return c; 
        }
        int[] counts = new int[num+1];
        counts[0] = 0;
        int q = 0;
        int k = 0;
        for (int i = 1; i< num+1; i++){
            if (i == Math.pow(2, q)) {
                k = 0;
                q++;
            }
            counts[i] = counts[k++] + 1;
        }
        return counts;
    }
	/**
	 * pow没必要，2计算过的结果还是可以保存下来！ 速度非常快
	 * 时间100%，空间99%
	 * @param num
	 * @return
	 */
	public int[] solution2(int num) {
        if (num == 0) {
            int[] c = {0};
            return c; 
        }
        int[] counts = new int[num+1];
        counts[0] = 0;
        counts[1] = 1;
        int q = 2;
        int k = 0;
        for (int i = 2; i< num+1; i++){
            if (i == q) {
                k = 0;
                q *= 2;
            }
            counts[i] = counts[k++] + 1;
        }
        return counts;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
