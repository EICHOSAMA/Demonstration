package per.eicho.demo.leetcode.q001_100;

/**
 * <p>13. Roman to Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/roman-to-integer/">13. Roman to Integer</a>
 */
public final class Q13 {
    public static enum STATE {
        Initialize {
            @Override
            public STATE next(char ch) {
                // ch is I,V,X,L,C,D,M

                if (ch == 'I') {
                    return I;
                }
                if (ch == 'V') {
                    result += 5;
                    return Initialize;
                }

                if (ch == 'X') {
                    return X;
                }

                if (ch == 'L') {
                    result += 50;
                    return Initialize;
                }
                if (ch == 'C') {
                    return C;
                }
                if (ch == 'D') {
                    result += 500;
                    return Initialize;
                }
                if (ch == 'M') {
                    result += 1000;
                    return Initialize;
                }

                return END;
            }
        },
        I {
            @Override
            public STATE next(char ch) {
                // ch is I,V,X,L,C,D,M
                if (ch == 'I') {
                    result += 1;
                    return I;
                }

                if (ch == 'V') { // IV = 4
                    result += 4;
                    return Initialize;
                }

                if (ch == 'X') { // IX = 9
                    result += 9;
                    return Initialize;
                }

                if (ch == 'L') {
                    result += 1;
                    return Initialize;
                }

                if (ch == 'C') {
                    result += 1;
                    return Initialize;
                }

                if (ch == 'D') {
                    result += 1;
                    return Initialize;
                }

                if (ch == 'M') {
                    result += 1;
                    return Initialize;
                }

                result += 1;
                return END;
            }
        },
        X {
            @Override
            public STATE next(char ch) {
                // ch is I,V,X,L,C,D,M
                if (ch == 'I') {
                    result += 10;
                    return I;
                }

                if (ch == 'V') {
                    result += 15;
                    return Initialize;
                }

                if (ch == 'X') {
                    result += 10;
                    return X;
                }

                if (ch == 'L') {
                    result += 40;
                    return Initialize; // XL
                }

                if (ch == 'C') {
                    result += 90;
                    return Initialize; // XC
                }

                if (ch == 'D') {
                    result += 510;
                    return Initialize;
                }

                if (ch == 'M') {
                    result += 1010;
                    return Initialize;
                }


                result += 10;
                return END;
            }
        },
        C{
            @Override
            public STATE next(char ch) {
                // ch is I,V,X,L,C,D,M
                if (ch == 'I') {
                    result += 100;
                    return I;
                }

                if (ch == 'V') {
                    result += 105;
                    return Initialize;
                }

                if (ch == 'X') {
                    result += 100;
                    return X;
                }

                if (ch == 'L') {
                    result += 150;
                    return Initialize;
                }

                if (ch == 'C') {
                    result += 100;
                    return C;
                }

                if (ch == 'D') {
                    result += 400;
                    return Initialize; // CD
                }

                if (ch == 'M') {
                    result += 900;
                    return Initialize; // CM
                }

                result += 100;
                return END;
            }
        },
        END {
            @Override
            public STATE next(char ch) {
                throw new UnsupportedOperationException("Ended");
            }
        };

        public abstract STATE next(char ch);
    }

    private static int result = 0;

    public static int romanToInt(String s) {
        final int len = s.length();
        result = 0;

        STATE state = STATE.Initialize;
        int i = 0;
        char ch;

        while (i < len) {
            ch = s.charAt(i++);
            state = state.next(ch);
        }
        state.next('\n'); // to end.

        return result;
    }
}
