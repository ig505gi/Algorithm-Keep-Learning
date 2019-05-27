package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*
 * to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * 
 * 思路1：找图中是否有环！，给了所有的有向边，从0开始遍历
 * 保存就用map，<int，list<int>>
 * 用深度优先遍历，如果遇到遍历过的
 * 一个数组是visited,还有一个是inStack，如果遇到inStack是true那么久返回false
 * 如果全部遍历完成，则返回true
 * Runtime: 6 ms, faster than 74.44% of Java online submissions for Course Schedule.
Memory Usage: 44.3 MB, less than 52.18% of Java online submissions for Course Schedule.
 */
public class CourseSchedule {
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> nextMap = new HashMap<Integer, List<Integer>>();
        boolean[] visited = new boolean[numCourses];
        boolean[] inStack = new boolean[numCourses];
        Stack<Integer> stack = new Stack<Integer>();
        // 建立nextMap
        for (int i = 0; i < prerequisites.length; i++) {
        	int key = prerequisites[i][1];
        	if (!nextMap.containsKey(key)) {
        		nextMap.put(key, new ArrayList<Integer>());
        	}
        	nextMap.get(key).add(prerequisites[i][0]);
        }
        // 从0开始遍历,进行dfs
        for (int i = 0; i < numCourses; i++) {
        	if (!visited[i]) {
        		boolean flag = dfs(i, stack, inStack, visited, nextMap);
        		if (!flag) return flag;
        	}
        }
		return true;
    }
	private boolean dfs(int i, Stack<Integer> stack, boolean[] inStack, boolean[] visited,
			HashMap<Integer, List<Integer>> nextMap) {
		if (inStack[i]) return false;
		stack.push(i);
        inStack[i] = true;
		if (nextMap.containsKey(i)) {
			for (int next : nextMap.get(i)) {
				if (visited[next]) continue;
				boolean flag = dfs(next, stack, inStack, visited, nextMap);
				if (!flag) return flag;
			}       
        }
        visited[stack.pop()] = true;
        inStack[i] = false;
		return true;
	}
	public static void main(String[] args) {
		CourseSchedule solution = new CourseSchedule();
		int[][] prerequisites = {{1, 0}, {0,1}};
		boolean c = solution.canFinish(2, prerequisites);
		System.out.print(c);
	}

}
