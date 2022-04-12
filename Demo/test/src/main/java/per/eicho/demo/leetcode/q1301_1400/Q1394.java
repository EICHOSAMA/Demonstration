package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1394. Find Lucky Integer in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-lucky-integer-in-an-array/">
 *   1394. Find Lucky Integer in an Array</a>
 */
public final class Q1394 {
    public int findLucky(int[] arr) {
        // 1. 1 <= arr.length <= 500
        // 2. 1 <= arr[i] <= 500        

        // a lucky integer is an integer that has a frequency in the array equal to its value.
        // Return the largest lucky integer in the array. If there is no lucky integer return -1.

        final int[] counts = new int[501];
        for (int num : arr) counts[num]++;
        for (int i = 500; i > 0; i--) if (i == counts[i]) return i;
        return -1;
    }
}
