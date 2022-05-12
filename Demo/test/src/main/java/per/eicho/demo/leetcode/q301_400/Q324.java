package per.eicho.demo.leetcode.q301_400;

/**
 * <p>312. Burst Balloons 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/burst-balloons/">
 *   312. Burst Balloons</a>
 */
public final class Q324 {
    public void wiggleSort(int[] nums) {
        // 1. 1 <= nums.length <= 5 * 10^4
        // 2. 0 <= nums[i] <= 5000
        // 3. It is guaranteed that there will be an answer for the given input nums.        
        final int n = nums.length;
        final int[] buckets = new int[5001]; // [0, 5000]
        for (int num : nums) buckets[num]++;

        final int nextP;
        int p;
        if (n % 2 == 0) {
            p = n - 2;
            nextP = n - 1;
        } else {
            p = n - 1;
            nextP = n - 2;
        } 

        for (int num = 0; num <= 5000; num++) {
            int count = buckets[num]; 
            while (count-- != 0) {
                nums[p] = num;
                p -= 2;
                if (p < 0) p = nextP;
            }
        }
    }
}
