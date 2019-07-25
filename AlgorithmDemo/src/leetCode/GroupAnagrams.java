package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Gao Yuan
 * @date 2019-07-25 - 11:14
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * Runtime: 9 ms, faster than 78.09% of Java online submissions for Group Anagrams.
 * Memory Usage: 40.8 MB, less than 98.42% of Java online submissions for Group Anagrams.
 */
public class GroupAnagrams {
    /*
    思路：找到strs，按照char排序，然后作为key保存在map中
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] charKey = strs[i].toCharArray();
            Arrays.sort(charKey);
            String key = String.valueOf(charKey);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }
}
