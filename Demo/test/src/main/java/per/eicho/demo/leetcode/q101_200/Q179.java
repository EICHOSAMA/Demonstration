package per.eicho.demo.leetcode.q101_200;

import java.util.Arrays;

/**
 * <p>179. Largest Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-number/">179. Largest Number</a>
 */
public final class Q179 {

    public String largestNumber(int[] nums) {
        // 1 <= nums.length <= 100
        // 0 <= nums[i] <= 10^9
        final int n = nums.length;
        final String[] strNums = new String[n];
        boolean onlyZero = true;
        for (int i = 0; i < n; i++) {
            final int num = nums[i];
            if (num != 0) onlyZero = false;
            strNums[i] = String.valueOf(num);
        }

        if (onlyZero) return "0";

        Arrays.sort(strNums, ((n1, n2) -> {
            String o1 = n1 + n2;
            String o2 = n2 + n1;
            return o2.compareTo(o1);
        }));

        final StringBuilder sb = new StringBuilder();
        for (String strNum : strNums) {
            sb.append(strNum);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Q179 q179 = new Q179();
        System.out.println(q179.largestNumber(new int[]{432, 43243}));
    }
}
