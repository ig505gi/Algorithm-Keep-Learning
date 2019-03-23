package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int num: nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		List<Integer>[] buckets = new List[nums.length + 1];
		
		for (int num: map.keySet()) {
			int freq = map.get(num);
			if (buckets[freq] == null) {
				buckets[freq] = new ArrayList<Integer>();
			}
			buckets[freq].add(num);
		}
		
		List<Integer> res = new ArrayList<Integer>();
		for (int start = nums.length; start >= 0 && res.size() < k; --start) {
			if (buckets[start] != null) {
				res.addAll(buckets[start]);
			}
		}
		
		return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
