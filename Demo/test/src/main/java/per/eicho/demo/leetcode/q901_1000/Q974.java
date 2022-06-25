package per.eicho.demo.leetcode.q901_1000;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>971. Flip Binary Tree To Match Preorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/">
 *   971. Flip Binary Tree To Match Preorder Traversal</a>
 */
public final class Q974 {
    public int subarraysDivByK(int[] nums, int k) {
        // 1. 1 <= nums.length <= 3 * 10^4
        // 2. -10^4 <= nums[i] <= 10^4
        // 3. 2 <= k <= 10^4
        final int n = nums.length;
        final int[] sums = new int[n];
        final Map<Integer, int[]> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sums[i] = sum;

            final Integer key = ((sum % k) + k) % k; // key ∈ [0, k)
            if (!map.containsKey(key)) map.put(key, new int[]{0});
            map.get(key)[0]++;
        }

        int result = map.containsKey(0) ? map.get(0)[0] : 0;
        for (int i = 0; i < n; i++) {
            final int sumI = sums[i];
            final Integer key = ((sumI % k) + k) % k; // key ∈ [0, k)
            result += --map.get(key)[0];
        }

        return result;
    }

    public static void main(String[] args) {
        Q974 q974 = new Q974();
        System.out.println(q974.subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
    }
}
