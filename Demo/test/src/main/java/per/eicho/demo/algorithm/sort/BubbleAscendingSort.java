package per.eicho.demo.algorithm.sort;

/**
 * 冒泡排序，O(n²),稳定
 * @author toranekojp
 */
public final class BubbleAscendingSort extends AbstractAscendingSort {

	@Override
	protected void doSort(int[] nums) {
        assert nums != null;

        // 用于表示冒泡排序尾部边界不断缩小的过程。
        int boundle = nums.length;
        do {
            for (int i = 1; i < boundle; i++) {
                if (nums[i - 1] > nums[i]) { // 冒泡
                    swap(nums, i - 1, i);
                }
            }
        } while (--boundle != 0); // 缩小边界
	}

}
