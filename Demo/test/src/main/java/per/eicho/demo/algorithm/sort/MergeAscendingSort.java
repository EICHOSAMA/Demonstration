package per.eicho.demo.algorithm.sort;

/**
 * 归并排序
 * @author toranekojp
 */
public final class MergeAscendingSort extends AbstractAscendingSort {

	@Override
	protected void doSort(int[] nums) {
		assert nums != null;
		if (nums.length == 0) return;
		int[] temp = nums.clone();
        mergeSortRecursively(temp, nums, 0, nums.length - 1);
	}

	/**
	 * 对给定数组nums的指定范围[l, r]的元素进行排序，其排序结果同时反映在result和nums数组的[l, r]段。
	 * 
	 * @param nums 排序对象数组。
	 * @param result 存放排序结果的辅助数组。
	 * @param l 指定排序范围的左边界，inclusive
	 * @param r 指定排序范围的右边界，inclusive
	 */
    private void mergeSortRecursively(int[] nums, int[] result , int l, int r) {
    	assert l <= r;
        if (l == r) return; // 1个元素总是自然有序的。

        final int mid = (l + r + 1) / 2;

        // 分割 & 排序
        mergeSortRecursively(nums, result, l, mid - 1); // 此时可以断言 result 的 [l, mid-1]是有序的。
        mergeSortRecursively(nums, result, mid, r); //此时可以断言 result 的 [mid, r]是有序的。
        
        // 合并两个有序区间[l, mid-1] & [mid, r]到[l, r]并保证结束后该区间保存有序。
        final int leftBoundle = mid - 1;
        final int rightBoundle = r;

        // 一个指针记录整合区间的合并进度。针对result数组。
        int cursor = l;
        // 两个指针分别记录左右两个区间的合并进度。针对nums数组
        int cursorL = l;
        int cursorR = mid;

        // 左右开工，直到某一方元素耗尽。
        while (cursorL <= leftBoundle && cursorR <= rightBoundle)
            result[cursor++] = nums[cursorL] < nums[cursorR] ? nums[cursorL++]: nums[cursorR++];
        // 检查左区间是否有剩余未合并的元素，有就榨干。
        while (cursorL <= leftBoundle) result[cursor++] = nums[cursorL++];
        // 检查右区间是否有剩余未合并的元素，有就榨干。
        while (cursorR <= rightBoundle) result[cursor++] = nums[cursorR++];

        // 同步回nums
        for (int i = l; i <= r; i++) nums[i] = result[i];
    }
	
}
