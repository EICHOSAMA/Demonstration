package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1156. Swap For Longest Repeated Character Substring 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/swap-for-longest-repeated-character-substring/">
 *  1156. Swap For Longest Repeated Character Substring</a>
 */
public final class Q1156 {
    public int maxRepOpt1(String text) {
        // 1. 1 <= text.length <= 2 * 10^4
        // 2. text consist of lowercase English characters only.
        final int n = text.length();
        final int[] counts = new int[26];
        for (int i = 0; i < n; i++) counts[text.charAt(i) - 'a']++;

        int result = 1;
        for (char t = 'a'; t <= 'z'; t++) {
            final int countT = counts[t - 'a'];
            if (countT <= result) continue;
            int l = 0; // [l, r)
            while (l < n && text.charAt(l) != t) l++;
            int r = l + 1; // [l, r)
            boolean swapped = false;
            while (l < n) {
                char ch = ' ';
                while (r < n && (((ch = text.charAt(r)) == t) || !swapped)) {
                    if (ch == t) {
                        r++;
                    } else {
                        swapped = true;
                        r++;
                    }
                }

                final int len = Math.min(r - l, countT);
                result = Math.max(result, len);

                if (!swapped) {
                    l = r;
                    while (l < n && text.charAt(l) != t) l++;
                    r = l + 1;
                } else {
                    while (swapped) {
                        ch = text.charAt(l++);
                        if (ch != t) swapped = false;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q1156 q1156 = new Q1156();
        System.out.println(q1156.maxRepOpt1("ababa")); // 3
        System.out.println(q1156.maxRepOpt1("aaabaaa")); // 6
        System.out.println(q1156.maxRepOpt1("aaaaa")); // 5
        System.out.println(q1156.maxRepOpt1("bbababaaaa")); // 6
    }
}
