package leetCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Gao Yuan
 * @date 2019-08-06 - 16:43
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * 排好序的，而且是不相交的区间
 */

public class InsertInterval {
    // 肯定要遍历一遍复制数组 应该不可能有nlogn的解法
    /*
    还没写好！！！分类讨论太差了
     */
    public int[][] wrong1(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] res = {{newInterval[0], newInterval[1]}};
            return res;
        }
        if (newInterval.length == 0) return intervals;
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0] || intervals[i][0] > newInterval[1]) {
                // 同时满足的情况下 不用merge需要添加
                if (intervals[i][1] < newInterval[0] && intervals[i][0] > newInterval[1]) {
                    start.add(newInterval[0]); end.add(newInterval[1]);
                }
                start.add(intervals[i][0]);
                end.add(intervals[i][1]);
            } else {
                // 第一次进入 此时的 inter[i][1] >= new[0]
                // 确定merge后的start
                start.add(Math.min(intervals[i][0], newInterval[0]));
                while (i < intervals.length-1 && intervals[++i][0] <= newInterval[1]) {}
                if (intervals[i][0] > newInterval[1]) { i--;}
                end.add(Math.max(newInterval[1], intervals[i][1]));
            }
        }
        int[][] res = new int[start.size()][2];
        for (int i = 0; i < start.size(); i++) {
            res[i][0] = start.get(i);
            res[i][1] = end.get(i);
        }
        return res;
    }

    /*
    思路2：所有的点按照大小排序！
    ----------!!!!!仍然错误
     */
    public int[][] wrong2(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] res = {{newInterval[0], newInterval[1]}};
            return res;
        }
        if (newInterval.length == 0) return intervals;
        int[] arr = new int[intervals.length * 2];
        for (int i = 0, j = 0; i < intervals.length; i++) {
            arr[j++] = intervals[i][0];
            arr[j++] = intervals[i][1];
        }
        // 没有的话 返回的是 - (insertpos - 1);
        int idxL = Arrays.binarySearch(arr, newInterval[0]);
        int idxR = Arrays.binarySearch(arr, newInterval[1]);
        // 相等，不用merge的情况 [1,1]
        if (idxL == idxR && idxL >= 0) return intervals;
        List<Integer> indexs = new ArrayList<>();
        int posL = idxL >= 0 ? idxL : (-idxL - 1);
        int posR = idxR >= 0 ? idxR : (-idxR - 1);
        for (int i = 0; i < arr.length; i++) {
            if (i == posL - 1) {
                if (idxL == idxR) { // 负的 两个相等的，不用merge直接添加
                    indexs.add(newInterval[0]);
                    indexs.add(newInterval[1]);
                } else { // 开始奇偶讨论
                    int start, end;
                    if (posL % 2 == 0) start = newInterval[0];
                    else start = arr[posL - 1];
                    if (posR % 2 == 0) {
                        if (idxR >= 0) { end = arr[posR + 1]; i = posR + 1;}
                        else { end = newInterval[1]; i = posR - 1; }
                    } else { end = arr[posR]; i = posR; }
                    indexs.add(start); indexs.add(end);
                }
                continue;
            }
            indexs.add(arr[i]);
        }
        int[][] res = new int[indexs.size() / 2][2];
        for (int i = 0, j = 0; i < res.length; i++) {
            res[i][0] = indexs.get(j++);
            res[i][1] = indexs.get(j++);
        }
        return res;
    }
    /*
    直接copy评论区的方法，思路上是差不多的，但是实现细节上
    1. 多return，直接toArray,这点可以保证在不同的地方就可以输出
    2. newInterval[0] = Math.min(intervals[i][0], newInterval[0]); 这两行代码，避免了很多分类讨论
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i;
        for (i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0]) res.add(intervals[i]);
                // current interval is strictly less than the newInterval, so just add the current interval
            else if (intervals[i][0] > newInterval[1]) { // there are no overlaps between the newInterval and the
                // rest of them, so we can just add the newInterval and then the rest of the intervals
                // sequentially and be done
                res.add(newInterval);
                for (; i < intervals.length; i++) {
                    res.add(intervals[i]);
                }
                return res.toArray(new int[res.size()][]);
            }
            else break; // there is some intervals that are overlapping and we have to handle them
        }

        for (; i < intervals.length; i++) {
            if (intervals[i][0] >= newInterval[0] && intervals[i][1] <= newInterval[1]) continue; // the
                // currentInterval is strictly overshadowed by the newInterval, so there is nothing we can do
                // with it
            else if (intervals[i][0] > newInterval[1]) { // there are no more overlapping between the current and
                // new intervals, so like above, add newInterval and then rest of the intervals sequentially and
                // then return them
                res.add(newInterval);
                for (; i < intervals.length; i++) {
                    res.add(intervals[i]);
                }
                return res.toArray(new int[res.size()][]);
            }
            else { // else merge the current interval and the new interval into a bigger interval
                // 这个思路很关键，避免了很多分类讨论，如果需要merge，把interval加到newInterval上
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }

        res.add(newInterval); // if the newInterval is bigger than all of the other intervals, then have to add
        // it separately
        return res.toArray(new int[res.size()][]); // return the result
    }

    public static void main(String[] args) {
        int[] arr = {2,5};
        InsertInterval solution = new InsertInterval();
        int[][] in = {{1,3}, {6,9}};
        System.out.println(solution.insert(in, arr));
        System.out.println(Arrays.binarySearch(arr,6));
    }
}
