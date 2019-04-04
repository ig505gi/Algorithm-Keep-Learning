package April2nd.interview;

import java.util.Arrays;
import java.util.Map;
/**
 * 每个输入包含一个测试用例。
每个测试用例的第一行包含两个正整数，分别表示工作的数量N(N<=100000)和小伙伴的数量M(M<=100000)。
接下来的N行每行包含两个正整数，分别表示该项工作的难度Di(Di<=1000000000)和报酬Pi(Pi<=1000000000)。
接下来的一行包含M个正整数，分别表示M个小伙伴的能力值Ai(Ai<=1000000000)。
保证不存在两项工作的报酬相同。
输出描述:

对于每个小伙伴，在单独的一行输出一个正整数表示他能得到的最高报酬。一个工作可以被多个人选择。

输入例子1:

3 3 
1 100 
10 1000 
1000000000 1001 
9 10 1000000000


输出例子1:

100 
1000 
1001
 * @author GaoYuan
 * 
 */
import java.util.Scanner;
import java.util.TreeMap;
public class FindJob {

	public static void main(String[] args) {
		FindJob fd = new FindJob();
		fd.solution();
		
		// read data
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		Map<Integer, Integer> jobs = new TreeMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			int d = in.nextInt();
			int p = in.nextInt();
			jobs.put(p, d);
		}
//		for (int d: jobs.keySet()) {
//			System.out.println(d+ ": " + jobs.get(d));
//		}
		// 拿出keySet进行倒序查找
		Object[] salary = jobs.keySet().toArray();
		int[] res = new int[m];
		for (int i = 0; i < m; i++) {
			// friends[i] = in.nextInt();
			int cap = in.nextInt();
			for (int j = n - 1; j>= 0; --j) {
				if (cap >= jobs.get(salary[j])) {
					res[i] = (Integer) salary[j]; 
					break;
				}	
			}
		}
		in.close();
		for (int i = 0; i < m; i++) {
			System.out.println(res[i]);
		}
	}
	/**
	 * 思路: 更新難度值對應的報酬為所有小於等於其難度的報酬中報酬最大的值

    數組按難度值從小到大排序 O(N*logN)

    更新每個難度對應的報酬 O(N)

    使用TreeMap存儲(難度，報酬)對

    對每一個小夥伴的ability, 取出小於或等於其ability中難度最大的key，按照key取出報酬(注意 key 可能爲空)
    總的時間複雜度: O(N*logN)
	 *
	 *网友的解答，但是最重要的就是第一个思路
	 *用 
	  Arrays.sort(arr, (e1, e2) -> (int)(e1[0] - e2[0]));
	  for(int i = 1; i < arr.length; i++) {
          arr[i][1] = Math.max(arr[i-1][1], arr[i][1]);
      }
     *来实现难度值对应的报酬中小于等于其难度的报酬最大的值
	 */
	public void solution() {
		Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] arr = new int[N][2];
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for(int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, (e1, e2) -> (int)(e1[0] - e2[0]));
        for(int i = 1; i < arr.length; i++) {
            arr[i][1] = Math.max(arr[i-1][1], arr[i][1]);
        }
        for(int i = 0; i < arr.length; i++) {
            map.put(arr[i][0], arr[i][1]);
        }
        for(int i = 0; i < M; i++) {
            int ability = sc.nextInt();
            Integer index = map.floorKey(ability);
            if(index != null) {
                System.out.println(map.get(index));
            } else {
                System.out.println(0);
            }
        }
        sc.close();
	}

}
