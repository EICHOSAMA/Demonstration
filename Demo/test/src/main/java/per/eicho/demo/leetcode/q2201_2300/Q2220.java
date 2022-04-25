package per.eicho.demo.leetcode.q2201_2300;

/**
 * <p>2220. Minimum Bit Flips to Convert Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-bit-flips-to-convert-number/">2220. Minimum Bit Flips to Convert Number</a>
 */
public final class Q2220 {
    public int minBitFlips(int start, int goal) {
        // 1. 0 <= start, goal <= 10^9
        int count = 0;
        while (start != goal) {
            if ((start % 2) != (goal % 2)) count++;
            start >>= 1;
            goal >>= 1;
        }
        return count;
    }
}
