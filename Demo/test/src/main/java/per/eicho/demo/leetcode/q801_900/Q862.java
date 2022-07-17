package per.eicho.demo.leetcode.q801_900;

/**
 * <p>862. Shortest Subarray with Sum at Least K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/">
 *   862. Shortest Subarray with Sum at Least K</a>
 */
public final class Q862 {

    private static final class SegementTree {

        final int l;
        final int r;
        SegementTree left = null;
        SegementTree right = null;
        long maxVal = -1_000_000_000_000L;

        SegementTree(int l, int r) {
            this.l = l;
            this.r = r;
        }

        void set(int i, long val) {
            if (i < l || i > r) return;
            maxVal = Math.max(maxVal, val);

            if (l == r) return;
            genSubTreeIfAbsent();
            
            left.set(i, val);
            right.set(i, val);
        }

        long max(int l, int r) {
            if (r < this.l || l > this.r) return -1_000_000_000_000L;
            if (l <= this.l && this.r <= r) return maxVal;
            return Math.max(left.max(l, r), right.max(l, r));
        }

        private void genSubTreeIfAbsent() {
            if (left != null) return;

            final int mid = (l + r + 1) / 2;
            left = new SegementTree(l, mid - 1);
            right = new SegementTree(mid, r);
        }
    }

    public int shortestSubarray(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^5
        // 2. -10^5 <= nums[i] <= 10^5
        // 3. 1 <= k <= 10^9
        final int n = nums.length;
        final long[] sums = genSums(nums);
        final SegementTree root = genSegementTree(sums);
        
        int result = Integer.MAX_VALUE;

        for (int i = -1, bound = n - 1; i < bound; i++) {
            final long offset = i == -1 ? 0 : sums[i];

            final int l = i + 1;
            int r; 
            if (result == Integer.MAX_VALUE) {
                // default val
                r = bound;
            } else {
                // 
                r = Math.min(bound, i + result);
            }

            while (r >= l && root.max(l, r) - offset >= k) {
                result = r - l + 1;
                r--;
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private SegementTree genSegementTree(long[] sums) {
        final int n = sums.length;
        final SegementTree root = new SegementTree(0, n - 1);
        for (int i = 0; i < n; i++) {
            final long sum = sums[i];
            root.set(i, sum);
        }
        return root;
    }

    private long[] genSums(int[] nums) {
        final int n = nums.length;
        final long[] sums = new long[n];
        long sum = 0;
        int p = 0;
        for (long num : nums) sums[p++] = sum += num;
        return sums;
    }

    public static void main(String[] args) {
        Q862 q862 = new Q862();
        int[] input = new int[100_000];
        for (int i = 0; i < input.length; i++) {
            input[i] = -100_000;
        }
        System.out.println(q862.shortestSubarray(input, 1_000_000_000));
    }
}
