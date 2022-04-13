package per.eicho.demo.leetcode.q1401_1500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1441. Build an Array With Stack Operations 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/build-an-array-with-stack-operations/">
 *   1441. Build an Array With Stack Operations</a>
 */
public final class Q1441 {
    public List<String> buildArray(int[] target, int n) {
        // 1. 1 <= target.length <= 100
        // 2. 1 <= n <= 100
        // 3. 1 <= target[i] <= n
        // target is strictly increasing.
        final int size = target.length;
        final List<String> result = new ArrayList<>(size + 2 * (n - size));
        
        int p = 1;
        for (int i = 0; i < size; i++) {
            final int num = target[i];

            while (p < num) {
                result.add("Push");
                result.add("Pop");
                p++;
            }

            result.add("Push");
            p++;
        }

        return result;
    }
}
