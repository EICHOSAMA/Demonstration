package per.eicho.demo.leetcode.q601_700;

/**
 * <p>698. Partition to K Equal Sum Subsets 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partition-to-k-equal-sum-subsets/">
 *   698. Partition to K Equal Sum Subsets</a>
 */
public final class Q698 {

    int[] nums;
    int n; // 1 <= k <= n <= 16
    int statusCount;
    int[][] f;
    int target;
    int k;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 1. 1 <= k <= nums.length <= 16
        // 2. 1 <= nums[i] <= 10^4
        // 3. The frequency of each element is in the range [1, 4].        
        final int sum = sum(nums);
        if (sum % k != 0) return false;
        // sum % k == 0
        target = sum / k;
        this.k = k;
        this.nums = nums;
        n = nums.length;
        statusCount = power(2, n);
        f = new int[k + 1][statusCount]; // [0, statusCount - 1 (0b11111...11))]
        return search(0, 0, 0) == 1;
    }
    
    private int search(int status, int round, int sum) {
        if (status == statusCount - 1 && round == k && sum == 0) return 1;
        if (f[round][status] != 0) return f[round][status];

        int ith = 1;
        for (int i = 0; i < n; i++, ith <<= 1) {
            if ((status & ith) != 0) continue;

            final int num = nums[i];
            if (sum + num > target) continue;

            final int result;
            if (sum + num == target) {
                result = search(status | ith, round + 1, 0);
            } else {
                result = search(status | ith, round, sum + num);
            }

            if (result == 1) return f[round][status] = 1; 
        }

        return f[round][status] = -1;
    }

    private int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        return sum;
    }

    private int power(int a, int b) {
        if (b == 0) return 1;
        final int half = power(a, b / 2);
        return half * half * (b % 2 == 1 ? a : 1);
    }

    public static void main(String[] args) {
        Q698 q698 = new Q698();
        System.out.println(q698.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4)); // true
        System.out.println(q698.canPartitionKSubsets(new int[]{1,2,3,4}, 3)); // false
    }
}
