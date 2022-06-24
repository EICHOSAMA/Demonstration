package per.eicho.demo.leetcode.q1301_1400;

import java.util.PriorityQueue;

/**
 * <p>1354. Construct Target Array With Multiple Sums 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/construct-target-array-with-multiple-sums/">
 *   1354. Construct Target Array With Multiple Sums</a>
 */
public final class Q1354 {
    public boolean isPossible(int[] target) {
        // 1. n == target.length
        // 2. 1 <= n <= 5 * 10^4
        // 3. 1 <= target[i] <= 10^9
        final int n = target.length;
        if (n == 1) return target[0] == 1;
        final PriorityQueue<Integer> nums = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1));
        long sum = 0L;
        for (int num : target) {
            nums.offer(num);
            sum += num;
        }

        int maxNum = 0;
        while ((maxNum = nums.poll()) != 1) {
            final long newNumL = 2 * maxNum - sum;
            if (newNumL <= 0) return false;
            final int others = (int)(sum - maxNum);
            final int diff = maxNum - nums.peek();
            final int time = (diff / others) + (diff % others != 0 ? 1 : 0);
            final int newNum = maxNum - others * time;
            if (newNum <= 0) return false;
            sum = sum - maxNum + newNum;
            nums.offer(newNum);
        }
        return true;
    }

    public static void main(String[] args) {
        Q1354 q1354 = new Q1354();
        System.out.println(q1354.isPossible(new int[]{1, 1_000_000_000}));
        System.out.println(q1354.isPossible(new int[]{1}));
        System.out.println(q1354.isPossible(new int[]{100}));

        System.out.println(q1354.isPossible(new int[]{9,3,5}));
        System.out.println(q1354.isPossible(new int[]{1,1,1,2}));
        System.out.println(q1354.isPossible(new int[]{8,5}));
        System.out.println(q1354.isPossible(new int[]{1,6,8,10,19,30}));
    }
}
