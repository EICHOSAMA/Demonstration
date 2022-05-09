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
        final List<Integer> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) result.add(i);

        result.sort((num1, num2) -> {
            int cnt1 = count(num1);
            int cnt2 = count(num2);
            while (getDigit(num1, cnt1) == getDigit(num2, cnt2)) {
                cnt1 /= 10;
                cnt2 /= 10;
            }
            return Integer.compare(getDigit(num1, cnt1), getDigit(num2, cnt2));
        });

        return result;
    }

    private int count(int num) {
        int count = 1;
        while (num >= count * 10) {
            count *= 10;
        }
        return count;
    }

    private int getDigit(int num, int cnt) {
        if (cnt == 0) return -1;
        return (num / cnt) % 10;
    }

    public static void main(String[] args) {
        Q386 q386 = new Q386();
        List<Integer> result = q386.lexicalOrder(13);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + ",");
        }
    }
}
