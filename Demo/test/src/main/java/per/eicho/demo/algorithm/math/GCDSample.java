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

    private GCDSample() {}
}
