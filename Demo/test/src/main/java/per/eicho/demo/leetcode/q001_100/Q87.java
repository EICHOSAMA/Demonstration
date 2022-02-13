package per.eicho.demo.leetcode.q001_100;

import java.util.Date;

/**
 * <p>87. Scramble String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/scramble-string/">87. Scramble String</a>
 */
public final class Q87 {

    int[][][][] f;

    public boolean isScramble(String s1, String s2) {
        if (check(s1, s2) == false) return false;
        final int n = s1.length();
        f = new int[n][n][n][n];
        return isScramble(s1, 0, s1.length() - 1, s2, 0, s2.length() - 1);
    }

    private boolean check(String s1, String s2) {
        int mark = 0;
        for (int i = 0; i < s1.length(); i++) {
            mark ^= s1.charAt(i);
            mark ^= s2.charAt(i);
        }
        return mark == 0;
    }

    private boolean isScramble(
        final String s1, int l1, int r1, 
        final String s2, int l2, int r2)  {
        if (l1 == r1) return true; // len = 1
        if (f[l1][r1][l2][r2] != 0) return f[l1][r1][l2][r2] > 0 ? true : false;
        
        int cursor = l1;
        int cursorL = l2;
        int cursorR = r2;

        int markL = 0, markR = 0;

        while (cursor < r1) {
            final int c = s1.charAt(cursor);
            final int cL = s2.charAt(cursorL);
            final int cR = s2.charAt(cursorR);

            markL = markL ^ c ^ cL;
            markR = markR ^ c ^ cR;

            if (markL == 0) {
                if (cursor - l1 < r1 - cursor - 1) {
                    if (isScramble(s1, l1, cursor, s2, l2, cursorL) &&
                        isScramble(s1, cursor + 1, r1, s2, cursorL + 1, r2)) {
                        f[l1][r1][l2][r2] = 1;
                        return true;
                    }
                } else {
                    if (isScramble(s1, cursor + 1, r1, s2, cursorL + 1, r2) &&
                        isScramble(s1, l1, cursor, s2, l2, cursorL)) {
                        f[l1][r1][l2][r2] = 1;
                        return true;
                    }
                }
            }

            if (markR == 0) {
                if (cursor - l1 < r1 - cursor - 1) {
                    if (isScramble(s1, l1, cursor, s2, cursorR, r2) && 
                        isScramble(s1, cursor + 1, r1, s2, l2, cursorR - 1)) {
                        f[l1][r1][l2][r2] = 1;
                        return true;
                    }
                } else {
                    if (isScramble(s1, cursor + 1, r1, s2, l2, cursorR - 1) && 
                        isScramble(s1, l1, cursor, s2, cursorR, r2)) {
                        f[l1][r1][l2][r2] = 1;
                        return true;
                    }
                }
            }

            cursor++;
            cursorL++;
            cursorR--;
        }

        f[l1][r1][l2][r2] = -1;
        return false;
    }

    public static void main(String[] args) {
        Q87 q87 = new Q87();
        System.out.println(new Date());
        System.out.println(q87.isScramble("eebaacbcbcadaaedceaaacadccd", "eadcaacabaddaceacbceaabeccd"));
        System.out.println(new Date());
    }
}
