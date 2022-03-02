package per.eicho.demo.leetcode.q801_900;

import per.eicho.demo.leetcode.q201_300.Q223;

/**
 * <p>836. Rectangle Overlap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rectangle-overlap/">836. Rectangle Overlap</a>
 * @see {@link Q223 Q223. Rectangle Area}
 */
public final class Q836 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // 1. rect1.length == 4
        // 2. rect2.length == 4
        // 3. -10^9 <= rec1[i], rec2[i] <= 10^9
        // 4. rec1 and rec2 represent a valid rectangle with a non-zero area.        
        if (rec1[2] <= rec2[0]) return false; // r1.x2 <= r2.x1 
        if (rec1[3] <= rec2[1]) return false; // r1.y2 <= r2.y1

        if (rec2[2] <= rec1[0]) return false; // r2.x2 <= r1.x1 
        if (rec2[3] <= rec1[1]) return false; // r2.y2 <= r1.y1
        return true;
    }
}
