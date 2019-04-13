package algorithm2.week4;
/**
 * KMP算法 
 * 1. 基于DFA的，时间复杂度最低
 * 1.1 根据目标子串特征，构造DFA
 * 1.2 用DFA模拟要搜索的字符串
 * 2. 简化的，要找到前驱index
 * 1.1 根据目标子串的特征，构造next[]数组
 * 1.2 利用该数组搜索字符串
 * @author GaoYuan
 *
 */
public class KMP {
	
	private static boolean Simple(String pat, String str) {
		int M = pat.length();
		int[] next = new int[M];
		// next[0] = 0; 第一个值没匹配成功应该回到0
		int k = next[0];
		int j = 0;
		while (j < M) {
			
		}
		
		
		return false;
	}

	public static boolean BasedDFA(String pat, String str) {
		// 1.构造dfa
		int R = 256; // 字符直接转换成ASCII值
		int M = pat.length();
		int[][] dfa = new int[R][M];
		// 1.1 先构造dfa的第1列，在用dfa去模拟第2到n个字符
		int c = pat.charAt(0);
		dfa[c][0] = 1;
		// 1,2假如pat是 ABABAA，i开始模拟是从0开始的
		// 而X是从BABAA开始用dfa模拟的，X记录的是前一个状态
		for (int X = 0, j = 1; j < M; j++) {
			c = pat.charAt(j);
			// 直接覆盖前一个状态 mismacth
			for (int i = 0; i < R; i++) {
				dfa[i][j] = dfa[i][X];
			}
			// 更新第i个字符匹配上的情况 macth
			dfa[c][j] = j + 1;
			// 更新X
			X = dfa[c][X];
		}
		// 2.用dfa模拟
		int state = 0;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			state = dfa[c][state];
			if (state == pat.length()) return true;
		}
		return false;
	}
	public static void main(String[] args) {
		String pat = "11A3A12";
		String str = "11987811A3A12131";
		boolean isExist1 = KMP.BasedDFA(pat, str);
		boolean isExist2 = KMP.Simple(pat, str);
		System.out.println(isExist1 + ", " + isExist2);
	}

}
