package per.eicho.demo.leetcode.q001_100;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>60. Permutation Sequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/permutation-sequence/">60. Permutation Sequence</a>
 */
public final class Q60 {
    public String getPermutation(int n, int k) {
        // 1. 1 <= n <= 9
        // 2. 1 <= k <= n!        
        
        //assert n >= 1 && n <= 9: "Given n will be between 1 and 9 inclusive.";
        //assert k >= 1 && k <= factorial(n): "Given k will be between 1 and n! inclusive.";
        List<Integer> candidates = new LinkedList<>();
        for (int i = 1; i <= n; i++) candidates.add(i);

        // process
        StringBuilder sb = new StringBuilder(n);
        for (int count = candidates.size(), factorial = factorial(count) ; count != 0 ; count--) {
            // [1, f(count-1)]
            factorial /= count;
            int index = (k - 1) / factorial; // 0-based index.
            k = (k - 1) % factorial + 1; // update k.

            sb.append(candidates.remove(index));
        }

        return sb.toString();
    }


    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n ; i++) {
            result *= i;
        }
        return result;
    }
}
