package per.eicho.demo.leetcode.q1501_1600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>1569. Number of Ways to Reorder Array to Get Same BST 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/">
 *   1569. Number of Ways to Reorder Array to Get Same BST</a>
 */
public final class Q1569 {

    int[][] f;
    final static int MODULO = 1_000_000_000 + 7;

    public int numOfWays(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 1 <= nums[i] <= nums.length
        // 3. All integers in nums are distinct.
        
        // Since the answer may be very large, return it modulo 10^9 + 7.  
        f = new int[nums.length][nums.length];
        final int result = numOfWays(Arrays.stream(nums).boxed().collect(Collectors.toList())) - 1; 
        return result >= 0 ? result : result + MODULO;
    }

    private int numOfWays(List<Integer> numList) {
        if (numList.size() <= 2) return 1; 

        final Integer root = numList.get(0);
        final List<Integer> left = new ArrayList<>(numList.size());
        final List<Integer> right = new ArrayList<>(numList.size());

        for (int i = 1; i < numList.size(); i++) {
            final Integer num = numList.get(i);
            if (num < root) {
                left.add(num);
            } else {
                right.add(num);
            }
        }


        final long numOfWaysL = numOfWays(left);
        final long numOfWaysR = numOfWays(right);

        return (int)((((long)calc(left.size(), right.size())) * ((numOfWaysL * numOfWaysR) % MODULO)) % MODULO);
    }

    private int calc(int nodeNumL, int nodeNumR) {
        if (nodeNumL < 0 || nodeNumR < 0) return 0; //invalid input
        if (f[nodeNumL][nodeNumR] != 0) return f[nodeNumL][nodeNumR];
        if (nodeNumL == 0 || nodeNumR == 0) {
            f[nodeNumL][nodeNumR] = 1;
            return 1;
        }

        f[nodeNumL][nodeNumR] = (calc(nodeNumL - 1, nodeNumR) + calc(nodeNumL, nodeNumR - 1)) % MODULO;
        return f[nodeNumL][nodeNumR];
    }
}
