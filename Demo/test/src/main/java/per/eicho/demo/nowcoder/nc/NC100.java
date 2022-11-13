package per.eicho.demo.nowcoder.nc;

public final class NC100 {
    private static final int MATCHING_STATE_INIT = 0;
    private static final int MATCHING_STATE_SIGN_MATCHED = 1;
    private static final int MATCHING_STATE_MATCHING_DIGITS = 2;

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param s string字符串 
     * @return int整型
     */
    public int StrToInt (String s) {
        // len: [0, 100]
        if (s == null || s.length() == 0) return 0;
        final long lBound = Integer.MIN_VALUE;
        final long rBound = Integer.MAX_VALUE;
        final int n = s.length();

        int p = 0;
        int matchingState = 0;
        long sign = 0;
        long num = 0;
        while (p < n) {
            final char ch = s.charAt(p++);
            if (matchingState == MATCHING_STATE_INIT) {
                if (ch == ' ') continue;
                if (isDigit(ch)) {
                    matchingState = MATCHING_STATE_MATCHING_DIGITS;
                    sign = 1; // default sign.
                    num = toDigit(ch);
                    continue;
                }
                if (ch == '+') {
                    matchingState = MATCHING_STATE_SIGN_MATCHED;
                    sign = 1;
                    continue;
                } else if (ch == '-') {
                    matchingState = MATCHING_STATE_SIGN_MATCHED;
                    sign = -1;
                    continue;
                }
                return 0;
            } else if (matchingState == MATCHING_STATE_SIGN_MATCHED) {
                if (ch == ' ') continue;
                if (isDigit(ch)) {
                    matchingState = MATCHING_STATE_MATCHING_DIGITS;
                    num = toDigit(ch);
                    continue;
                }
                return 0;
            } else { /* matchingState == MATCHING_STATE_MATCHING_DIGITS */
                if (isDigit(ch)) {
                    num = num * 10L + toDigit(ch);

                    final long checkNum = num * sign;
                    if (checkNum > rBound) return Integer.MAX_VALUE;
                    if (checkNum < lBound) return Integer.MIN_VALUE;
                } else {
                    break; // stop matching
                }
            }
        }

        return (int)(num * sign);
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private int toDigit(char ch) {
        return ch - '0';
    }

    public static void main(String[] args) {
        NC100 nc100 = new NC100();
        System.out.println(nc100.StrToInt("82"));
    }
}
