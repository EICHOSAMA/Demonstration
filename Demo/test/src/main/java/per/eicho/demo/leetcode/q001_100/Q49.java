package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>49. Group Anagrams 的题解代码 </p>
 * 
 * <pre>
 *  Given an array of strings strs, group the anagrams together. 
 *  You can return the answer in any order.
 *  An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
 *  typically using all the original letters exactly once.
 * 
 * Example 1:
 *    Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Example 2:
 *    Input: strs = [""]
 *    Output: [[""]]
 * 
 * Example 3:
 *    Input: strs = ["a"]
 *    Output: [["a"]]
 *  
 * Constraints:
 *   1. 1 <= strs.length <= 104
 *   2. 0 <= strs[i].length <= 100
 *   3. strs[i] consists of lower-case English letters.
 * </pre>
 * @see <a href="https://leetcode.com/problems/group-anagrams/">49. Group Anagrams</a>
 */
final public class Q49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        final Map<String ,List<String>> result = new HashMap<>();

        for (String str: strs) {
            final String hashCode = hashCode(str);

            List<String> container = result.get(hashCode); 
            if (container == null) {
                container = new ArrayList<>();
                result.put(hashCode, container);
            }

            container.add(str);
        }

        return result.values()
                .stream()
                .collect(Collectors.toList());
    }

    private String hashCode(String str) {
        final int[] counts = new int[26];
        final StringBuilder sb = new StringBuilder();

        for (char c: str.toCharArray()) { 
            counts[c - 'a']++;
        }

        for (int count: counts) {
            sb.append(count);
            sb.append('-');
        }
        return sb.toString();
    }
}
