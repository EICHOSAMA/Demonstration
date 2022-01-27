package per.eicho.demo.leetcode.q001_100;

/**
 * <p>27. Remove Element 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-element/">27. Remove Element</a>
 */
public final class Q27 {
    public int removeElement(int[] nums, int val) {
        // 1. 0 <= nums.length <= 100
        // 2. 0 <= nums[i] <= 50
        // 3. 0 <= val <= 100
        if (nums.length == 0) return 0;

        int lastIndex = 0;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != val) {
                nums[lastIndex++] = nums[i];
            }
        }
        return lastIndex;
    }
}
