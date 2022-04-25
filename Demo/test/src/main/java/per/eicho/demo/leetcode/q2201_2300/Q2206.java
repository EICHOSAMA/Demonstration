package per.eicho.demo.leetcode.q2201_2300;

/**
 * <p>2206. Divide Array Into Equal Pairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/divide-array-into-equal-pairs/">2206. Divide Array Into Equal Pairs</a>
 */
public final class Q2206 {
    public boolean divideArray(int[] nums) {
        // 1. nums.length == 2 * n
        // 2. 1 <= n <= 500
        // 3. 1 <= nums[i] <= 500
        final int[] bucket = new int[501];
        for (int num : nums) bucket[num]++;
        for (int i = 1; i <= 500; i++) {
            final int count = bucket[i];
            if (count % 2 != 0) return false;
        }
        return true;
    }
}
