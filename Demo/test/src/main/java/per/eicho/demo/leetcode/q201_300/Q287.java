package per.eicho.demo.leetcode.q201_300;

/**
 * <p>287. Find the Duplicate Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-the-duplicate-number/">
 *   287. Find the Duplicate Number</a>
 */
public final class Q287 {
    public int findDuplicate(int[] nums) {
        // 1. 1 <= n <= 10^5
        // 2. nums.length == n + 1
        // 3. 1 <= nums[i] <= n
        // All the integers in nums appear only once except for precisely one 
        // integer which appears two or more times.        
        
        int slow = 0;
        int fast = 0;
        
        do {
            slow = move(nums, slow);
            fast = move(nums, move(nums, fast));
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = move(nums, slow);
            fast = move(nums, fast);
        }
        return slow;
    }

    public int move(int[] nums, int pointer) {
        return nums[pointer];
    }

    public static void main(String[] args) {
        Q287 q287 = new Q287();
        System.out.println(q287.findDuplicate(new int[]{2,5,9,6,9,3,8,9,7,1}));
    }
}
