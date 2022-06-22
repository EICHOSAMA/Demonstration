package per.eicho.demo.leetcode.q701_800;

/**
 * <p>714. Best Time to Buy and Sell Stock with Transaction Fee 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/">
 *   714. Best Time to Buy and Sell Stock with Transaction Fee</a>
 */
public final class Q738 {
    public int monotoneIncreasingDigits(int n) {
        // 1. 0 <= n <= 10^9
        if (n < 10) return n;
        final int[] digits = getDigits(n); 
        final int len = digits.length;
        final int[] monotoneDigits = new int[len];

        int p = 1;
        monotoneDigits[0] = digits[0];
        boolean to9 = false;
        while (p < len) {
            
            if (to9) {
                monotoneDigits[p++] = 9;
                continue;
            }

            // to9 = false;
            final int bound = digits[p];
            if (monotoneDigits[p - 1] <= bound) {
                monotoneDigits[p++] = bound;
            } else {
                // > bound
                int p2 = p - 1;
                monotoneDigits[p2]--;
                
                while (p2 - 1 >= 0 && monotoneDigits[p2 - 1] > monotoneDigits[p2]) {
                    monotoneDigits[p2 - 1]--;
                    p2--;
                }
                to9 = true;
                p = p2 + 1;
            }
        }
    
        return toNum(monotoneDigits);
    }

    private int toNum(int[] digits) {
        int num = 0;
        for (int i = 0; i < digits.length; i++) {
            num = num * 10 + digits[i];
        }
        return num;
    }

    private int[] getDigits(int n) {
        // 10 ⇒ [1, 0]
        int count = 0;
        int temp = n;
        while (temp != 0) {
            temp /= 10;
            count++;
        }

        final int[] digits = new int[count];
        int p = count - 1;
        while (p >= 0) {
            digits[p--] = n % 10;
            n /= 10;
        }

        return digits; // todo:
    }

    public static void main(String[] args) {
        Q738 q738 = new Q738();
        System.out.println(q738.monotoneIncreasingDigits(1234));
        System.out.println(q738.monotoneIncreasingDigits(100000000));
        System.out.println(q738.monotoneIncreasingDigits(111111110));
        System.out.println(q738.monotoneIncreasingDigits(332));
    }
}
