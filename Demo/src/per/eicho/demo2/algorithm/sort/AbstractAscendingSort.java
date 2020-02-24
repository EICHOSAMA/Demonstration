package per.eicho.demo2.algorithm.sort;

public abstract class AbstractAscendingSort implements AscendingSort {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sort(int[] nums) {
		
	}
	
	protected abstract void doSort(int nums);
}
