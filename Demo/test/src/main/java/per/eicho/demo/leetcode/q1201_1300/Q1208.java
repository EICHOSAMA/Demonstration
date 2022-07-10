package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1208. Get Equal Substrings Within Budget 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/get-equal-substrings-within-budget/">
 *   1208. Get Equal Substrings Within Budget</a>
 */
public final class Q1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        // 1. 1 <= s.length <= 10^5
        // 2. t.length == s.length
        // 3. 0 <= maxCost <= 10^6
        // 4. s and t consist of only lowercase English letters.
        final int n = s.length();
        int l = 0, r = 0; // [l, r)
        int result = 0;
        while (l < n) {
            int cost = 0;
            while (r < n && maxCost >= (cost = Math.abs(s.charAt(r) - t.charAt(r)))) {
                maxCost -= cost;
                r++;
            }

            result = Math.max(result, r - l);
            
            maxCost += (Math.abs(s.charAt(l) - t.charAt(l)));
            l++;
        }

        return result;
    }
}
