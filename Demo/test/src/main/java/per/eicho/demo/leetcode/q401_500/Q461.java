package per.eicho.demo.leetcode.q401_500;

/**
 * <p>461. Hamming Distance 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-average-subarray-i/">461. Hamming Distance</a>
 */
public final class Q461 {
    public int hammingDistance(int x, int y) {
        // 1. 0 <= x, y <= 2^31 - 1
        int n = x ^ y;
        
        int result = 0;
        while (n > 0) {
            result += n & 1;
            n >>= 1;
        }
        return result;
    }
}
