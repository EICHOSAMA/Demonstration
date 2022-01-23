package per.eicho.demo.leetcode.q1201_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1291. Sequential Digits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sequential-digits/">
 *   1291. Sequential Digits</a>
 */
public final class Q1291 {

    private final static List<Integer> candidates;

    static {
        candidates = new ArrayList<>();

        // 10 <= low <= high <= 10^9
        // 12, 23, 34 ⇒ 89, 123, 234, 345 ⇒ 789, 1234 ⇒ 123456789, 

        int num = 1;
        int step = 1;

        for (int nextDigits = 2; nextDigits <= 9; nextDigits++) {
            num = num * 10 + nextDigits;
            step = step * 10 + 1;

            int temp = num;

            while (temp % 10 != 0) {
                candidates.add(temp);
                temp += step;
            }
        }

        // add dummy candidate for null safe.
        candidates.add(Integer.MAX_VALUE);
    }

    public List<Integer> sequentialDigits(int low, int high) {
        if (high < candidates.get(0) || low > candidates.get(candidates.size() - 2)) return new ArrayList<>(1);
        
        int l = 0;
        while (low > candidates.get(l)) {
            l++;
        }
        // assert low <= candidates.get(l);
        int r = l;
        while (high >= candidates.get(r)) {
            r++;
        }
        // assert high < candidates.get(r);
        return candidates.subList(l, r);
    }

    public static void main(String[] args) {
        Q1291 q1291 = new Q1291();
        
        List<Integer> result = q1291.sequentialDigits(10, 223456789);
        for (Integer num : result) {
            System.out.println(num);
        }
    }
}
