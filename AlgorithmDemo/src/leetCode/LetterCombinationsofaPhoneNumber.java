package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手机九键，输入数字，给出所有的字母组合
 * @author GaoYuan
 *Runtime: 1 ms, faster than 89.98% of Java online submissions for Letter Combinations of a Phone Number.
Memory Usage: 37.2 MB, less than 66.20% of Java online submissions for Letter Combinations of a Phone Number.

注意：输出是[]和[""]是不一样的
 */
public class LetterCombinationsofaPhoneNumber {
	public List<String> letterCombinations(String digits) {
        Map<Character, char[]> map = new HashMap<Character, char[]>(){{
        	put('2', new char[]{'a', 'b', 'c'}); put('3', new char[]{'d', 'e', 'f'});
        	put('4', new char[]{'g', 'h', 'i'}); put('5', new char[]{'j', 'k', 'l'});
        	put('6', new char[]{'m', 'n', 'o'}); put('7', new char[]{'p', 'q', 'r', 's'});
        	put('8', new char[]{'t', 'u', 'v'}); put('9', new char[]{'w', 'x', 'y', 'z'});
        }};
		List<String> res = new ArrayList<String>();
		// 可以 bfs！
		if (digits.length() <= 0) return res;
		bfs(res, map, digits, 0, new StringBuilder());
        return res;
    }
	
	private void bfs(List<String> res, Map<Character, char[]> map, String digits, int i, StringBuilder sb) {
		if (i == digits.length()) {
			res.add(sb.toString());
		} else {
			for (char c: map.get(digits.charAt(i))) {
				sb.append(c);
				bfs(res, map, digits, i + 1, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}		
	}

	public static void main(String[] args) {
		LetterCombinationsofaPhoneNumber solution = new LetterCombinationsofaPhoneNumber();
		List<String> res = solution.letterCombinations("23");
		System.out.println(res);
	}

}
