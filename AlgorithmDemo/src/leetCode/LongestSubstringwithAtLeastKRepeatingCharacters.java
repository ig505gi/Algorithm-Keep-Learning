package leetCode;

import java.util.Arrays;

public class LongestSubstringwithAtLeastKRepeatingCharacters {
	/**
	 * 完全理解错题意了，这是找出重复次数大约等于k次的所有字母，把这些字母重复的次数相加
	 * @param s
	 * @param k
	 * @return
	 */
	public int longestSubstringWrong(String s, int k) {
        int[][] IndexAndTimes = new int[2][26];
        // 初始化index，第一行为index，并且0-25代表a-z
        // 第二行为次数times
        for (int i = 0; i < 26; i++) {
        	IndexAndTimes[0][i] = i;
        }
        // 遍历s, 每遇到一个就加1，复杂度O(n)
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	IndexAndTimes[1][c - 'a'] += 1;
        }
        // 按照次数多少排序，O(nlogn)
        // 因为第一行的字母代表的数字没用了 只排序第二行
        Arrays.sort(IndexAndTimes[1]);
        int res = 0;
        for (int i = 25; i >= 0; i--) {
        	if (IndexAndTimes[1][i] < k) break;
        	res += IndexAndTimes[1][i];
        }
        return res;
    }
	/*
	 * 找到最大的子串，子串中每个字母都要至少重复k次
	 * 再输出子串长度
	 * 又错了！，ababacb,b一共出现3次，但是ababa中b出现两次，所以k=3时，结果是0
	 */
	public int longestSubstringWrong2(String s, int k) {
		int[][] IndexAndTimes = new int[26][2];
        // 初始化index，第一行为index，并且0-25代表a-z
        // 第二行为次数times
        for (int i = 0; i < 26; i++) {
        	IndexAndTimes[i][0] = i;
        }
        // 遍历s, 每遇到一个就加1，复杂度O(n)
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	IndexAndTimes[c - 'a'][1] += 1;
        }
        // 按照次数多少排序，O(nlogn)
//        Arrays.sort(IndexAndTimes, (t1, t2) -> (t1[1] - t2[1]));
//        for (int[] a: IndexAndTimes) {
//        	System.out.println(Arrays.toString(a));
//        }
        // 不用排序。。直接再遍历，如果次数小于k赋值为0
        int max = 0;
        int currentMax = 0;
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (IndexAndTimes[c - 'a'][1] >= k) currentMax++;
        	else {
        		max = Math.max(max, currentMax);
        		currentMax = 0;
        	}
        }
        max = Math.max(max, currentMax);
		return max;
    }
	/*
	 * 评论区的办法，也想到可以这样，不难。。
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Substring with At Least K Repeating Characters.
Memory Usage: 37.3 MB, less than 52.41% of Java online submissions for Longest Substring with At Least K Repeating Characters.
	 */
	public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = new char[26];
        // record the frequency of each character
        for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a'] += 1;
        boolean flag = true;
        for (int i = 0; i < chars.length; i += 1) {
            if (chars[i] < k && chars[i] > 0) flag = false;
        }
        // return the length of string if this string is a valid string
        if (flag == true) return s.length();
        int result = 0;
        int start = 0, cur = 0;
        // otherwise we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (chars[s.charAt(cur) - 'a'] < k) {
                result = Math.max(result, longestSubstring(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        result = Math.max(result, longestSubstring(s.substring(start), k));
        return result;
    }
	public static void main(String[] args) {
		LongestSubstringwithAtLeastKRepeatingCharacters solution = new LongestSubstringwithAtLeastKRepeatingCharacters();
		System.out.println(solution.longestSubstring("aaabbb", 2));
	}

}
