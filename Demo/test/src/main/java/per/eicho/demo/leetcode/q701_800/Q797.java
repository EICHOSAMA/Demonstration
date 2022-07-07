package per.eicho.demo.leetcode.q701_800;

/**
 * <p>779. K-th Symbol in Grammar 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/k-th-symbol-in-grammar/">
 *   779. K-th Symbol in Grammar</a>
 */
public final class Q797 {
    public int kthGrammar(int n, int k) {
        // 1. 1 <= n <= 30
        // 2. 1 <= k <= 2^n - 1
        return _kthGrammar(n, --k);
    }

    private int _kthGrammar(int n, int k) {
        // k (0-indexed)
        if (k == 0) return 0;
        final int parent = _kthGrammar(n - 1, k / 2);
        // 0 ⇒ 01, 1 ⇒ 10
        return k % 2 == 0 ? parent : parent ^ 1;
    }
}
