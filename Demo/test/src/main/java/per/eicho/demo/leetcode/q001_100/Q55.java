package per.eicho.demo.leetcode.q001_100;

/**
 * <p>55. Jump Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/jump-game/">55. Jump Game</a>
 */
public final class Q55 {
    public boolean canJump(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. 0 <= nums[i] <= 10^5        
        // maintain a reachablePosition variable.
        int reachablePosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachablePosition) break;
            reachablePosition = Math.max(reachablePosition, i + nums[i]);
            if (reachablePosition >= nums.length - 1) return true;
        }
        return false;
    }
}
