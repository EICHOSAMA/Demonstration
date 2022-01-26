package per.eicho.demo.leetcode.q001_100;

/**
 * <p>88. Merge Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/merge-sorted-array/">88. Merge Sorted Array</a>
 */
public final class Q88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 1. nums1.length == m + n
        // 2. nums2.length == n
        // 3. 0 <= m, n <= 200
        // 4. 1 <= m + n <= 200
        // 5. -10^9 <= nums1[i], nums2[j] <= 10^9

        int cursor = m + n - 1;
        int cursorM = m - 1;
        int cursorN = n - 1;
        while (cursor >= 0) {
            if (cursorM >= 0 && cursorN >= 0) {
                if (nums1[cursorM] > nums2[cursorN]) {
                    nums1[cursor] = nums1[cursorM--];
                } else {
                    nums1[cursor] = nums2[cursorN--];
                }
            } else if (cursorM >= 0) {
                // cursorN < 0
                break;
            } else {
                // cursorM < 0
                while (cursorN >= 0) {
                    nums1[cursor--] = nums2[cursorN--];
                }
                break;
            }

            cursor--;
        }
    }
}
