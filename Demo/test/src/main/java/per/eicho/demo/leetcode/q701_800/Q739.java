package per.eicho.demo.leetcode.q701_800;

/**
 * <p>739. Daily Temperatures 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/daily-temperatures/">739. Daily Temperatures</a>
 */
public final class Q739 {
    public int[] dailyTemperatures(int[] temperatures) {
        // 1. 1 <= temperatures.length <= 10^5
        // 2. 30 <= temperatures[i] <= 100        
        final int n = temperatures.length;
        final int[] monoStack = new int[n]; // saving index info. temperatures[index] desc order.
        int stackSize = 0; // avoid using Stack<Integer> for autoboxing problem. 
        final int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            final int temperature = temperatures[i];

            while (stackSize > 0 && temperatures[monoStack[stackSize - 1]] < temperature) {
                final int idx = monoStack[--stackSize];
                result[idx] = i - idx;
            }
            monoStack[stackSize++] = i;
        }

        return result;
    }

}
