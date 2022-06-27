package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1689. Partitioning Into Minimum Number Of Deci-Binary Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/">
 *   1689. Partitioning Into Minimum Number Of Deci-Binary Numbers</a>
 */
public final class Q1689 {
    public int minPartitions(String n) {
        // 1. 1 <= n.length <= 10^5
        // 2. n consists of only digits.
        // 3. n does not contain any leading zeros and represents a positive integer.
        final int len = n.length();
        int result = 1;
        for (int i = 0; i < len && result < 9; i++) {
            final int digit = n.charAt(i) - '0';
            if (digit > result) {
                result = digit;
            }
        }
        return result;
    }
}
