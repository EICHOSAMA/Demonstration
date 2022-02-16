package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>989. Add to Array-Form of Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-to-array-form-of-integer/">989. Add to Array-Form of Integer</a>
 */
public final class Q997 {
    public int findJudge(int n, int[][] trust) {
        // 1. 1 <= n <= 1000
        // 2. 0 <= trust.length <= 10^4
        // 3. trust[i].length == 2
        // 4. All the pairs of trust are unique.
        // 5. ai != bi
        // 6. 1 <= ai, bi <= n
        if (trust.length == 0) return -1;
        final int[] trustCount = new int[n + 1];
        final int[] trustedCount = new int[n + 1];

        for (int i = 0; i < trust.length; i++) {
            final int[] trustPair = trust[i];

            trustCount[trustPair[0]]++;
            trustedCount[trustPair[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (trustCount[i] == 0 && trustedCount[i] == n - 1) return i; 
        }

        return -1;
    }
}
