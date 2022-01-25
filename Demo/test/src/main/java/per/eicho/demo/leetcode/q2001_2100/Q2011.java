package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2011. Final Value of Variable After Performing Operations 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/final-value-of-variable-after-performing-operations/">2011. Final Value of Variable After Performing Operations</a>
 */
public final class Q2011 {
    public int finalValueAfterOperations(String[] operations) {
        // 1. 1 <= operations.length <= 100
        // 2. operations[i] will be either "++X", "X++", "--X", or "X--".
        int x = 0;
        for (String operation : operations) {
            if (operation.charAt(1) == '-') {
                x--;
            } else {
                x++;
            }
        }
        return x;
    }
}
