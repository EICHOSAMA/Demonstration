package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>991. Broken Calculator 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/broken-calculator/">991. Broken Calculator</a>
 */
public final class Q991 {
    public int brokenCalc(int startValue, int target) {
        // 1 <= x, y <= 10^9
        if (startValue >= target) return startValue - target;

        int result = 0;
        int time = 1;
        while (startValue < target) {
            startValue <<= 1;
            result++;
            time <<= 1;
        }
        
        int diff = startValue - target;
        while (diff != 0) {
            result += (diff / time);
            diff %= time;
            time >>= 1;
        }

        return result;
    }
}
