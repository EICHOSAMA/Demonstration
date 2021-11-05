package per.eicho.demo.leetcode.q601_700;

/**
 * <p>657. Robot Return to Origin 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/robot-return-to-origin/">657. Robot Return to Origin</a>
 */
public final class Q657 {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < moves.length(); i++) {
            final char move = moves.charAt(i);

            /*
              "L": left, x -1 
              "R": right, x + 1
              "U": up, y + 1
              "D": down, y - 1
             */
            switch (move) {
                case 'L': 
                    x--; 
                    break;
                case 'R':
                    x++; 
                    break;
                case 'U':
                    y++; 
                    break;
                default:
                    y--;
            }
        }

        return x == 0 && y == 0;
    }
}
