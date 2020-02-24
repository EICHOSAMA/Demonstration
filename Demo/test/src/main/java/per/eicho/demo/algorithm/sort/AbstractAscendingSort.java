package per.eicho.demo.algorithm.sort;

import java.util.Objects;

public abstract class AbstractAscendingSort implements AscendingSort {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sort(int[] nums) {
		Objects.nonNull(nums);
		doSort(nums);
	}
	
    /**
     * 排序的实现部分，各子类自行实现。其实现要求与Sort接口定义一致。
     * @param nums 抽象父类保证传入的nums一定是non-null的。
     * @see {@link AscendingSort#sort(int[])}
     */
	protected abstract void doSort(int[] nums);
	
    /**
     * 交换给定数组下标为i,j的元素。当i == j 时什么都不做。
     * 
     * 调用方开发者需要保证
     * 1. nums不为null
     * 2. i,j ∈ [0, nums.length)
     */
    protected void swap(int[] nums, int i, int j) {
        assert nums != null;
        assert 0 <= i && i < nums.length;
        assert 0 <= j && j < nums.length;
        if (i == j) return;

        // 交换
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
	
}