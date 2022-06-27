package per.eicho.demo.leetcode.q801_900;

/**
 * <p>877. Stone Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/stone-game/">
 *   877. Stone Game</a>
 */
public final class Q877 {

    int[][] memo;
    int[] piles;

    public boolean stoneGame(int[] piles) {
        // 1. 2 <= piles.length <= 500
        // 2. piles.length is even.
        // 3. 1 <= piles[i] <= 500
        // 4. sum(piles[i]) is odd.        
        // Alice and Bob take turns, with Alice starting first
        // Assuming Alice and Bob play optimally, 
        // return true if Alice wins the game, or false if Bob wins.
        final int n = piles.length;
        memo = new int[n][n];
        for (int i = 0; i < n - 1; i++) memo[i][i + 1] = Math.max(piles[i], piles[i + 1]);
        this.piles = piles;
        final int maxStones = search(0, n - 1);
        int sum = 0;
        for (int stone : piles) sum += stone;
        return maxStones > (sum - maxStones);
    }

    private int search(int l, int r) {
        int maxStones;
        if ((maxStones = memo[l][r]) != 0) return maxStones;

        final int pL = piles[l];
        final int pR = piles[r];

        maxStones = Math.max(
            Math.max(pL + search(l + 2, r), pL + search(l + 1, r - 1)),
            Math.max(pR + search(l, r - 2), pR + search(l + 1, r - 1)));
        return memo[l][r] = maxStones;
    }
}
