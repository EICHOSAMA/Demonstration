package per.eicho.demo.algorithm.sort;

/**
 * 选择排序的实现类。
 * @author toranekojp
 */
public final class SelectionAscendingSort extends AbstractAscendingSort {

	@Override
	protected void doSort(int[] nums) {
        assert nums != null;

        final int boundle = nums.length;
        for (int i = 0; i < boundle; i++) {
            int minNum = nums[i]; // 记录本轮最小数字
            int minIndex = i; // 记录本轮最小数字的下标。

            // 枚举剩余数组，找出最小数字。
            for (int j = i + 1; j < boundle; j++) {
                if (nums[j] < minNum) {
                    // 找到更小的替换就当前记录的信息。
                    minNum = nums[j];
                    minIndex = j;
                }
            }

            // 通过交换把最小的数字挪到本轮搁置位i。
            swap(nums, i, minIndex);
            // 继续下一轮直到所有元素都被处理。
        }
	}

}
