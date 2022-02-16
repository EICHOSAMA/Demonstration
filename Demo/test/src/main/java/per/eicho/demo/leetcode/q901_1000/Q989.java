package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>989. Add to Array-Form of Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-to-array-form-of-integer/">989. Add to Array-Form of Integer</a>
 */
public final class Q989 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        final LinkedList<Integer> result = new LinkedList<>();
        int p = num.length - 1;
        while (k != 0 || p >= 0) {
            if (p >= 0) k += num[p--];

            result.addFirst(k % 10);
            k /= 10;
        }
        return result;
    }
}
