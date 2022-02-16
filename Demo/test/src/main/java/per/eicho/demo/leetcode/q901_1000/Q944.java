package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>944. Delete Columns to Make Sorted 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/delete-columns-to-make-sorted/">944. Delete Columns to Make Sorted</a>
 */
public final class Q944 {
    public int minDeletionSize(String[] strs) {
        // 1. n == strs.length
        // 2. 1 <= n <= 100
        // 3. 1 <= strs[i].length <= 1000
        // 4. strs[i] consists of lowercase English letters.        
        final int n = strs.length;
        final int m = strs[0].length();
        
        int result = 0;
        for (int col = 0; col < m; col++) {
            char ch = strs[0].charAt(col);

            boolean delete = false;
            for (int row = 1; row < n; row++) {
                final char nextCh = strs[row].charAt(col);

                if (nextCh < ch) {
                    delete = true;
                    break;
                } else {
                    ch = nextCh;
                }
            }

            if (delete) result++;
        }

        return result;
    }
}
