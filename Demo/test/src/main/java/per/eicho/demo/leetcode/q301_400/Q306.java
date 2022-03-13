package per.eicho.demo.leetcode.q301_400;

/**
 * <p>306. Additive Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/additive-number/">
 *   306. Additive Number</a>
 */
public final class Q306 {
    public boolean isAdditiveNumber(String num) {
        // 1. 1 <= num.length <= 35
        // 2. num consists only of digits.        
        final int n = num.length();

        
        long firstNum = 0;
        for (int i = 0; (i + 1) * 2 <= n - 1; i++) {
            if (firstNum == 0 && i > 0) break;

            final int digit1 = num.charAt(i) - '0';
            firstNum = firstNum * 10 + digit1;

            // cannot have leading zero
            if (num.charAt(i + 1) == '0') {
                if (canFormAdditiveSequence(firstNum, 0, num, i + 2)) return true;
                continue;
            }
            long secondNum = 0;
            for (int j = i + 1; 2 * Math.max(i + 1, j - i) <= n; j++) {
                final int digit2 = num.charAt(j) - '0';
                secondNum = secondNum * 10 + digit2;
                if (canFormAdditiveSequence(firstNum, secondNum, num, j + 1)) return true;
            }
        }
        return false;
    }

    private boolean canFormAdditiveSequence(long firstNum, long secondNum, String num, int p) {
        if (num.charAt(p) == '0' && firstNum + secondNum != 0) return false;

        while (p < num.length()) {
            long nextNum = firstNum + secondNum;
            final String str = Long.toString(nextNum);
            
            int i = 0;
            while (i < str.length() && p < num.length()) {
                if (str.charAt(i++) != num.charAt(p++)) return false;
            }

            if (i != str.length()) return false;

            firstNum = secondNum;
            secondNum = nextNum;
        }
        return true;
    }

    public static void main(String[] args) {
        Q306 q306 = new Q306();
        System.out.println(q306.isAdditiveNumber("101"));
        System.out.println(q306.isAdditiveNumber("0235813"));
    }
}
