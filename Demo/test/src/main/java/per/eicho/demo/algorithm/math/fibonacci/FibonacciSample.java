package per.eicho.demo.algorithm.math.fibonacci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>斐波拉契数列Sample类</p>
 * 
 * <pre>
 *   斐波拉契数列是一个数列满足
 *   F[0] = 0, F[1] = 1, n > 1时 F[n] = F[n - 1] + F[n - 2]。
 *   
 *   int  范围内能容纳的最大值为F[46] = 18_3631_1903
 *   long 范围内能容纳的最大值为F[92] = 754_0113_8047_4634_6429L
 * 
 *   斐波拉契数列在小值（92）时相对简单，超过long的上限则
 *   需要涉及到高精度或浮点数（低精度）运算了。
 * </pre>
 */
public final class FibonacciSample {

    public static final List<Long> FIBONACC_LIST;

    static {
        final List<Long> temp = new ArrayList<>(93);
        temp.add(0L); // f[0] = 0;
        temp.add(1L); // f[1] = 1;
        for (int i = 1; i < 92; i++) temp.add(temp.get(i - 1) + temp.get(i));
        FIBONACC_LIST = Collections.unmodifiableList(temp);
    }

    public static int fib(int n) {
        return FIBONACC_LIST.get(n).intValue();        
    }

    public static void main(String[] args) {
        for (int i = 0; i < FIBONACC_LIST.size(); i++) {
            System.out.println(i + ":" + FIBONACC_LIST.get(i));
        }
    }
}
