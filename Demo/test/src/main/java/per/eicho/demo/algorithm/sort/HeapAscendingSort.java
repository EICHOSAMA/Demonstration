package per.eicho.demo.algorithm.sort;

/**
 * <p>堆排序 - 升序</p>
 * 
 * @author toranekojp
 */
public final class HeapAscendingSort extends AbstractAscendingSort {

    @Override
    protected void doSort(int[] nums) {
        int n = nums.length;
        // 初始化堆，从最后一个父节点开始调整堆。（优化 ½N 叶子节点的O(1) heapify调用）
        // 小知识：最后一个父节点的index为 (½N - 1).
        for (int i = (n / 2) - 1 ; i >= 0; i--) heapify(nums, i, n);
        
        for (int i = n - 1; i > 0; i--) {
            swap(nums, i, 0);
            heapify(nums, 0, i);
        }
    }

    private void heapify(int[] nums, int current, int bound) {
        int son, father = current;
        while ((son = father * 2 + 1) < bound) {
            if (son + 1 < bound && nums[son] < nums[son + 1]) son++; // 如果右儿子存在且更大，选择右儿子做代表 
            if (nums[father] > nums[son]) break; // 父亲大于两个儿子则停止调整处理

            swap(nums, father, son);

            father = son;
        }
    }    
}
