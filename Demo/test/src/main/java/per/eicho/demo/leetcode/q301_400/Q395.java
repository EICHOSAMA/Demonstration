package per.eicho.demo.leetcode.q301_400;

/**
 * <p>395. Longest Substring with At Least K Repeating Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/">
 *   395. Longest Substring with At Least K Repeating Characters</a>
 */
public final class Q395 {
    public int longestSubstring(String s, int k) {
        // 1. 1 <= s.length <= 10^4
        // 2. s consists of only lowercase English letters.
        // 3. 1 <= k <= 10^5 
        final int n = s.length();
        if (k == 1) return n;
        final int[][] charCountInfo = new int[n][26];
        charCountInfo[0][s.charAt(0) - 'a'] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 26; j++) charCountInfo[i][j] = charCountInfo[i - 1][j];
            charCountInfo[i][s.charAt(i) - 'a']++;
        }

        int result = 0;
        int[] count = new int[26];
        for (int l = 0; l < n; l++) {
            // [l, r]
            if (l - 1 >= 0 && s.charAt(l) == s.charAt(l - 1)) continue; // optimized.
            int r = l + result; // optimized;
            if (r >= n) break;
            genCountInfoForRange(charCountInfo, l, r, count);
            int need = countNeed(count, k);
            while (r < n) {
                r += need;
                if (r < n) {
                    genCountInfoForRange(charCountInfo, l, r, count);
                    need = countNeed(count, k);
                    if (need != 0) continue;
                    while (++r < n && count[s.charAt(r) - 'a'] > 0);
                    --r;

                    result = Math.max(result, r - l + 1);
                    r++;
                }
            }
        }

        return result;
    }

    private void genCountInfoForRange(int[][] charCountInfo, int l, int r, int[] countRef) {
        copy2Ref(charCountInfo, r, countRef);        
        if (l - 1 >= 0) {
            int[] count = charCountInfo[l - 1];
            for (int j = 0; j < 26; j++) countRef[j] -= count[j];
        }
    }

    private void copy2Ref(int[][] charCountInfo, int i, int[] countRef) {
        int[] count = charCountInfo[i];
        for (int j = 0; j < 26; j++) countRef[j] = count[j];
    }

    private int countNeed(int[] count, int k) {
        int need = 0;
        for (int cnt : count) {
            if (cnt == 0 || cnt >= k) continue;
            need += (k - cnt);
        }
        return need;
    }

    public static void main(String[] args) {
        Q395 q395 = new Q395();
        System.out.println(q395.longestSubstring("aaabb", 3));
    }
}
