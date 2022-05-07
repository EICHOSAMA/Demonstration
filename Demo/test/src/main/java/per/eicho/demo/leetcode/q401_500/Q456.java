package per.eicho.demo.leetcode.q401_500;

/**
 * <p>456. 132 Pattern 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/132-pattern/">
 *   456. 132 Pattern</a>
 */
public final class Q456 {

    private final static class SegementTree {
        private final static class TreeNode {
            final int l;
            final int r;
            boolean marked;
    
            TreeNode left;
            TreeNode right;
    
            private TreeNode(int l, int r) {
                // assert l <= r: "由construceSegmentTree方法保证";
                this.l = l;
                this.r = r;
                marked = false;
            }
                
            private boolean isMakred(int num) {
                if (num < l || num > r) return false;
                if (marked == true) return true;
                if (left != null && left.isMakred(num)) return true;
                if (right != null && right.isMakred(num)) return true;
                return false;
            }

            private void mark(int l, int r) {
                if (r < this.l || l > this.r) return;
                if (marked == true) return; // already marked.
                
                if (l <= this.l && this.r <= r) {
                    marked = true;
                    left = null;
                    right = null;
                    return;
                }

                if (left == null) {
                    final int mid = (this.l + this.r + 1) / 2;
                    left = new TreeNode(this.l, mid - 1);
                    right = new TreeNode(mid, this.r);
                }

                left.mark(l, r);
                right.mark(l, r);
            }
        }
    
        final TreeNode root;
    
        SegementTree(int l, int r) {
            root = new TreeNode(l, r);
        }

        public void mark(int l, int r) {
            if (l > r) return;
            root.mark(l, r);
        }

        public boolean isMarked(int num) {
            return root.isMakred(num);
        }
    }

    public boolean find132pattern(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 2 * 10^5
        // 3. -10^9 <= nums[i] <= 10^9
        
        final int n = nums.length;
        if (n <= 2) return false;
        int min = nums[0];

        final SegementTree sTree = new SegementTree(-1_000_000_000, 1_000_000_000); 
        for (int i = 1; i < n; i++) {
            final int num = nums[i];
            if (sTree.isMarked(num)) return true;

            if (num <= min) {
                min = num; // update
            } else {
                // num > min
                // mark range (min, num)
                sTree.mark(min + 1, num - 1);
            }
        }
        return false;
    }
}
