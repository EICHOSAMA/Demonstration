package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1309. Decrypt String from Alphabet to Integer Mapping 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/">
 *   1309. Decrypt String from Alphabet to Integer Mapping</a>
 */
public final class Q1309 {
    public String freqAlphabets(String s) {
        // 1. 1 <= s.length <= 1000
        // 2. s consists of digits and the '#' letter.
        // 3. s will be a valid string such that mapping is always possible.        
        final int OFFSET = 'a' - '1';
        final int OFFSET2 = 'j' - 10;

        final StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            final char ch = s.charAt(i);

            if (ch != '#') {
                sb.append((char)(ch + OFFSET));
            } else {
                final int code = getDigitAtIndex(s, i - 2) * 10 + getDigitAtIndex(s, i - 1);
                sb.append((char)(code + OFFSET2)); 
                // 10 -> 'j'
                i -= 2;
            }
        }
        return sb.reverse().toString();
    }

    private int getDigitAtIndex(String s, int idx) {
        return s.charAt(idx) - '0';
    }
}
