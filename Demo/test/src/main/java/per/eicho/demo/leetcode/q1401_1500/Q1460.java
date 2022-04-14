package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1460. Make Two Arrays Equal by Reversing Sub-arrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/">
 *   1460. Make Two Arrays Equal by Reversing Sub-arrays</a>
 */
public final class Q1460 {
    public boolean canBeEqual(int[] target, int[] arr) {
        // 1. target.length == arr.length
        // 2. 1 <= target.length <= 1000
        // 3. 1 <= target[i] <= 1000
        // 4. 1 <= arr[i] <= 1000    
        final int n = target.length;
        final int[] counts = new int[1001];
        for (int i = 0; i < n; i++) {
            counts[target[i]]++;
            counts[arr[i]]--;
        }

        for (int count : counts) if (count != 0) return false;
        return true;
    }
}
