package per.eicho.demo.leetcode.q701_800;

/**
 * <p>703. Kth Largest Element in a Stream 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-a-stream/">703. Kth Largest Element in a Stream</a>
 */
public final class Q703 {
    private static class KthLargest {

        private final int k; 

        private final int[] minHeap;
        int size = 0;

        public KthLargest(int k, int[] nums) {
            // 1. 1 <= k <= 10^4
            // 2. 0 <= nums.length <= 10^4
            // 3. -10^4 <= nums[i] <= 10^4
            minHeap = new int[k];
            for (int num : nums) {
                if (size < k) {
                    minHeap[size] = num;
                    shiftUp(minHeap, size++);
                    continue;
                }

                if (num <= minHeap[0]) continue;

                minHeap[0] = num;
                heapify(minHeap, 0, size);
            }
            this.k = k;
        }

        private void heapify(int[] minHeap, int p, int bound) {
            int son;
            while ((son = p * 2 + 1) < bound) {
                if (son + 1 < bound && minHeap[son + 1] < minHeap[son]) son++;
                if (minHeap[p] < minHeap[son]) break;

                swap(minHeap, p, son);
                p = son;
            }
        }

        private void shiftUp(int[] minHeap, int p) {
            int father;
            while (p > 0) {
                father = (p - 1) / 2;

                if (minHeap[father] < minHeap[p]) break;
                swap(minHeap, father, p);
                p = father;
            }
        }

        private void swap(int[] minHeap, int i, int j) {
            final int temp = minHeap[i];
            minHeap[i] = minHeap[j];
            minHeap[j] = temp;
        }
        
        /**
         * <p>
         * Appends the integer val to the stream and returns the element 
         * representing the kth largest element in the stream.
         * </p>
         * 
         * @param val
         * @return kth largest element
         */
        public int add(int val) {
            if (size < k) {
                minHeap[size] = val;
                shiftUp(minHeap, size++);
                return minHeap[0];
            }

            if (val < minHeap[0]) return minHeap[0];
            minHeap[0] = val;
            heapify(minHeap, 0, size);
            return minHeap[0];
        }
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));   // return 4
        System.out.println(kthLargest.add(5));   // return 5
        System.out.println(kthLargest.add(10));  // return 5
        System.out.println(kthLargest.add(9));   // return 8
        System.out.println(kthLargest.add(4));   // return 8
    }
}
