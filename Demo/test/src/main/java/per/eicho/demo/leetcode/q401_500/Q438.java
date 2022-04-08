package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>438. Find All Anagrams in a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">438. Find All Anagrams in a String</a>
 */
public final class Q438 {
    public List<Integer> findAnagrams(String s, String p) {
        // 1. 1 <= s.length, p.length <= 3 * 10^4
        // 2. s and p consist of lowercase English letters.
        final List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;


        final int[] counts = new int[26];
        for (int i = 0; i < p.length(); i++) counts[p.charAt(i) - 'a']++;

        final int[] windowCounts = new int[26];
        for (int i = 0; i < p.length(); i++) windowCounts[s.charAt(i) - 'a']++;

        int diff = 0;
        for (int i = 0; i < 26; i++) diff += Math.abs(windowCounts[i] - counts[i]);

        int l = 0, r = p.length(); // [l, r)
        while (r < s.length()) {
            if (diff == 0) result.add(l);

            final int charL = s.charAt(l++) - 'a';
            final int charR = s.charAt(r++) - 'a';

            diff -= Math.abs(counts[charL] - windowCounts[charL]--);
            diff += Math.abs(counts[charL] - windowCounts[charL]);
            diff -= Math.abs(counts[charR] - windowCounts[charR]++);
            diff += Math.abs(counts[charR] - windowCounts[charR]);
        }
        if (diff == 0) result.add(l);
        return result;
    }
}
