package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1287. Element Appearing More Than 25% In Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/">
 *   1287. Element Appearing More Than 25% In Sorted Array</a>
 */
public final class Q1287 {
    public int findSpecialInteger(int[] arr) {
        // 1. 1 <= arr.length <= 10^4
        // 2. 0 <= arr[i] <= 10^5
        final int n = arr.length;
        int num = -1;
        int count = 0;
        int result = 0;
        int maxCount = 0;

        int p = 0;
        while (p < n) {
            if (num != arr[p]) {
                if (count > maxCount) {
                    maxCount = count;
                    result = num;
                }

                num = arr[p];
                count = 1;
            } else {
                count++;
            }

            p++;
        }

        if (count > maxCount) {
            maxCount = count;
            result = num;
        }

        return result;
    }
}
