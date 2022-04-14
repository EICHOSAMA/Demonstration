package per.eicho.demo.leetcode.q201_300;

/**
 * <p>215. Kth Largest Element in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">215. Kth Largest Element in an Array</a>
 */
public final class Q215 {
    
    public int findKthLargest(int[] nums, int k) {
        // 1. 1 <= k <= nums.length <= 10^4
        // 2. -10^4 <= nums[i] <= 10^4
        return select(nums, nums.length - k + 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int partition(int[] nums, int l, int r) {
        if (l == r) return l;
        final int pivotIndex = r;
        final int pivot = nums[l];

        swap(nums, l , r--);
        while (l <= r) {
            if (nums[l] < pivot) {
                l++;
            } else {
                swap(nums, l, r--);
            }
        }
        swap(nums, l, pivotIndex);
        return l;
    }
    
    private int select(int[] unsortedNums, int k) {
        // 1. n == unsortedNums
        // 2. 1 <= k <= n;

        int l = 0, r = unsortedNums.length - 1;
        int pivotIndex = 0;
        k--;
        while (l != r) {
            pivotIndex = partition(unsortedNums, l, r);
            if (pivotIndex == k) break;
            if (pivotIndex < k) {
                l = pivotIndex + 1;
            } else { // pivotIndex > k
                r = pivotIndex - 1;
            }
        }
        return unsortedNums[k];
    }

    public static void main(String[] args) {
        Q215 q215 = new Q215();
        System.out.println(q215.findKthLargest(new int[]{2, 1}, 2));
    }
}
