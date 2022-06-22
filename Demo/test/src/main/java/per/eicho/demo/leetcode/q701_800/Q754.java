package per.eicho.demo.leetcode.q701_800;

/**
 * <p>754. Reach a Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reach-a-number/">
 *   754. Reach a Number</a>
 */
public final class Q754 {
    public int reachNumber(int target) {
        // 1. -10^9 <= target <= 10^9
        // 2. target != 0
        // 31622 < sqrt(10^9) < 31623
        // 44721 < sqrt(2 * 10^9) < 44722
        int i = 0;
        if (target < 0) target = -target;
        final int twoTimeOfTarget = 2 * target;
        for (; i <= 44721; i++) if (i * (i + 1) > twoTimeOfTarget) break;
        i--;
        final int num = i * (i + 1) / 2;
        int diff = target - num;
        if (diff == 0) return i;
        diff -= ++i;
        while (diff % 2 != 0) diff -= ++i;
        return i;
    }

    public static void main(String[] args) {
        Q754 q754 = new Q754();
        System.out.println(q754.reachNumber(10_0000_0000));
    }
}
