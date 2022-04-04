package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>315. Count of Smaller Numbers After Self 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-of-smaller-numbers-after-self/">
 *   315. Count of Smaller Numbers After Self</a>
 */
public final class Q315 {

    private static final class SegmentTreeNode {
        final int l;
        final int r;

        int count;
        
        SegmentTreeNode left;
        SegmentTreeNode right;

        SegmentTreeNode(int l, int r) {
            this.l = l;
            this.r = r;
            count = 0;
        }

        void add(int num) {
            if (num < l || num > r) return;

            count++;
            if (l == r) return;

            if (left == null) {
                final int mid = (l + r + 1) / 2;
                left = new SegmentTreeNode(l, mid - 1);
                right = new SegmentTreeNode(mid, r);
            }
            left.add(num);
            right.add(num);
        }

        void delete(int num) {
            if (num < l || num > r) return;
            count--;
            if (l == r) return;
            left.delete(num);
            right.delete(num);
        }

        int count(int l, int r) {
            if (r < this.l || this.r < l) return 0;
            if (l <= this.l && this.r <= r) return count;
            return left.count(l, r) + right.count(l, r);
        }
    }

    SegmentTreeNode root;

    public List<Integer> countSmaller(int[] nums) {
        // 1 <= nums.length <= 10^5
        // -10^4 <= nums[i] <= 10^4
        final int n = nums.length;

        root = new SegmentTreeNode(-10_001, 10_000);
        for (int num : nums) root.add(num);

        final List<Integer> result = new ArrayList<>(n);
        for (int num : nums) {
            root.delete(num);
            result.add(root.count(-10_001, num - 1));
        }

        return result;
    }

    public static void main(String[] args) {
        Q315 q315 = new Q315();
        for (int num : q315.countSmaller(new int[]{-1})) {
            System.out.println(num);
        }
    }
}
