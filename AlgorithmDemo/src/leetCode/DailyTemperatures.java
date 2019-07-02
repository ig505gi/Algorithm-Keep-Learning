package leetCode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *  Given a list of daily temperatures T, return a list such that, for each day in the input, 
 *  tells you how many days you would have to wait until a warmer temperature. 
 *  If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
 your output should be [1, 1, 4, 2, 1, 1, 0, 0]. 
 * @author GaoYuan
 *
 */
public class DailyTemperatures {
	/**
	 * Runtime: 26 ms, faster than 79.06% of Java online submissions for Daily Temperatures.
Memory Usage: 42.8 MB, less than 85.10% of Java online submissions for Daily Temperatures.
	果然太久不写，想的太复杂了，既然T没有变，用stack存放index就好 可以直接用T[index]就能查到温度
	 * @param T
	 * @return
	 */
	public int[] dailyTemperatures(int[] T) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				// o[0]存放的是温度，0[1]存放的是Index,温度小的返回1，放在前面
				return o1[0] - o2[0];
			}
		});
        int[] res = new int[T.length];
        pq.add(new Integer[]{T[0], 0});
        for (int i = 1; i < T.length; i++) {
        	int Tnow = T[i];
        	while (!pq.isEmpty()) {
        		if (pq.peek()[0] < Tnow) {
        			Integer[] t = pq.poll();
        			res[t[1]] = i - t[1];
        		} else break;
        	}
        	pq.add(new Integer[]{T[i], i});
        }
        res[res.length - 1] = 0;
        return res;
    }
	/**
	 * Runtime: 41 ms, faster than 71.20% of Java online submissions for Daily Temperatures.
Memory Usage: 42.9 MB, less than 83.51% of Java online submissions for Daily Temperatures.
	评论区 还可以用array来模拟stack，会更快！
	 * @param temperatures
	 * @return
	 */
	public int[] dailyTemperatures2(int[] temperatures) {
	    Stack<Integer> stack = new Stack<>();
	    int[] ret = new int[temperatures.length];
	    for(int i = 0; i < temperatures.length; i++) {
	        while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
	            int idx = stack.pop();
	            ret[idx] = i - idx;
	        }
	        stack.push(i);
	    }
	    return ret;
	}
	public static void main(String[] args) {
		DailyTemperatures solution = new DailyTemperatures();
		int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
		int[] res = solution.dailyTemperatures(T);
		//System.out.println(solution.dailyTemperatures(T));
		for (int i=0; i <res.length; i++) {
			System.out.print(res[i]+ ", ");
		}
		
	}

}
