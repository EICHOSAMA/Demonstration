package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1306. Jump Game III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/jump-game-iii/">
 *   1306. Jump Game III</a>
 */
public final class Q1306 {

    boolean[] canReach;
    int n;
    int[] arr;

    public boolean canReach(int[] arr, int start) {
        // 1. 1 <= arr.length <= 5 * 104
        // 2. 0 <= arr[i] < arr.length
        // 3. 0 <= start < arr.length
        n = arr.length;
        canReach = new boolean[n];
        this.arr = arr;
        return dfs(start);
    }

    private boolean dfs(int p) {
        if (p < 0 || p >= n) return false; // out of bound.
        if (canReach[p]) return false; // already accessed.

        canReach[p] = true;
        if (arr[p] == 0) return true;
        return dfs(p - arr[p]) || dfs(p + arr[p]);
    }

    public static void main(String[] args) {
        Q1306 q1306 = new Q1306();
        System.out.println(q1306.canReach(new int[]{0,3,0,6,3,3,4}, 6)); // true
    }
}
