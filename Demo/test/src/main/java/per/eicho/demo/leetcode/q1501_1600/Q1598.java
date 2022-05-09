package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1598. Crawler Log Folder 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/crawler-log-folder/">
 *   1598. Crawler Log Folder</a>
 */
public final class Q1598 {
    public int minOperations(String[] logs) {
        // 1. 1 <= logs.length <= 10^3
        // 2. 2 <= logs[i].length <= 10
        // 3. logs[i] contains lowercase English letters, digits, '.', and '/'.
        // 4. logs[i] follows the format described in the statement.
        // 5. Folder names consist of lowercase English letters and digits.
        int depth = 0;
        for (String log : logs) {
            if (log.charAt(0) == '.') {
                if (log.charAt(1) == '.') {
                    // Move to the parent folder
                    if (depth > 0) depth--;
                } else {
                    // Remain in the same folder.
                    // do no thing
                }
            } else {
                depth++;
            }
        }
        return depth;
    }
}
