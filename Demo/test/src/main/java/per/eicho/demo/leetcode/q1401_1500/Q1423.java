package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1423. Maximum Points You Can Obtain from Cards 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/">
 *   1423. Maximum Points You Can Obtain from Cards</a>
 */
public final class Q1423 {
    public int maxScore(int[] cardPoints, int k) {
        // 1. 1 <= cardPoints.length <= 10^5
        // 2. 1 <= cardPoints[i] <= 10^4
        // 3. 1 <= k <= cardPoints.length
        final int n = cardPoints.length;
        int sum = 0;
        for (int i = 0; i < k; i++) sum += cardPoints[i];
        int result = sum;

        int r = k - 1, l = n;
        for (int i = r; i >= 0; i--) {
            sum = sum - cardPoints[i] + cardPoints[--l];
            result = Math.max(result, sum);
        }

        return result;
    }
}
