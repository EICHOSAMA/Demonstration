package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;

/**
 * <p>937. Reorder Data in Log Files 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reorder-data-in-log-files/">937. Reorder Data in Log Files</a>
 */
public final class Q937 {

    final static int LETTER_LOG = 1;
    final static int DIGIT_LOG = 2;

    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            final int type1 = isLetterLog(log1) ? LETTER_LOG : DIGIT_LOG;
            final int type2 = isLetterLog(log2) ? LETTER_LOG : DIGIT_LOG;

            if (type1 == DIGIT_LOG && type2 == DIGIT_LOG) return 0; // 3. The digit-logs maintain their relative ordering.
            if (type1 == LETTER_LOG && type2 == DIGIT_LOG) return -1; //  1. The letter-logs come before all digit-logs.
            if (type1 == DIGIT_LOG && type2 == LETTER_LOG) return 1; // 1. The letter-logs come before all digit-logs.
            
            final String[] log1Parts = log1.split(" ", 2);
            final String[] log2Parts = log2.split(" ", 2);

            // 2. The letter-logs are sorted lexicographically by their contents.
            // If their contents are the same, then sort them lexicographically by their identifiers.
            final int compareContents = log1Parts[1].compareTo(log2Parts[1]); 
            if (compareContents != 0) return compareContents;
            return log1Parts[0].compareTo(log2Parts[0]);
        });
        return logs;
    }

    private boolean isLetterLog(String log) {
        final char lastChar = log.charAt(log.length() - 1);
        // Letter-logs: All words (except the identifier) consist of lowercase English letters.
        return 'a' <= lastChar && lastChar <= 'z';
    }
}
