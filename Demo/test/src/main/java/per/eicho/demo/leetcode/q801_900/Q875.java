package per.eicho.demo.leetcode.q801_900;

/**
 * <p>875. Koko Eating Bananas 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/koko-eating-bananas/">
 *   875. Koko Eating Bananas</a>
 */
public final class Q875 {
    public int minEatingSpeed(int[] piles, int h) {
        // 1. 1 <= piles.length <= 10^4
        // 2. piles.length <= h <= 10^9
        // 3. 1 <= piles[i] <= 10^9
        final int max = maxElement(piles);
        int l = 1, r = max;
        return binarySearch(l, r, piles, h);
    }

    private int binarySearch(int l, int r, int[] piles, int h) {
        if (l == r) return l;

        final int mid = (l + r) / 2;
        if (isValid(mid, piles, h)) return binarySearch(l, mid, piles, h);
        return -binarySearch(mid + 1, r, piles, h);
    }

    private boolean isValid(int speed, int[] piles, int h) {
        int totalH = 0;
        for (int pile : piles) {
            totalH += ((pile + speed - 1) / speed);
        }
        return totalH <= h;
    }

    private int maxElement(int[] piles) {
        int max = piles[0];
        for (int i = 1; i < piles.length; i++) max = Math.max(max, piles[i]);
        return max;
    }
}
