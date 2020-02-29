package per.eicho.demo.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * 并行归并排序
 * @author toranekojp
 */
public final class ParallelMergeAscendingSort extends AbstractAscendingSort {

	@Override
	protected void doSort(int[] nums) {
		assert nums != null;
		if (nums.length == 0) return;
		
		// DELEGATE: 委托排序任务到子组件SortTask
		SortTask sortTask = new SortTask(nums, 0, nums.length);
		sortTask.compute();
	}

	private class SortTask extends RecursiveAction {
		
		/**
		 * 不进行并行分割排序的阈值。表示小于{@value}的时候不会并行执行。
		 */
		static final int THRESHOLD = 1000;
		
		private static final long serialVersionUID = 2361239805661299619L;
		
		/**
		 * 排序对象数组，non-null
		 */
		final int[] nums;
		
		/**
		 * 排序对象区间左边界的下标，inclusive。
		 */
		final int l;
		
		/**
		 * 排序对象区间有边界的下标，exclusive。
		 */
		final int r;
		
		/**
		 * 构造一个排序任务。需要指定排序对象数组，并且指定排序对象区间[l, r)。
		 * 排序对象数组不能为空，并且区间必须合法（0 <= l < r <= nums.length）。
		 * 
		 * @param nums 排序对象数组，non-null
		 * @param l 排序对象区间的左边界下标，inclusive。
		 * @param r 排序对象区间的右边界下标，exclusive
		 */
		SortTask(int[] nums, int l, int r) {
			assert nums != null;
			assert 0 <= l && l < r && r <= nums.length;
			// System.out.printf("Sort[%d - %d)\n", l , r);
			
			this.nums = nums;
			this.l = l;
			this.r = r;
		}

		@Override
		protected void compute() {
			final int elementCount = r - l;
			
			if (elementCount < THRESHOLD) {
				sortDirectly();
			} else {
				final int mid = (l + r + 1) / 2;
				invokeAll(new SortTask(nums, l, mid),
						  new SortTask(nums, mid, r));
				merge(l, mid, r);	
			}
		}
		
		/**
		 * 合并已经排序好的左右区间。[l, mid) 与 [mid, r)。<br/>
		 * 该方法需要额外的½区间大小的辅助数组来帮助合并。
		 * 
		 * @param l 左区间起始下标。
		 * @param mid 右区间起始下标。（同时也是左区间的结尾下标。
		 * @param r 右区间结尾下标。
		 */
		private void merge(int l, int mid, int r) {
			// 辅助数组用于，备份左区间
			final int[] leftPartCopy = Arrays.copyOfRange(nums, l, mid);
			
			for (int cursor = l, cursorL = 0, cursorR = mid; 
					cursorL < leftPartCopy.length ;) {
				nums[cursor++] = (cursorR == r || leftPartCopy[cursorL] < nums[cursorR]) ?
						leftPartCopy[cursorL++] : // 右区间用光 或 左区间的数较小
						nums[cursorR++]; 
			}
		}
		
		/**
		 * 这里笔者偷懒简化了，使用{@link MergeAscendingSort}的mergeSortRecursively方法是一样的。
		 */
		private void sortDirectly() {
			Arrays.sort(nums, l, r);
		}
	}
}
