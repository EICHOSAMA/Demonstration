package per.eicho.demo.leetcode.q001_100;

/**
 * <p>81. Search in Rotated Sorted Array II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array-ii/">81. Search in Rotated Sorted Array II</a>
 */
final public class Q81 {

    private static class ProcessContext {
        final int[] nums;
        final int target;

        ProcessContext(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
        }
        
        private int get(int index) {
            return nums[index];
        }

    }

    public boolean search(int[] nums, int target) {
        final ProcessContext context = new ProcessContext(nums, target);
        return binarySearch(context, 0, nums.length - 1);
    }

    private boolean binarySearch(ProcessContext context, int l, int r) {
        // search range [l, r]
        if (r < l) {
            return false;
        } else if (r == l) {
            return context.get(l) == context.target;
        }
        // assert r > l;
        
        final int nL = context.get(l);
        final int nR = context.get(r);

        // [Performance Optimization]
        if (nL < nR && !inside(context.target, nL, nR)) {
            return false;
        }

        final int mid = (l + r - 1) / 2;
        
        return binarySearch(context, l, mid) || binarySearch(context, mid + 1, r);
    }

    private boolean inside(int target, int nl, int nr) {
        // assert nl < nr;
        return nl <= target && target <= nr;
    }
}
