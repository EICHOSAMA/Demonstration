package per.eicho.demo.leetcode.q301_400;

/**
 * <p>372. Super Pow 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/super-pow/">
 *   372. Super Pow</a>
 */
public final class Q372 {

    private static final int MOD = 1337;
    
    public int superPow(int a, int[] b) {
        // 1. 1 <= a <= 2^31 - 1
        // 2. 1 <= b.length <= 2000
        // 3. 0 <= b[i] <= 9
        // 4. b does not contain leading zeros.
        if (b.length == 1 && b[0] == 0) return a % MOD;
        return _superPow(a % MOD, b);
    }

    private int _superPow(int a, int[] b) {
        if (b.length == 1 && b[0] == 1) return a;

        final int lastIdx = b.length - 1;
        final int remain = b[lastIdx] % 2;
        b[lastIdx] -= remain;
        b = divideBy2(b);

        final int half = _superPow(a, b);
        if (remain == 0) return (half * half) % MOD;
        return ((half * half) % MOD) * a % MOD ;
    }

    private int[] divideBy2(int[] b) {
        final int lenOfNewB = b[0] > 1 ? b.length : b.length - 1; 
        final int[] newB = new int[lenOfNewB];

        int p = 0;
        int remain = b[0] > 1 ? 0 : b[0];
        int i = b[0] > 1 ? 0 : 1;
        for (; i < b.length; i++) {
            final int digit = b[i];
            remain = remain * 10 + digit;
            newB[p++] = remain / 2;
            remain %= 2;
        }
        return newB;
    }

    public static void main(String[] args) {
        Q372 q372 = new Q372();
        System.out.println(q372.superPow(2, new int[]{1, 1, 1, 0}));
    }
}