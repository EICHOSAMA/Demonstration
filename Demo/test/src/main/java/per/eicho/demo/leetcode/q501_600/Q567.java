package per.eicho.demo.leetcode.q501_600;

/**
 * <p>567. Permutation in String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/permutation-in-string/">
 *   567. Permutation in String</a>
 */
public final class Q567 {

    public boolean checkInclusion(String s1, String s2) {
        // 1. 1 <= s1.length, s2.length <= 10^4
        // 2. s1 and s2 consist of lowercase English letters.
        if (s2.length() < s1.length()) return false;
        final int[] countS1 = new int[26]; 
        for (int i = 0; i < s1.length(); i++) countS1[s1.charAt(i) - 'a']++;

        final int[] countS2Window = new int[26];
        int l = 0, r = 0;
        for (r = 0; r < s1.length(); r++) countS2Window[s2.charAt(r) - 'a']++;

        int diff = 0;
        for (int i = 0; i < 26; i++) diff += Math.abs(countS2Window[i] - countS1[i]);
        if (diff == 0) return true;

        for (; r < s2.length(); l++, r++) {
            final int iL = s2.charAt(l) - 'a';
            final int iR = s2.charAt(r) - 'a';

            diff -= Math.abs(countS2Window[iL] - countS1[iL]);
            diff -= Math.abs(countS2Window[iR] - countS1[iR]);

            countS2Window[iL]--;
            countS2Window[iR]++;

            diff += Math.abs(countS2Window[iL] - countS1[iL]);
            diff += Math.abs(countS2Window[iR] - countS1[iR]);

            if (diff == 0) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Q567 q567 = new Q567();
        System.out.println(q567.checkInclusion("ab", "eidboaoo"));
    }
}
