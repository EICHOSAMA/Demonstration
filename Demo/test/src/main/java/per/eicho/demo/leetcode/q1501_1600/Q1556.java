package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1556. Thousand Separator 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/thousand-separator/">
 *   1556. Thousand Separator</a>
 */
public final class Q1556 {
    public String thousandSeparator(int n) {
        // 1. 0 <= n <= 2^31 - 1
        if (n == 0) return "0";
        final StringBuilder sb = new StringBuilder();        
        int count = 0;
        while (n != 0) {
            if (count == 3) {
                sb.append('.');
                count = 0;
            }
            sb.append(n % 10);
            n /= 10;
            count++;
        }
        return sb.reverse().toString();
    }
}
