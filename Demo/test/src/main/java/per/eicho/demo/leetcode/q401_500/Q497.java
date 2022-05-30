package per.eicho.demo.leetcode.q401_500;

import java.util.Random;

/**
 * <p>497. Random Point in Non-overlapping Rectangles 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/">
 *   497. Random Point in Non-overlapping Rectangles</a>
 */
@SuppressWarnings("unused")
public final class Q497 {
    private static final class Solution {

        final int[][] rects;
        final long[] sum;
        final int n;

        /** Initializes the object with the given rectangles rects. */
        public Solution(int[][] rects) {
            // 1. 1 <= rects.length <= 100
            // 2. rects[i].length == 4
            // 3. -10^9 <= ai < xi <= 10^9
            // 4. -10^9 <= bi < yi <= 10^9
            // 5. xi - ai <= 2000
            // 6. yi - bi <= 2000
            // 7. All the rectangles do not overlap.
            this.rects = rects;
            n = rects.length;
            sum = new long[n];
            sum[0] = getPointCount(rects[0]);
            for(int i = 1; i < n; i++) sum[i] = sum[i-1] + getPointCount(rects[i]);
        }

        private long getPointCount(int[] rect) {
            return (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
        }
        
        /** Returns a random integer point [u, v] inside the space covered by one of the given rectangles. */
        public int[] pick() {
            // 8. At most 10^4 calls will be made to pick.
            final double p = sum[n - 1] * Math.random();
            int i = 0;
            while(sum[i] < p) i++;
            return new int[]{rects[i][0]+(int)(Math.random()*(rects[i][2]-rects[i][0]+1)),rects[i][1]+(int)(Math.random()*(rects[i][3]-rects[i][1]+1))};
        }
    }
}
