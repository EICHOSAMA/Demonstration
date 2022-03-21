package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1007. Minimum Domino Rotations For Equal Row 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/">
 *   1007. Minimum Domino Rotations For Equal Row</a>
 */
public final class Q1007 {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        // 1. 2 <= tops.length <= 2 * 10^4
        // 2. bottoms.length == tops.length
        // 3. 1 <= tops[i], bottoms[i] <= 6        
        final int n = tops.length;

        int same = -1;
        int countSame = 0;

        final int[][] countInfo = new int[2][7]; // [top, bottom][1-6]

        for (int i = 0; i < n; i++) {
            final int top = tops[i];
            final int bottom = bottoms[i];

            if (bottom == top) {
                if (same != -1 && same != top) return -1;

                same = top;
                countSame++;
                continue;
            }

            // bottom != top;
            countInfo[0][top]++;
            countInfo[1][bottom]++;
        }

        if (same == -1) {
            for (int i = 1; i <= 6; i++) {
                final int topCount = countInfo[0][i];
                final int botCount = countInfo[1][i];
                
                if (topCount + botCount < n) continue;
                return Math.min(topCount, botCount);
            }

            return -1;
        }

        if (countInfo[0][same] + countInfo[1][same] + countSame < n) return -1;
        return Math.min(countInfo[0][same], countInfo[1][same]);
    }
}
