package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1247. Minimum Swaps to Make Strings Equal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/">
 *   1247. Minimum Swaps to Make Strings Equal</a>
 */
public final class Q1247 {
    public int minimumSwap(String s1, String s2) {
        // 1. 1 <= s1.length, s2.length <= 1000
        // 2. s1, s2 only contain 'x' or 'y'.
        int countXY = 0, countYX = 0;
        for (int i = 0, n = s1.length(); i < n; i++) {
            final char ch1 = s1.charAt(i);
            final char ch2 = s2.charAt(i);

            if (ch1 == ch2) continue;
            if (ch1 == 'x') {
                countXY++;
            } else {
                countYX++;
            }
        }

        int result = (countXY / 2) + (countYX / 2);
        countXY %= 2;
        countYX %= 2;
        if (countXY == 0 && countYX == 0) return result;
        if (countXY == 1 && countYX == 1) return result + 2;
        return -1;
    }
}
