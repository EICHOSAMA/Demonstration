package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1071. Greatest Common Divisor of Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/greatest-common-divisor-of-strings/">
 *   1071. Greatest Common Divisor of Strings</a>
 */
public final class Q1071 {
    public String gcdOfStrings(String str1, String str2) {
        // 1. 1 <= str1.length, str2.length <= 1000
        // 2. str1 and str2 consist of English uppercase letters.        
        final String t = str1.substring(0, gcd(str1.length(), str2.length()));
        if (isDivisor(str1, t) && isDivisor(str2, t)) return t;
        return "";
    }

    public boolean isDivisor(String s, String t) {
        final int time = s.length() / t.length();
        final StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= time; ++i) {
            ans.append(t);
        }
        return ans.toString().equals(s);
    }

    private int gcd(int num1, int num2) {
        int temp;
        while (num2 != 0) {
            temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }
}
