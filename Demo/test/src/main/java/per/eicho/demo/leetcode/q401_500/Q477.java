package per.eicho.demo.leetcode.q401_500;

/**
 * <p>4477. Total Hamming Distance 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/total-hamming-distance/">477. Total Hamming Distance</a>
 * @see {@link Q461 Q461 Hamming Distance}
 */
public final class Q477 {
    public int totalHammingDistance(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. 0 <= nums[i] <= 10^9
        // 3. The answer for the given input will fit in a 32-bit integer.
        final int n = nums.length;
        final int[] countBits = new int[31];

        for (int num : nums) countBit(num, countBits);
        
        int result = 0;
        for (int count : countBits) {
            result += count * (n - count);
        }

        return result;
    }

    private void countBit(int num, int[] countBits) {
        int p = 0;
        while (num != 0) {
            countBits[p++] += (num % 2);
            num >>= 1;
        }
    }
}
