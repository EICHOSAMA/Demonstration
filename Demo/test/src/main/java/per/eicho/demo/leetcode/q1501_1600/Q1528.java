package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1528. Shuffle String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shuffle-string/">
 *   1528. Shuffle String</a>
 */
public final class Q1528 {
    public String restoreString(String s, int[] indices) {
        // 1. s.length == indices.length == n
        // 2. 1 <= n <= 100
        // 3. s consists of only lowercase English letters.
        // 4. 0 <= indices[i] < n
        // 5. All values of indices are unique.        
        final int n = indices.length;
        final char[] str = new char[n];
        for (int i = 0; i < n; i++) str[indices[i]] = s.charAt(i);
        return new String(str);
    }
}
