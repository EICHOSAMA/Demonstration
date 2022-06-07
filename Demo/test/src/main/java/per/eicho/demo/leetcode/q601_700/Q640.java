package per.eicho.demo.leetcode.q601_700;

/**
 * <p>640. Solve the Equation的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/solve-the-equation/">
 *   640. Solve the Equation</a>
 */
public final class Q640 {
    public String solveEquation(String equation) {
        // 1. 3 <= equation.length <= 1000
        // 2. equation has exactly one '='.
        // 3. equation consists of integers with an absolute value in the range [0, 100] without any leading zeros, 
        //    and the variable 'x'.        
        int cntX = 0;
        int cntNum = 0;
        final int n = equation.length();
        int p = 0;
        int tX = 1;
        int tNum = -1;
        while (p < n) {
            // "x+5-3+x=6+x-2"
            char ch = equation.charAt(p);

            if (ch == '=') {
                tX = -1; tNum = 1; p++;
                continue;
            }

            boolean isPositive = true;
            if (ch == '+') {
                isPositive = true;
                p++;
            } else if (ch == '-') {
                isPositive = false;
                p++;
            }

            int num = 0;
            boolean omitted = true;
            while (p < n && (ch = equation.charAt(p)) >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
                omitted = false;
                p++;
            }

            if (p < n && equation.charAt(p) == 'x') {
                // x
                if (omitted) num = 1; // omitted 1
                cntX += num * tX * (isPositive ? 1 : -1);
                p++;
            } else {
                // number
                cntNum += num * tNum * (isPositive ? 1 : -1);
                if (p < n && equation.charAt(p) == '=') {
                    tX = -1; tNum = 1;
                }
            }
        }

        // "x=x+4+1"    ⇒ "No solution"
        // "2x+1=x+1+x" ⇒ "Infinite solutions"

        if (cntX == 0) {
            return cntNum == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (cntNum / cntX) ;
    }

    public static void main(String[] args) {
        Q640 q640 = new Q640();
        System.out.println(q640.solveEquation("2x+1=x+1+x"));
        System.out.println(q640.solveEquation("x=x+4+1"));
        System.out.println(q640.solveEquation("x+5-3+x=6+x-2"));
        System.out.println(q640.solveEquation("0x=0"));
    }
}
