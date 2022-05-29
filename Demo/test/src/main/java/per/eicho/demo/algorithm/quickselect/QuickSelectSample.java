package per.eicho.demo.algorithm.quickselect;

/**
 * <p>快速选择算法</p>
 * 
 * <pre>
 *  快速选择算法(英:Quick Select)是一种从无序列表找到第k小元素的选择算法，其核心原理(分区，partition)与快速排序同源。
 *  其发明者都是Tony Hoare，所以快速选择算法也被称为Hoare's selection algorithm。
 *  
 *  与快速排序需要完全处理分区后左右两边的处理不同，快速选择算法只需要处理包含k th元素的那一边分区。
 *  其本质就是一个简化版的快速排序。
 *  有O(n) - O(n log n)的平均复杂度和O(n^2)的最坏时间复杂度。
 * </pre>
 */
public final class QuickSelectSample {

    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int partition(int[] nums, int l, int r) {
        if (l == r) return l;
        final int pivotIndex = r;
        final int pivot = nums[l];

        swap(nums, l , r--);
        while (l <= r) {
            if (nums[l] < pivot) {
                l++;
            } else {
                swap(nums, l, r--);
            }
        }
        swap(nums, l, pivotIndex);
        return l;
    }
    
    public int select(int[] unsortedNums, int k) {
        // 1. n == unsortedNums
        // 2. 1 <= k <= n;

        int l = 0, r = unsortedNums.length - 1;
        int pivotIndex = 0;
        k--;
        while (l != r) {
            pivotIndex = partition(unsortedNums, l, r);
            if (pivotIndex == k) break;
            if (pivotIndex < k) {
                l = pivotIndex + 1;
            } else { // pivotIndex > k
                r = pivotIndex - 1;
            }
        }
        return unsortedNums[k];
    }
}
