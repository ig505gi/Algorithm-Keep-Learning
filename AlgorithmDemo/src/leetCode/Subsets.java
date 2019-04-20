package leetCode;

import java.util.ArrayList;
import java.util.List;
/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Subsets.
Memory Usage: 38.4 MB, less than 11.85% of Java online submissions for Subsets.
 * @author GaoYuan
 *
 */
public class Subsets {
	
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        add(res, 0, nums, new ArrayList<Integer>());
        return res;
    }
    
    private void add(List<List<Integer>> res, int i, 
                     int[] nums, ArrayList<Integer> pre) {
        if (i == nums.length) {
            res.add(pre);
        } else {
            add(res, i + 1, nums, pre);
            ArrayList<Integer> newPre = new ArrayList<Integer>(pre);
            newPre.add(nums[i]);
            add(res, i + 1, nums, newPre);
        }
        
    }
	public static void main(String[] args) {
		Subsets ss = new Subsets();
		int[] nums = {1, 2, 3};
		for (List<Integer> l: ss.subsets(nums)) {
			System.out.println(l);
		}

	}

}
