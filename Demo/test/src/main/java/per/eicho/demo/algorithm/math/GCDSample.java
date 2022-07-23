package per.eicho.demo.algorithm.math;

/**
 * <p>欧几里得算法: 求最大公约数</p>
 * 
 * <pre>
 *  辗转相除法，又称欧几里得算法(英语：Euclidean algorithm)，是求最大公约数的算法。辗转相除法首次出现于欧几里得的《几何原本》
 *  
 *  注：最大公约数(greatest common divisor (GCD))
 * </pre>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Euclidean_algorithm">Euclidean algorithm</a>
 */
public final class GCDSample {
    public static int gcd(int num1, int num2) {
        int temp;
        while (num2 != 0) {
            temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    /**
     * <p>求最小公倍数(Least Common Multiple)</p>
     * 
     * <pre>
     *  对于给定的两个<b>正整数</b>求其最小公倍数。
     * </pre>
     * 
     * @param num1 正整数
     * @param num2 正整数
     * @return 给定正整数的LCM可能超过int类型的范围，返回一个用long存储的正整数。
     */
    public static long lcm(int num1, int num2) {
        if (num1 <= 0 || num2 <= 0) {
            throw new IllegalArgumentException("给定参数不是正整数");
        }
        return ((long)num1 * (long)num2) / (long)gcd(num1, num2);
    }

    private GCDSample() {}
}
