package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1011. Capacity To Ship Packages Within D Days 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/">
 *   1011. Capacity To Ship Packages Within D Days</a>
 */
public final class Q1011 {
    int days;

    public int shipWithinDays(int[] weights, int days) {
        // 1. 1 <= days <= weights.length <= 5 * 10^4
        // 2. 1 <= weights[i] <= 500
        this.days = days;
        final int totalSum = Sum(weights);
        return binarySearch(weights, totalSum / days, totalSum);
    }

    private int binarySearch(int[] weights, int l, int r) {
        if (l == r) return l;

        final int mid = (l + r) / 2;
        if (can(weights, mid)) return binarySearch(weights, l, mid);
        return binarySearch(weights, mid + 1, r);
    } 

    private boolean can(int[] weights, int capacity) {
        int p = 0;
        int day = 0;
        int sum = 0;
        final int n = weights.length;
        while (p < n) {
            day++; // ${day} th Day
            if (day > days) return false;
            sum = 0;
            while (p < n && sum + weights[p] <= capacity) sum += weights[p++];
            // [l,p)
        }
        return true;
    }

    private int Sum(int[] weights) {
        int sum = 0;
        for (int weight : weights) sum += weight;
        return sum;
    }
}
