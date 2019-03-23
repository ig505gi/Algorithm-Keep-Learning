package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

 * @author GaoYuan
 * 代码简单的方式，应该是递归，
 * 1.  有n个不同的整数， 假设n-1个值得排列组合已经结束，再加一个数字，
 * 第一种方法其实就是暴力求解。。一个一个加进去
 * 2 回溯，也就是用递归， 先add 再remove ，需要对递归有很好的理解
 */

public class Permutations {
	
	// 暴力求解。。。
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for (int num: nums)
			helper(res, num);
		return res;
    }
	
	private void helper(List<List<Integer>> res, int num) {
		if (res.isEmpty()) {
			res.add(new ArrayList<Integer>() {{
				add(num);
			}});
			return;
		}
		int n = res.get(0).size();
		int length = res.size();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < n; j++) {
				List<Integer> newList = new ArrayList<Integer>(Arrays.asList(new Integer[n])); 
				Collections.copy(newList, res.get(i));
				newList.add(j, num);
				res.add(newList);
			}
			res.get(i).add(num);
		}
	}
	
	//回溯
	public List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		backtrack(res, new ArrayList<Integer>(), nums);
		return res;
	}

	private void backtrack(List<List<Integer>> res, ArrayList<Integer> tempList, int[] nums) {
		if (tempList.size() == nums.length) {
			res.add(new ArrayList<Integer>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (tempList.contains(nums[i])) continue;
				tempList.add(nums[i]);
				backtrack(res, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		Permutations p = new Permutations();
		int[] nums  = {1, 2, 3};
		List<List<Integer>> RES = p.permute2(nums);
		for (List<Integer> r: RES) {
			System.out.println(r);
		}
	}

}
