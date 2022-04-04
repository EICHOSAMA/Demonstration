package per.eicho.demo.algorithm.sort;

/**
 * 
 * @author toranekojp
 */
public final class DualPivotQuickSort extends AbstractAscendingSort {

    @Override
    protected void doSort(int[] nums) {
        dualPivotQuickSort(nums, 0, nums.length - 1);
    }

    private void dualPivotQuickSort(int[] nums, int low, int high) {
        if (low < high) {
            final int[] p = partition(nums, low, high);
            dualPivotQuickSort(nums, low, p[0] - 1);
            dualPivotQuickSort(nums, p[0] + 1, p[1] - 1);
            dualPivotQuickSort(nums, p[1] + 1, high);
        }
    }

    /**
     * <p>分区处理 - 双基准值</p>
     * 
     * <pre>
     *  对给定数组的闭区间[low, high]做分区处理。
     *  并返回分区结果的边界{leftIdx, rightIdx}。
     *  方法保证
     *   1. 闭区间 [low          , leftIdx - 1 ] 的所有元素小于左基准元素。 (val < lp)
     *   2. 闭区间 [leftIdx + 1  , rightIdx - 1] 的所有元素大于等于左基准元素，小于右基准元素。(lp <= val < rp)
     *   3. 闭区间 [rightIdx + 1 , high        ] 的所有元素大于等于基准元素。(rp <= val)
     * </pre>
     */
    private int[] partition(int[] nums, int low, int high) {
        if (nums[low] > nums[high]) swap(nums, low, high);
        
        final int lp = nums[low];  // left pivot
        final int rp = nums[high]; // right pivot

        assert lp <= rp;

        int j = low + 1;
        int k = high - 1, i = low + 1;
            
        while (i <= k) {
            if (nums[i] < lp) {
                swap(nums, i, j++);
            } else if (nums[i] >= rp) {
                // If elements are greater than or equal
                // to the right pivot
                while (nums[k] > rp && i < k) k--;
                    
                swap(nums, i, k--);
                
                if (nums[i] < lp) swap(nums, i, j++);
            }
            i++;
        }
        
        // Bring pivots to their appropriate positions.
        swap(nums, low, --j);
        swap(nums, high, ++k);
        return new int[] { j, k };
    }
    
}
