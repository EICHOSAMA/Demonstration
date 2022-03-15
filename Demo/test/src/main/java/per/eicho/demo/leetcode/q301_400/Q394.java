package per.eicho.demo.leetcode.q301_400;

/**
 * <p>394. Decode String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/decode-string/">394. Decode String</a>
 */
public final class Q394 {
    public String decodeString(String s) {
        // 1. 1 <= s.length <= 30
        // 2. s consists of lowercase English letters, digits, and square brackets '[]'.
        // 3. s is guaranteed to be a valid input.
        // 4. All the integers in s are in the range [1, 300].
        return decodeString(s, 0, s.length());
    }

    private String decodeString(String s, int l, int r) {
        if (l == r) return "";
        // [l, r)
        final StringBuilder sb = new StringBuilder();

        for (int i = l; i < r; i++) {
            final char ch = s.charAt(i);

            if (isLetter(ch)) {
                sb.append(ch);
                continue;
            }

            if (isDigit(ch)) {
                int j = i + 1;
                while (isDigit(s.charAt(j))) j++;
                final int subL = j; // '['

                final int count = Integer.parseInt(s.substring(i, j));

                assert s.charAt(j) == '[';
                int lv = 1;
                j++;
                while (lv != 0) {
                    final char pch = s.charAt(j);
                    if (pch == '[') {
                        lv++;
                    } else if (pch == ']') {
                        lv--;
                    }
                    j++;
                }
                final int subR = j - 1; // ']'

                final String decodedSubString = decodeString(s, subL + 1, subR);
                for (int time = 0; time < count; time++) sb.append(decodedSubString);
                i = subR;
                continue;
            }

            sb.append(ch);
        }

        return sb.toString();
    }

    private boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }
}
