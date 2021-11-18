package per.eicho.demo.leetcode.q701_800;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>703. Kth Largest Element in a Stream 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-a-stream/">703. Kth Largest Element in a Stream</a>
 */
public final class Q703 {
    private static class KthLargest {

        private final int k; 
        private final List<Integer> nums = new LinkedList<>();

        public KthLargest(int k, int[] nums) {
            Arrays.sort(nums);

            for (int num : nums) {
                this.nums.add(num);
            }
            this.k = k;
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
            final Integer iVal = val;

            if (nums.size() == 0 || iVal < nums.get(0)) {
                nums.add(0, iVal);
            } else if (iVal > nums.get(nums.size() - 1)) {
                nums.add(iVal);
            } else {
                final int index = binarySearch(iVal, 0, nums.size() - 1);    
                nums.add(index + 1, iVal);    
            }
            return nums.get(nums.size() - k);
        }

        private int binarySearch(final Integer val, int l, int r) {
            if (l == r) {
                return l;
            }            
            int mid = (l + r + 1) / 2;
            final Integer numMid = nums.get(mid);

            if (val < numMid) return binarySearch(val, l, mid - 1);
            return binarySearch(val, mid, r);
        }
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        kthLargest.add(3);   // return 4
        kthLargest.add(5);   // return 5
        kthLargest.add(10);  // return 5
        kthLargest.add(9);   // return 8
        kthLargest.add(4);   // return 8
    }
}
