package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1385. Find the Distance Value Between Two Arrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-the-distance-value-between-two-arrays/">
 *   1385. Find the Distance Value Between Two Arrays</a>
 */
public final class Q1385 {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // 1. 1 <= arr1.length, arr2.length <= 500
        // 2. -1000 <= arr1[i], arr2[j] <= 1000
        // 3. 0 <= d <= 100        
        int result = arr1.length;

        for (int i = 0; i < arr1.length; i++) {
            final int num1 = arr1[i];

            for (int j = 0; j < arr2.length; j++) {
                final int num2 = arr2[j];
                if (Math.abs(num2 - num1) <= d) {
                    result--;
                    break;
                }
            }
        }
        return result;
    }
}
