package per.eicho.demo.algorithm.array.kadane;

import java.util.Objects;

/**
 * <p>Kadane算法</p>
 * 
 * <pre>
 *  在计算机科学中有一类问题，被称为<b>最大子数列问题(Maximum subarray problem)</b>，
 *  为了解决这类问题，我们通常能很轻易想到O(n^2)的BF算法，以及利用TreeMap的O(n logn)的算法。
 *  而其实在很久之前就已经有学者（卡内基梅隆大学的Jay Kadane）提出了这个问题的线性时间算法。
 *  
 *  本Sample类将简单介绍和实现Kadane算法以在线性时间内解决最大子数列问题。
 * </pre>
 * 
 * <pre>
 *  Kadane算法是基于DP的思想,该算法定义 f[i] 是 以第i个元素为结尾的子数列的最大和。
 *   
 *  那么有如下DP
 *    1.边界值:
 *       f[0] = nums[0]
 *    2.当 i > 0 时的动态转移方程
 *       f[i] = max(f[i - 1] + nums[i], nums[i])
 *  
 *  TC: O(n)
 *  SC: O(n) 或者因为只与前一个状态有关，优化后为 O(1)
 * </pre>
 * 
 * <pre>
 *   LeetCode Questions:
 *     53. Maximum Subarray
 *     918. Maximum Sum Circular Subarray
 * </pre>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm">Wiki - Maximum subarray problem</a>
 */
public final class KadaneSample {
    
    public static int kadane(int[] nums) {
        Objects.nonNull(nums);
        if (nums.length == 0) throw new IllegalArgumentException("nums数组必须包含至少一个元素");

        final int n = nums.length;
        int result = nums[0];
        int f = nums[0];
        for (int i = 1; i < n; i++) {
            final int num = nums[i];
            f = Math.max(f + num, num);
            result = Math.max(result, f);
        }

        return result;
    }

    private KadaneSample() {}
}
