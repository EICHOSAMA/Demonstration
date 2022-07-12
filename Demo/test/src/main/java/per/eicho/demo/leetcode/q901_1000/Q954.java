package per.eicho.demo.leetcode.q901_1000;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>954. Array of Doubled Pairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/array-of-doubled-pairs/">
 *   954. Array of Doubled Pairs</a>
 */
public final class Q954 {
    public boolean canReorderDoubled(int[] arr) {
        // 1. 2 <= arr.length <= 3 * 10^4
        // 2. arr.length is even.
        // 3. -10^5 <= arr[i] <= 10^5
        final int n = arr.length;
        final Map<Integer, int[]> oddNums = new HashMap<>();
        Map<Integer, int[]> evenNums = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final Integer num = arr[i];
            if (num % 2 == 0) {
                if (!evenNums.containsKey(num)) evenNums.put(num, new int[]{0});
                evenNums.get(num)[0]++;
            } else {
                if (!oddNums.containsKey(num)) oddNums.put(num, new int[]{0});
                oddNums.get(num)[0]++;
            }
        }

        for (int oddNum : oddNums.keySet()) {
            final int count = oddNums.get(oddNum)[0];
            final int oddNumX2 = oddNum * 2;
            if (!evenNums.containsKey(oddNumX2)) return false;
            if ((evenNums.get(oddNumX2)[0] -= count) < 0) return false;
        }

        evenNums = new TreeMap<>(evenNums);

        for (int evenNum : evenNums.keySet()) {
            final int count = evenNums.get(evenNum)[0];
            if (count == 0) continue;
            
            final int evenNumX2 = evenNum >= 0 ? evenNum * 2 : evenNum / 2;
            if (!evenNums.containsKey(evenNumX2)) return false;
            if ((evenNums.get(evenNumX2)[0] -= count) < 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Q954 q954 = new Q954();
        System.out.println(q954.canReorderDoubled(new int[]{10, 20, 40, 80}));
    }
}
