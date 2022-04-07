package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1046. Last Stone Weight 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/last-stone-weight/">
 *   1046. Last Stone Weight</a>
 */
public final class Q1046 {
    public int lastStoneWeight(int[] stones) {
        // 1. 1 <= stones.length <= 30
        // 2. 1 <= stones[i] <= 1000        

        int n = stones.length;
        for (int i = (n / 2) - 1 ; i >= 0; i--) heapify(stones, i, n);

        int size = n;
        while (size > 1) {
            final int x = stones[0];
            stones[0] = stones[size - 1];
            stones[size - 1] = x;
            heapify(stones, 0, --size);

            final int y = stones[0];
            stones[0] = stones[size - 1];
            stones[size - 1] = y;
            heapify(stones, 0, --size);

            if (x == y) continue;

            final int newStone = x - y;
            stones[size++] = newStone;
            shiftup(stones, size - 1);
        }

        return size == 0 ? 0 : stones[0];
    }

    private void shiftup(int[] nums,  int index) {
        int father = 0;
        while (index > 0 && nums[index] > nums[father = (index - 1) / 2]) {
            final int temp = nums[index];
            nums[index] = nums[father];
            nums[father] = temp;

            index = father;
        }
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
