package per.eicho.demo.leetcode.q801_900;

/**
 * <p>868. Binary Gap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-gap/">868. Binary Gap</a>
 */
public final class Q868 {
    public int binaryGap(int n) {
        // 1. 1 <= n <= 10^9
        int l = -1;
        int r = -1;

        int p = 0;
        int result = 0;
        while (n != 0) {
            final int remain = n % 2;
            n >>= 1;

            if (remain == 1) {
                l = r;
                r = p;
                if (l != -1) result = Math.max(result, r - l); 
            }

            System.out.println(remain);

            p++;
        }
        return result;
    }

    public static void main(String[] args) {
        Q868 q868 = new Q868();
        System.out.println(q868.binaryGap(22));
    }
}
