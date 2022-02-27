package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>912. Sort an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-an-array/">912. Sort an Array</a>
 */
public final class Q912 {
    public int[] sortArray(int[] nums) {
        // 1. 1 <= nums.length <= 5 * 10^4
        // 2. -5 * 10^4 <= nums[i] <= 5 * 10^4

        int n = nums.length;
        // 初始化堆，从最后一个父节点开始调整堆。（优化 ½N 叶子节点的O(1) heapify调用）
        // 小知识：最后一个父节点的index为 (½N - 1).
        for (int i = (n / 2) - 1 ; i >= 0; i--) heapify(nums, i, n);
        
        for (int i = n - 1; i > 0; i--) {
            final int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;

            heapify(nums, 0, i);
        }

        return nums;
    }

    private void heapify(int[] nums, int current, int bound) {
        int father = current;
        int son = father * 2 + 1;
        while (son < bound) {
            if (son + 1 < bound && nums[son] < nums[son + 1]) son++; // 选择右儿子做代表 (右儿子更大)
            if (nums[father] > nums[son]) break;

            // Swap
            final int temp = nums[father];
            nums[father] = nums[son];
            nums[son] = temp;

            father = son;
            son = father * 2 + 1;
        }
    }
}
