package per.eicho.demo.leetcode.q001_100;

/**
 * <p>65. Valid Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-number/">65. Valid Number</a>
 */
final public class Q65 {
    private static final char CHAR_PLUS_SIGN = '+';
    private static final char CHAR_MINUS_SIGN = '-';

    private static final char CHAR_0 = '0';
    private static final char CHAR_9 = '9';

    private static final char CHAR_E = 'E';
    private static final char CHAR_PERIOD = '.';
    

    public boolean isNumber(String s) {
        // valid number := (decimal | integer) e|E integer
        // integer := (+ | -) digit +
        // decimal := (+ | -) (digit+ . | digit+ . digit+ |. digit+) 
        s = s.toUpperCase();

        if (containsUnexpectedCharacter(s)) {
            return false;
        }

        if (containsMoreThanOneE(s)) {
            return false;
        }

        final int index = s.indexOf(CHAR_E);
        if (index == -1) {
            return isValidDecimal(s) || 
                   isValidInteger(s);
        } else {
            final String s1 = s.substring(0, index);

            return (isValidDecimal(s1) || isValidInteger(s1)) && 
                   isValidInteger(s.substring(index + 1));
        }
    }

    private boolean containsUnexpectedCharacter(final String s) {
        final char L = 'A';
        final char R = 'Z';
        
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);

            if (c >= L && c <= R && c != CHAR_E) {
                return true;
            }
        }

        return false;
    }

    private boolean containsMoreThanOneE(final String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == CHAR_E) {
                count++;

                if (count >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValidInteger(final String s) {
        if (s.length() == 0) {
            return false;
        }

        int si = 0; // start index
        final char firstChar = s.charAt(0);
        if (isSign(firstChar)) {
            si++;
        }

        if (s.length() == si) {
            return false;
        }
        
        for (int i = si; i < s.length(); i++) {
            final char c = s.charAt(i);

            if (!isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    private boolean containsMoreThanOnePeriod(final String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == CHAR_PERIOD) {
                count++;

                if (count >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValidDecimal(final String s) {
        if (s.length() <= 1) {
            return false;
        }

        if (containsMoreThanOnePeriod(s)) {
            return false;
        }

        int si = 0; // start index
        final char firstChar = s.charAt(0);
        if (isSign(firstChar)) {
            si++;
        }

        final int index = s.indexOf(CHAR_PERIOD);
        if (index == -1) {
            return false;
        }


        final String s1 = s.substring(si, index);
        final String s2 = s.substring(index + 1);

        if (!onlyDigits(s1) || !onlyDigits(s2)) {
            return false;
        }

        // (digit+ . | digit+ . digit+ |. digit+) 
        final boolean r1 = containsMoreThanOneAndOnlyDigit(s1);
        final boolean r2 = containsMoreThanOneAndOnlyDigit(s2);  

        if (r1 || r2) {
            return true;
        }

        return false;
    }

    private boolean onlyDigits(final String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i))) {
                return false; 
            }
        }
        return true;
    }

    private boolean containsMoreThanOneAndOnlyDigit(final String s) {
        if (s.length() < 1) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i))) {
                return false;
            }            
        }

        return true;
    }

    private boolean isSign(final char c) {
        if (c == CHAR_PLUS_SIGN || c == CHAR_MINUS_SIGN) {
            return true;
        }
        return false;
    }

    private boolean isDigit(final char c) {
        if (CHAR_0 <= c && c <= CHAR_9) {
            return true;
        }
        return false;
    }

}
