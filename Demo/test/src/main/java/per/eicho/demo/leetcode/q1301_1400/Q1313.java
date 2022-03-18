package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1313. Decompress Run-Length Encoded List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/decompress-run-length-encoded-list/">
 *   1313. Decompress Run-Length Encoded List</a>
 */
public final class Q1313 {
    public int[] decompressRLElist(int[] nums) {
        // 1. 2 <= nums.length <= 100
        // 2. nums.length % 2 == 0
        // 3. 1 <= nums[i] <= 100        
        final int n = nums.length;
        int len = 0;
        for (int i = 0; i < n / 2; i++) {
            len += nums[i * 2];
        }
        final int[] result = new int[len];
        int p = 0;
        for (int i = 0; i < n / 2; i++) {
            final int freq = nums[i * 2];
            final int num = nums[i * 2 + 1];
            for (int j = 0; j < freq; j++) {
                result[p++] = num;
            }
        }
        return result;
    }
}
