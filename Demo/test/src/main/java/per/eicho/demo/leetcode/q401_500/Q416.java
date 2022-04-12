package per.eicho.demo.leetcode.q401_500;

/**
 * <p>416. Partition Equal Subset Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partition-equal-subset-sum/">416. Partition Equal Subset Sum</a>
 */
public final class Q416 {
    public boolean canPartition(int[] nums) {
        // 1. 1 <= nums.length <= 200
        // 2. 1 <= nums[i] <= 100
        int sum = 0;
        for (int num : nums) sum += num;
        
        if (sum % 2 != 0) return false;

        final int half = sum / 2;
        final boolean[] f = new boolean[half + 1];
        f[0] = true;
        int max = 0;

        for (int num : nums) {
            if (num > half) return false;

            for (int i = max; i >= 0; i--) {
                if (f[i] == false) continue;

                final int possibleSum = i + num;
                if (possibleSum <= half) {
                    f[possibleSum] = true;
                    max = Math.max(possibleSum, max);
                }
            }
        }
        return f[half];
    }

    public static void main(String[] args) {
        Q416 q416 = new Q416();
        System.out.println(q416.canPartition(new int[]{2,2,3,5}));
    }
}
