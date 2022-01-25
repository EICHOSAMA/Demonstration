package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2103. Rings and Rods 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rings-and-rods/">
 *   2103. Rings and Rods</a>
 */
public final class Q2103 {
    public int countPoints(String rings) {
        // 1. rings.length == 2 * n
        // 2. 1 <= n <= 100
        // 3. rings[i] where i is even is either 'R', 'G', or 'B' (0-indexed).
        // 4. rings[i] where i is odd is a digit from '0' to '9' (0-indexed).
        final int n = rings.length() / 2;
        final int[] mark = new int[10];

        for (int i = 0; i < n; i++) {
            final int rodIndex = rings.charAt(i * 2 + 1) - '0';
            final char color = rings.charAt(i * 2);

            if (color == 'R') {
                mark[rodIndex] |= 0b001;
            } else if (color == 'G') {
                mark[rodIndex] |= 0b010;
            } else {
                // 'B'
                mark[rodIndex] |= 0b100;
            }
        }

        int result = 0;
        for (int i = 0; i < mark.length; i++) {
            if (mark[i] == 0b111) result++;
        }

        return result;
    }
}
