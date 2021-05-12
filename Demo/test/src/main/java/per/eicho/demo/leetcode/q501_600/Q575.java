package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;

/**
 * <p>575. Distribute Candies 的题解代码 </p>

 * @see <a href="https://leetcode.com/problems/distribute-candies/">575. Distribute Candies</a>
 */
final public class Q575 {
    public int distributeCandies(int[] candyType) {
        final int maxNum = candyType.length / 2;

        Arrays.sort(candyType);

        // -10^5 <= candyType[i] <= 10^5
        int lastType = Integer.MIN_VALUE;
        int result = 0;
        for (int i = 0; i < candyType.length; i++) {
            final int type = candyType[i];

            if (type == lastType) {
                continue;
            }

            // type != lastType
            lastType = type;
            result++;
            if (result >= maxNum) {
                return maxNum;
            }
        }

        return result;
    }
}
