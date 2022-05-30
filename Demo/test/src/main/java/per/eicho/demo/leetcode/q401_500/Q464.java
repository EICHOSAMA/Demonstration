package per.eicho.demo.leetcode.q401_500;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>464. Can I Win 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/can-i-win/">464. Can I Win</a>
 */
public final class Q464 {
    final Map<Integer, Boolean> memo = new HashMap<Integer, Boolean>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 1. 1 <= maxChoosableInteger <= 20
        // 2. 0 <= desiredTotal <= 300
        if ((1 + maxChoosableInteger) * (maxChoosableInteger) / 2 < desiredTotal) return false; 
        return dfs(maxChoosableInteger, 0, desiredTotal, 0);
    }

    public boolean dfs(int maxChoosableInteger, int mark, int desiredTotal, int currentTotal) {
        if (memo.containsKey(mark)) return memo.get(mark);

        boolean result = false;
        for (int i = 0; i < maxChoosableInteger; i++) {
            if (((mark >> i) & 1) != 0) continue; // already used
            final int num = i + 1;

            if (num + currentTotal >= desiredTotal) {
                result = true;
                break;
            }
                
            if (!dfs(maxChoosableInteger, mark | (1 << i), desiredTotal, currentTotal + num)) {
                result = true;
                break;
            }
        }

        memo.put(mark, result);
        return result;
    }

    public static void main(String[] args) {
        Q464 q464 = new Q464();
        System.out.println(q464.canIWin(10, 40));
        //System.out.println(q464.canIWin(20, 300));
    }
}
