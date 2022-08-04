package per.eicho.demo.leetcode.q801_900;

/**
 * <p>858. Mirror Reflection 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/mirror-reflection/">
 *   858. Mirror Reflection</a>
 */
public final class Q858 {
    public int mirrorReflection(int p, int q) {
        // 1. 1 <= q <= p <= 1000
        final int lcm = lcm(p, q);
        final int time = lcm / q;
        if (time % 2 == 0) return 2;
        return (lcm / p) % 2 == 1 ? 1 : 0;
    }

    private int gcd(int a, int b) {
        int temp = 0;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
