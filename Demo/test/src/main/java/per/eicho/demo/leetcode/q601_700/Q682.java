package per.eicho.demo.leetcode.q601_700;

import java.util.Stack;

/**
 * <p>682. Baseball Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/baseball-game/">682. Baseball Game</a>
 */
public final class Q682 {
    public int calPoints(String[] ops) {
        final Stack<Integer> nums = new Stack<>();
        
        for (int i = 0; i < ops.length; i++) {
            final String c = ops[i];

            if (isC(c)) {
                nums.pop();
            } else if (isD(c)) {
                Integer top = nums.peek();
                nums.add(top * 2);
            } else if (isSum(c)) {
                //nums.add(nums.pe)
                nums.add(nums.get(nums.size() - 1) + nums.get(nums.size() - 2));
            } else {
                // is numberic
                nums.add(Integer.parseInt(c));
            }
        }

        int result = 0;
        for (Integer num : nums) {
            result += num;
        }
        return result;
    }

    private boolean isD(final String c) {
        return "D".equals(c);
    }

    private boolean isC(final String c) {
        return "C".equals(c);
    }

    private boolean isSum(final String c) {
        return "+".equals(c);
    }
}
