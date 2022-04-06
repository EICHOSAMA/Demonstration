package per.eicho.demo.leetcode.q501_600;

/**
 * <p>581. Shortest Unsorted Continuous Subarray 的题解代码 </p>

 * @see <a href="https://leetcode.com/problems/shortest-unsorted-continuous-subarray/">581. Shortest Unsorted Continuous Subarray</a>
 */
public final class Q581 {
    public int findUnsortedSubarray(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^5 <= nums[i] <= 10^5        
        final int n = nums.length;

        int l = n, r = 0; // sort range [l, r)

        int[] monoStack = new int[n]; // save idx info.
        monoStack[0] = 0;
        int size = 1;
        
        for (int i = 1; i < n; i++) {
            final int num = nums[i];

            if (num >= nums[monoStack[size - 1]]) {
                monoStack[size++] = i; // add to mono stack.
                continue;
            }

            final int top = monoStack[size - 1];
            r = i + 1;
            while (size > 0 && nums[monoStack[size - 1]] > num) size--;
            l = Math.min(l, monoStack[size]);

            monoStack[size++] = i;
            monoStack[size++] = top; 
        }
        return l == n ? 0 : r - l;
    }

    public static void main(String[] args) {
        Q581 q581 = new Q581();
        System.out.println(q581.findUnsortedSubarray(new int[]{1,3,2,2,2})); // 4
        System.out.println(q581.findUnsortedSubarray(new int[]{1,2,3,3,3})); // 0
    }
}
