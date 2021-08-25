package per.eicho.demo.leetcode.q1_100;

/**
 * <p>72. Edit Distance 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/edit-distance/">72. Edit Distance</a>
 */
final public class Q72 {
    public int minDistance(String word1, String word2) {
        // 0 <= word1.length, word2.length <= 500
        // word1 and word2 consist of lowercase English letters.
        final int len1 = word1.length();
        final int len2 = word2.length();

        // 1. parameter check.
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;

        // 2. init 
        // Def: f[i][j] is the edit distance of substring word1[0, i] and word2[0, j]
        // 2.1 allocation 
        final int[][] f = new int[len1 + 1][len2 + 1];

        // 2.2 init
        for (int i = 0; i < len1 + 1; i++) {
            f[i][0] = i;
        }
        for (int i = 0; i < len2 + 1; i++) {
            f[0][i] = i;
        }

        // 3. dynamic programming
        for(int i = 1; i < len1 + 1; i++ ) {
            for(int j = 1; j < len2 + 1;j++) {
                if (word1.charAt(i - 1)==word2.charAt(j-1)){
                    f[i][j] = f[i - 1][j - 1];
                    continue;
                }
                f[i][j]=1+min(f[i - 1][j], f[i - 1][j - 1], f[i][j - 1]);
            }
        }
        return f[len1][len2];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
