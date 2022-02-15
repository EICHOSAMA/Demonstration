package per.eicho.demo.leetcode.q801_900;

/**
 * <p>860. Lemonade Change 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/lemonade-change/">860. Lemonade Change</a>
 */
public final class Q860 {
    public boolean lemonadeChange(int[] bills) {
        // 1. 1 <= bills.length <= 10^5
        // 2. bills[i] is either 5, 10, or 20.        
        int num5 = 0, num10 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                num5++;
                continue;
            }

            if (bill == 10) {
                num5--;
                num10++;
                if (num5 < 0) return false;
                continue;
            }

            num10--; num5--; // num10 - 1, num5 - 1
            if (num10 < 0) { // or num5 - 3
                num10++;
                num5 -= 2;
            }

            if (num5 < 0) return false;
        }
        return true;
    }
}
