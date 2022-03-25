package per.eicho.demo.leetcode.q301_400;

import java.util.Random;

/**
 * <p>384. Shuffle an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shuffle-an-array/">
 *   384. Shuffle an Array</a>
 */
@SuppressWarnings("unused")
public final class Q384 {
    private static final class Solution {

        final int[] originalNums;
        final int[] workingCopy;

        /**
         * Initializes the object with the integer array nums.
         */
        public Solution(int[] nums) {
            // 1. 1 <= nums.length <= 200
            // 2. -10^6 <= nums[i] <= 10^6
            // 3. All the elements of nums are unique.
            // 4. At most 5 * 104 calls in total will be made to reset and shuffle.            
            originalNums = nums;
            workingCopy = new int[nums.length];
            System.arraycopy(originalNums, 0, workingCopy, 0, originalNums.length);
        }
        
        /**
         * Resets the array to its original configuration and returns it.
         */
        public int[] reset() {
            System.arraycopy(originalNums, 0, workingCopy, 0, originalNums.length);
            return workingCopy;
        }
        
        /**
         * Returns a random shuffling of the array.
         * 
         * Node: All permutations of the array should be equally likely as a result of the shuffling.
         */
        public int[] shuffle() {
            final Random random = new Random();
            // 1-200 permutations. 200!
            int bound = workingCopy.length;
            for (int i = 0; i < workingCopy.length; i++) {
                final int idx = random.nextInt(bound); // [0, bound)

                int temp = workingCopy[idx];
                workingCopy[idx] = workingCopy[--bound];
                workingCopy[bound] = temp;
            }
            return workingCopy;
        }
    }
}
