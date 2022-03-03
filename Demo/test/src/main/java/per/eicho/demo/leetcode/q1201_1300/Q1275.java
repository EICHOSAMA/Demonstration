package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1275. Find Winner on a Tic Tac Toe Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/">
 *   1275. Find Winner on a Tic Tac Toe Game</a>
 */
public final class Q1275 {
    public String tictactoe(int[][] moves) {
        // 1. 1 <= moves.length <= 9
        // 2. moves[i].length == 2
        // 3. 0 <= rowi, coli <= 2
        // 4. There are no repeated elements on moves.
        // 5. moves follow the rules of tic tac toe.        
        final int[][] board = new int[3][3];

        int val = 1;
        for (int[] move : moves) {
            board[move[0]][move[1]] = val;
            val *= -1;
        }

        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += board[i][j];
            }

            if (sum == 3) return "A";
            if (sum == -3) return "B";

            sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += board[j][i];
            }

            
            if (sum == 3) return "A";
            if (sum == -3) return "B";
        }

        int sum = board[0][0] + board[1][1] + board[2][2];
        if (sum == 3) return "A";
        if (sum == -3) return "B";

        sum = board[2][0] + board[1][1] + board[0][2];
        if (sum == 3) return "A";
        if (sum == -3) return "B";
        
        return moves.length == 9 ? "Draw" : "Pending";
    }
}
