package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1510. Stone Game IV 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/stone-game-iv/">
 *   1510. Stone Game IV</a>
 */
public final class Q1510 {
    private final static int WIN = 1;
    private final static int LOSE = -1;
    
    int[] f;

    public boolean winnerSquareGame(int n) {
        // 1 <= n <= 10^5
        f = new int[n + 1]; // [0, n]
        f[0] = LOSE;

        for (int i = 0; i < n; i++) {
            final int fi = f[i];

            int j = 1;
            int square = 1;
            while (i + square <= n) {
                final int fj = f[i + square];

                if (fj != WIN) {
                    f[i + square] = fi * -1;
                }

                j++;
                square = j * j;
            }
        }

        return f[n] == WIN;
    }
    
    public static void main(String[] args) {
        Q1510 q1510 = new Q1510();

        System.out.println(q1510.winnerSquareGame(1));
        System.out.println(q1510.winnerSquareGame(2));
        System.out.println(q1510.winnerSquareGame(4));
    }
}
