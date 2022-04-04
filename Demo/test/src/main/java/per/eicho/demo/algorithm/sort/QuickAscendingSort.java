package per.eicho.demo.algorithm.sort;

/**
 * <p>快速排序 - 升序</p>
 * 
 * <pre>
 *   核心思想：
 *    1.在待排序区间里任取一个元素作为基准 (通常选第一个元素，称为基准元素pivot)
 *    2.对待排序区间进行分区，分区规则如下
 *       1. 比基准元素大的元素移动到基准元素的右侧，
 *       2. 比基准元素小的移动到作左侧，从而一趟排序过程，就可以锁定基准元素的最终位置
 *    3.对左右两个分块重复以上步骤直到所有元素都是有序的（递归过程）
 * </pre>
 * 
 * @author toranekojp
 */
public final class QuickAscendingSort extends AbstractAscendingSort {

    @Override
    protected void doSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            final int p = partition(nums, low, high);
            quickSort(nums, low, p - 1);
            quickSort(nums, p + 1, high);
        }
    }

    /**
     * <p>分区处理</p>
     * 
     * <pre>
     *  对给定数组的闭区间[low, high]做分区处理。
     *  并返回分区结果的边界idx。
     *  方法保证
     *   1. 闭区间 [low, idx - 1] 的所有元素小于基准元素。
     *   2. 闭区间 [idx + 1, high] 的所有元素大于等于基准元素。
     * </pre>
     */
    private int partition(int[] nums, int low, int high) {
        final int pivot = nums[low]; // 选取基准元素

        int l = low, r = high;
        swap(nums, l, r--); // 暂存pivot至尾部
        while (l <= r) {
            if (nums[l] < pivot) {
                l++;
            } else {
                swap(nums, l, r--);
            }
        }
        swap(nums, l, high);
        return l;
    }
}
