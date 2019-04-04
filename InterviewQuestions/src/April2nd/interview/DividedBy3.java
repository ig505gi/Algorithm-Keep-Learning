package April2nd.interview;

import java.util.Scanner;

/**
 * 

小Q得到一个神奇的数列: 1, 12, 123,...12345678910,1234567891011...。

并且小Q对于能否被3整除这个性质很感兴趣。

小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。

输入描述:

输入包括两个整数l和r(1 <= l <= r <= 1e9), 表示要求解的区间两端。


输出描述:

输出一个整数, 表示区间内能被3整除的数字个数。


输入例子1:

2 5


输出例子1:

3


例子说明1:

12, 123, 1234, 12345...
其中12, 123, 12345能被3整除。


 * @author GaoYuan
 *
 */
public class DividedBy3 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int left = in.nextInt();
		int right = in.nextInt();
		in.close();
		if (right == 0 || left > right) {
	         System.out.println(0);
	    }
	    else{
			// 保存余数的数组
			int[] mod = new int[right + 1];
			// 从第一个开始算，把余数保存下来，每次循环，
			// 需要先判断index的位数，然后乘以10的位数次方，然后加上index
			// 位数在遍历的过程中也可以保存
			int tens = 10;
			for (int index = 1; index <= right; index++) {
				if (index % tens == 0) {
					tens *= 10;
				}
				mod[index] = (mod[index - 1] * tens + index) % 3;
			}
			int res = 0;
			for (int i = left; i <= right; i++) {
				if (mod[i] == 0) res++;
			}
			System.out.println(res);	
		}
	}
}
