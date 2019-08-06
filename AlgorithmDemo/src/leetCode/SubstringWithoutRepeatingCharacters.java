package leetCode;

import java.util.HashMap;

/**
 * @author Gao Yuan
 * @date 2019-08-05 - 10:27
 *
 * Runtime: 7 ms, faster than 82.52% of Java online submissions for Longest Substring Without Repeating Characters.
 * Memory Usage: 36.4 MB, less than 99.88% of Java online submissions for Longest Substring Without Repeating Characters.
 */
public class SubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                j = Math.max(j, map.get(c) + 1);
            }
            map.put(c, i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        SubstringWithoutRepeatingCharacters solution = new SubstringWithoutRepeatingCharacters();
        System.out.println(solution.lengthOfLongestSubstring("abba"));
    }
}
