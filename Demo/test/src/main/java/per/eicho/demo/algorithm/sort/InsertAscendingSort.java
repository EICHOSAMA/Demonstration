package per.eicho.demo.algorithm.sort;

/**
 * 插入排序
 * @author toranekojp
 *
 */
public final class InsertAscendingSort extends AbstractAscendingSort {

	@Override
	protected void doSort(int[] nums) {
        assert nums != null;

        final int count = nums.length;
        int sortedCount = 1; // 第一轮第一个永远是有序的。
        while (sortedCount < count) {
            // Assert [0, sortedCount)的区间 已经排序完毕
            // 二分查找insertPosition。
            final int targetIndex = sortedCount; // 需要做插入处理的元素的下标。
            final int targetNum = nums[targetIndex];
            final int sortedRightBoundle = sortedCount - 1; // 已排序部分的有边界index
            final int insertPosition = binarySearch(nums, 0, sortedRightBoundle, targetNum);

            // 移动与插入
            if (insertPosition <= sortedRightBoundle) {
            	moveBackwardFor1Step(nums, insertPosition, sortedRightBoundle);
            	nums[insertPosition] = targetNum;
            } else {
            	assert insertPosition == targetIndex: "此时不需要任何移动和插入。";
            	assert nums[insertPosition] >= nums[sortedRightBoundle]: "目标元素和已排序部分已经是有序的case。";
            }

            // 本轮结束，已排序计数+1。进入下一轮
            sortedCount++;
        }		
	}


    /**
     * 在[l,r]的有序升序区间内，递归地寻找能插入给定targetNum的位置pos。
     * @return pos ∈ [l, r + 1]，满足
     *  当pos = l          时 targetNum <= nums[l]
     *  当pos ∈ [l + 1, r] 时 nums[pos] >= targetNum
     *  当pos = r + 1      时 nums[r] < targetNum.
     */
    private int binarySearch(int[] nums, int l, int r, int targetNum) {
        if (l == r) {
            if (nums[l] < targetNum) return r + 1;
            return r;
        }
        final int mid = (l + r + 1) / 2;

        if (nums[mid] > targetNum)
            return binarySearch(nums, l, mid  - 1, targetNum);
        // nums[mid] <= targetNum
        return binarySearch(nums, mid, r, targetNum);
    }

    /**
     * 把下标在range [l,r]的数组元素向后挪一位。
     *
     * 调用者需要保证
     * 0 <= l <= r < nums.length - 1
     */
    private void moveBackwardFor1Step(int[] nums, int l, int r) {
        assert nums != null;
        assert 0 <= l && l <= r && r < nums.length - 1;

        int cursor = r + 1;
        while (cursor > l) nums[cursor] = nums[--cursor]; // 往后挪一位
    }
}
