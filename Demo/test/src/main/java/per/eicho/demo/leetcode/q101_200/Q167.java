package per.eicho.demo.leetcode.q101_200;

/**
 * <p>167. Two Sum II - Input Array Is Sorted 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/">167. Two Sum II - Input Array Is Sorted</a>
 */
public final class Q167 {
    public int[] twoSum(int[] numbers, int target) {
        // 1. 2 <= numbers.length <= 3 * 10^4
        // 2. -1000 <= numbers[i] <= 1000
        // 3. numbers is sorted in non-decreasing order.
        // 4. -1000 <= target <= 1000
        // 5. The tests are generated such that there is exactly one solution.        
        final int n = numbers.length;
        int[] result = new int[2];
        for (int i = 0; i < n; i++) {
            final int num = numbers[i];
            int index = binarySearch(numbers, i + 1, n - 1, target - num);
            if (index != -1) {
                result[0] = i + 1;
                result[1] = index + 1;
                break;
            }
        }
        return result;
    }

    public int binarySearch(final int[] numbers, final int l, final int r, final int target) {
        if (l == r) {
            if (numbers[l] == target) return l;
            return -1;
        }

        int mid = (l + r + 1) / 2;
        if (numbers[mid] > target) return binarySearch(numbers, l, mid - 1, target);
        return binarySearch(numbers, mid, r, target);
    }
}
