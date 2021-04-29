package per.eicho.demo.leetcode.q401_500;

/**
 * <p>482. License Key Formatting 的题解代码 </p>
 * 
 * <pre>
 *  You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. 
 *  The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 *  We want to reformat the string s such that each group contains exactly k characters, 
 *  except for the first group, which could be shorter than k but still must contain at least one character. 
 *  Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.
 * 
 *  Return the reformatted license key.
 * 
 * Example 1:
 *    Input: s = "5F3Z-2e-9-w", k = 4
 *    Output: "5F3Z-2E9W" 
 *    Explanation: The string s has been split into two parts, each part has 4 characters.
 *    Note that the two extra dashes are not needed and can be removed.
 * 
 * Example 2:
 *    Input: s = "2-5g-3-J", k = 2
 *    Output: "2-5G-3J"
 *    Explanation: The string s has been split into three parts, 
 *    each part has 2 characters except the first part as it could be shorter as mentioned above.
 * 
 *  Constraints:
 *   1. 1 <= s.length <= 105
 *   2. s consists of English letters, digits, and dashes '-'.
 *   3. 1 <= k <= 104
 * </pre>
 * @see <a href="https://leetcode.com/problems/license-key-formatting/">482. License Key Formatting</a>
 */
final public class Q482 {
    private static final char DAST_CHARACTER = '-';
    
    public String licenseKeyFormatting(String s, int k) {
        s = s.toUpperCase();
        final StringBuilder sb = new StringBuilder(s.length());

        for (int i = s.length() - 1; i >= 0; i--) {
            final char c = s.charAt(i);

            if (DAST_CHARACTER == c) {
                continue;
            }
            
            final int currentLen = sb.length();
            if (currentLen % (k + 1) == k) {
                sb.append(DAST_CHARACTER);
            }
            sb.append(c);
        }

        return sb.reverse().toString();
    }
}
