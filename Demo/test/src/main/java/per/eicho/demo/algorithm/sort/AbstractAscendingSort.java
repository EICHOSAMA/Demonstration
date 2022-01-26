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
     * <p>排序</p>
     * <pre>
     *  排序的实现部分，各子类自行实现。
     *  
     *  要求:
     *    1. 对给定<b>非空</b>数组进行<b>升序排序</b>。
     *    2. 破坏性排序，即是会改变给定数组内的数据。
     * </pre>
     * 
     * @param nums 抽象父类保证传入的nums一定是non-null的。
     * @see {@link AscendingSort#sort(int[])}
     */
	protected abstract void doSort(int[] nums);
	
    /**
     * <p>交换数组元素</p>
     * 
     * <pre>
     * 给定数组 <b>nums</b> 和 作为交换对象的两个元素的下标 <b>i</b> 和 <b>j</b>。
     * 本方法将交换指定的两个元素。当i == j 时什么都不做。
     * </pre>
     * 
     * @param nums non-null, 指定的数组
     * @param i i ∈ [0, nums.length)
     * @param j j ∈ [0, nums.length)
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