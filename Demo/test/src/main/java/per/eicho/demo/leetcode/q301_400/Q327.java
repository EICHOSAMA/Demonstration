package per.eicho.demo.leetcode.q301_400;

/**
 * <p>327. Count of Range Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-of-range-sum/">
 *   327. Count of Range Sum</a>
 */
public final class Q327 {

    private static final class SegmentTree {
        long left, right;
        int count;
        SegmentTree l = null, r = null;
    
        public SegmentTree(long left, long right) {
            this.left = left;
            this.right = right;
            count = 0;
        }

        private int count(long lBound, long rBound) {
            if (rBound < left || right < lBound) return 0;
            if (lBound <= left && right <= rBound) return count;
            int cnt = 0;
            if (l != null) cnt += l.count(lBound, rBound);
            if (r != null) cnt += r.count(lBound, rBound);
            return cnt;
        }

        private void insert(long val) {
            count++;
            if (left == right) return;
            final long mid = left + (right - left) / 2L;

            if (val <= mid) {
                if (l == null) l = new SegmentTree(left, mid);
                l.insert(val);
            } else {
                if (r == null) r = new SegmentTree(mid + 1, right);
                r.insert(val);
            }
        }
    }    

    public int countRangeSum(int[] nums, int lower, int upper) {
        // 1. 1 <= nums.length <= 10^5
        // 2. -2^31 <= nums[i] <= 2^31 - 1
        // 3. -10^5 <= lower <= upper <= 10^5
        // 4. The answer is guaranteed to fit in a 32-bit integer.
        
        final int n = nums.length;
        // 1. build presum array.
        final long[] sums = buildPresumArray(nums, n);
        
        long lBound = Long.MAX_VALUE, rBound = Long.MIN_VALUE;
        for (long sum : sums) {
            lBound = Math.min(lBound, Math.min(sum - upper, sum));
            rBound = Math.max(rBound, Math.max(sum - lower, sum));
        }

        final SegmentTree root = new SegmentTree(lBound, rBound);
        
        int result = 0;
        for (long sum : sums) {
            result += root.count(sum - upper, sum - lower);
            root.insert(sum);
        }
        return result;
    }

    private long[] buildPresumArray(int[] nums, final int n) {
        long sum = 0;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            sums[i + 1] = sum;
        }
        return sums;
    }

    public static void main(String[] args) {
        Q327 q327 = new Q327();
        System.out.println(q327.countRangeSum(new int[]{-2,5,-1}, -2, 2));
    }
}
