package leetCode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

Runtime: 2 ms, faster than 99.34% of Java online submissions for Palindrome Partitioning.
Memory Usage: 39.7 MB, less than 64.46% of Java online submissions for Palindrome Partitioning.

思路还是来自评论区
 */
public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> re = new ArrayList<List<String>>();
		// 先用O(n^2)复杂度算法来记录回文字符串
		// dp[j][i] 表示从i-j是否为回文
		boolean [][] dp = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1]))
					dp[j][i] = true;
			}
		}
		dfs(re, dp, new ArrayList<String>(), s, 0);
		return re;
    }
	private void dfs(List<List<String>> re, boolean[][] dp, ArrayList<String> path, String s, int pos) {
		if (pos == s.length()) {
			re.add(new ArrayList<String>(path));
			return;
		}
		for (int i = pos; i < s.length(); i++) {
			if (dp[pos][i]) {
				path.add(s.substring(pos, i + 1));
				dfs(re, dp, path, s, i + 1);
				path.remove(path.size() - 1);
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
