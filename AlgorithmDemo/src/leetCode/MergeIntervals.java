package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

 * @author GaoYuan
 * 1.数组[start][end] 按照 start排序
 * 2.遍历 如果start[i]<=end[i-1] 那么i和需要融合i-1
 * 融合后，start[i]= start[i-1],进行下一轮循环
 * 如果不满足，说明集合后面的都没有交集，输出一个区间
 */
public class MergeIntervals {
	/*
	 * Runtime: 38 ms, faster than 28.24% of Java online submissions for Merge Intervals.
Memory Usage: 41.1 MB, less than 74.98% of Java online submissions for Merge Intervals.
		
		错了一次，忘记考虑【1，4】，【2，3】这种情况
	 */
	public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) return intervals;
		Arrays.sort(intervals, (p, q)->{
        	return p[0] != q[0]?p[0] - q[0]:p[1] - q[1];});
        List<int[]> resList = new ArrayList<int[]>();
		for (int i = 1; i < intervals.length; i++) {
        	if (intervals[i][0] <= intervals[i-1][1]) {
        		intervals[i][0] = intervals[i-1][0];
        		intervals[i][1] = Math.max(intervals[i-1][1], intervals[i][1]);
        	} else {
        		resList.add(intervals[i-1]);
        	}
        }
		resList.add(intervals[intervals.length - 1]);
		int[][] res = new int[resList.size()][2];
		for (int i = 0; i < res.length; i++) {
			res[i][0] = resList.get(i)[0];
			res[i][1] = resList.get(i)[1];
		}
		return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
