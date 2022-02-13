package per.eicho.demo.leetcode.q001_100;

/**
 * <p>45. Jump Game II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/jump-game-ii/">45. Jump Game II</a>
 */
public final class Q45 {
    public int jump(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. 0 <= nums[i] <= 1000        
        if (nums.length <= 1) return 0;
        // f[i] represent the minimum number of jumps to reach position i(0-base index).
        final int[] f = new int[nums.length];

        // maintain a reachablePosition variable.
        int reachablePosition = 0;
        for (int i = 0; i < nums.length; i++) {
            // Comment out reason: "You can assume that you can always reach the last index."
            //if (i > reachablePosition) break;
            int newReachablePosition = i + nums[i];
            if (newReachablePosition > reachablePosition) {
                if (newReachablePosition >= nums.length - 1) return f[i] + 1; // optimize
                while (reachablePosition < newReachablePosition) {
                    reachablePosition++; // expand reachable position.
                    f[reachablePosition] = f[i] + 1; // step + 1
                }
                assert reachablePosition == newReachablePosition;
            }
        }

        // this will never happen.
        return 0;
    }
}
