package per.eicho.demo.leetcode.q401_500;

/**
 * <p>468. Validate IP Address 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/validate-ip-address/">
 *   468. Validate IP Address</a>
 */
public final class Q468 {

    private static final String RESULT_IPV4 = "IPv4";
    private static final String RESULT_IPV6 = "IPv6";
    private static final String RESULT_NEITHER = "Neither";
    
    public String validIPAddress(String queryIP) {
        // 1. queryIP consists only of English letters, digits and the characters '.' and ':'.
        
        int countDot = 0;
        int countColon = 0;
        for (int i = 0; i < queryIP.length(); i++) {
            final char ch = queryIP.charAt(i);

            if (ch == '.') {
                countDot++;
            } else if (ch == ':') {
                countColon++;
            }
        }

        if (countDot > 0 && countColon > 0) return RESULT_NEITHER;
        if (countDot == 3) return validIPV4(queryIP);
        if (countColon == 7) return validIPV6(queryIP);
        return RESULT_NEITHER;
    }

    private String validIPV4(String queryIP) {
        final String[] parts = queryIP.split("\\.");
        if (parts.length != 4) return RESULT_NEITHER;

        for (String part : parts) {
            final int len = part.length();
            if (len <= 0 || len > 3) return RESULT_NEITHER; // check len.
            if (part.charAt(0) == '0' && len > 1) return RESULT_NEITHER; // leading zero 

            int p = 0;
            for (int i = 0; i < len; i++) {
                final char ch = part.charAt(i);
                if (!isDigit(ch)) return RESULT_NEITHER;
                p *= 10;
                p += (ch - '0');
            }
            if (!(0 <= p && p <= 255)) return RESULT_NEITHER;
        }

        return RESULT_IPV4;
    }

    private String validIPV6(String queryIP) {
        queryIP = queryIP.toUpperCase();
        final String[] parts = queryIP.split(":");
        if (parts.length != 8) return RESULT_NEITHER;

        for (String part : parts) {
            final int len = part.length();

            // len [1, 4]
            if (len <= 0 || len > 4) return RESULT_NEITHER; // check len.
            for (int i = 0; i < len; i++) {
                final char ch = part.charAt(i);
                if (!isUpperHexChar(ch)) return RESULT_NEITHER;
            }
        }
        return RESULT_IPV6;
    }

    private boolean isDigit(char ch) { return '0' <= ch && ch <= '9'; }
    private boolean isUpperHexChar(char ch) { 
        if (isDigit(ch)) return true;
        final int code = ch - 'A' + 10;
        return 0xA <= code && code <= 0xF;
    }

    public static void main(String[] args) {
        Q468 q468 = new Q468();
        System.out.println(q468.validIPAddress("1.0.1."));
    }
}
