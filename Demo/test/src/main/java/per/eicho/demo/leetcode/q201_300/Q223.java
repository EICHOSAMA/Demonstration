package per.eicho.demo.leetcode.q201_300;

/**
 * <p>223. Rectangle Area 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rectangle-area/">223. Rectangle Area</a>
 */
public final class Q223 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 1. -10^4 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10^4
        final int area1 = (ay2 - ay1) * (ax2 - ax1);
        final int area2 = (by2 - by1) * (bx2 - bx1);

        int lx = Math.max(ax1, bx1);
        int rx = Math.min(ax2, bx2);

        int by = Math.max(ay1, by1);
        int ty = Math.min(ay2, by2);

        int commonArea = 0;
        if (lx < rx && by < ty) commonArea = (rx - lx) * (ty - by);

        return area1 + area2 - commonArea;
    }
}
