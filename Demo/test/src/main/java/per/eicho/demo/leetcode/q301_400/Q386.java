package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>386. Lexicographical Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/lexicographical-numbers/">
 *   386. Lexicographical Numbers</a>
 */
public final class Q386 {
    public List<Integer> lexicalOrder(int n) {
        // 1. 1 <= n <= 5 * 10^4
        final List<Integer> result = new ArrayList<>();
        int num = 1;
        
        for (int i = 0; i < n; i++) {
            result.add(num);
            if (num * 10 <= n) {
                num *= 10;
                continue;
            }

            while (lastDigit(num) == 9 || num + 1 > n) num /= 10;
            num++;
        }
        return result;
    }

    private int lastDigit(int num) {
        return num % 10;
    }

    public static void main(String[] args) {
        Q386 q386 = new Q386();
        List<Integer> result = q386.lexicalOrder(13);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + ",");
        }
    }
}
