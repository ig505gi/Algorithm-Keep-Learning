package leetCode;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-08-06 - 09:25
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
public class ThreeSum {
    /*
    思路1：保存一个表格 sum[][] 表示 nums中 0 - nums[i] - nums[j],也就是遍历的时候还需要的值
    // 这样的话可以保存一个 map，这样从n3降到n2logn

    Your input
[-1,0,1,2,-1,-4]
Output
[[-1,0,1],[-1,2,-1],[0,1,-1]]
Expected
[[-1,-1,2],[-1,0,1]]
数组中有可能有重复元素，这样还得重新去重。。sort然后去重

     */
    public List<List<Integer>> threeSum1(int[] nums) {
        HashMap<Integer, List<Integer[]>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                Integer[] arr = new Integer[]{i, j};
                int key = 0 - nums[i] - nums[j];
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(arr);
            }
        }
        // 一定是按照从小到大排序 这样保证不重复
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                for (Integer[] arr : map.get(nums[i])) {
                    if (i > arr[1]) {
                        res.add(Arrays.asList(arr[0], arr[1], i));
                    }
                }
            }
        }
        return res;
    }

    /*
    思路2：做过的two target的问题 如果确定一个数，那么其他的就是两个数的和的问题，最后还得去重
     */

    /*
    评论区！ 预处理数组 预处理数组！先排序，这样就可跳过重复的值了
    因为复杂度上升为n2，一次排序后的数组，在对待twoSum问题的时候可以用双指针，空间复杂度低
    Runtime: 30 ms, faster than 82.38% of Java online submissions for 3Sum.
Memory Usage: 45 MB, less than 99.27% of Java online submissions for 3Sum.

评论区还有方法用set，不用判断重复
set是根据equals方法判断是否重复，正常对象只要指向不同，都会不同，如主函数中的l1和l2 是两个不同的对象！
但是之前学的String重写了equals方法，没想到的是 Arrays也重写了！
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, tar = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == tar) { // 同时加减，还要避免重复
                        res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--])); //这次是必减的
                        while (lo < hi && nums[lo] == nums[lo - 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi + 1]) hi--; // 减过之后如果和原来相同，再减
                    } else if (nums[lo] + nums[hi] < tar) lo++; //因为有序！才可以这样
                    else hi--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums));
        //
        System.out.println(Float.floatToRawIntBits(10.0f));
        List<Integer> l1= Arrays.asList(10000,2,3);
        List<Integer> l2= Arrays.asList(10000,2,3);
        System.out.println(l1.equals(l2));
        Set<List<Integer>> set = new HashSet<>();
        set.add(l1);
        set.add(l2);
        System.out.println(set.size());
    }
}
