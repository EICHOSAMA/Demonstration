package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * <p>1696. Jump Game VI 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/jump-game-vi/">
 *   1696. Jump Game VI</a>
 */
public final class Q1696 {
    public int maxResult(int[] nums, int k) {
        // 1. 1 <= nums.length, k <= 10^5
        // 2. -10^4 <= nums[i] <= 10^4
        final int n = nums.length;
        final int[] f = new int[n];
        Arrays.fill(f, Integer.MIN_VALUE);
        f[0] = nums[0];

        final TreeMap<Integer, Set<Integer>> treeMap = new TreeMap<>();
        treeMap.put(f[0], new HashSet<>());
        treeMap.get(f[0]).add(0);

        int l = 0, r = 0;
        int p = 1;
        while (p < n) {
            f[p] = treeMap.lastKey() + nums[p];
            r = p;
            
            if (!treeMap.containsKey(f[p])) treeMap.put(f[p], new HashSet<>());
            treeMap.get(f[p]).add(p);

            if (r - l >= k) {
                final Set<Integer> set = treeMap.get(f[l]);
                set.remove(l);
                if (set.isEmpty()) treeMap.remove(f[l]);
                l++;    
            }

            p++;
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        Q1696 q1696 = new Q1696();
        int[] input = new int[10_0000];
        for (int i = 0; i < input.length; i++) {
            input[i] = -10000;
        }
        System.out.println(q1696.maxResult(input, 1));
    }
}
