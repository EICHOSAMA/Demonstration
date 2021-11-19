package per.eicho.demo.leetcode.q001_100;

/**
 * <p>4. Median of Two Sorted Arrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">4. Median of Two Sorted Arrays</a>
 */
final public class Q4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums1 and nums2 are two sorted array.

        if (nums1.length == 0 && nums2.length == 0)
            return 0.0;        
        // 1. swap two arrays to make nums1 always be the smaller one.
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // assert nums1.length <= nums2.length;

        // 2. init constant size: rounded.
        final int size = (nums1.length + nums2.length + 1) / 2;
        final boolean isEven = nums1.length + nums2.length == size * 2; 

        /**
         * 3. find two count(count1 & count2) to match the conditions below
         *  i . count1 + count2 = size
         *  ii. max(nums1[count1-1], nums2[count2-1]) < max(nums1[count1+1], nums2[count2+1])
         */
        int l = 0, r = nums1.length;
        while (true) {
            final int count1 = (l + r + 1) / 2;
            final int count2 = size - count1;

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if (count1 != 0) l1 = nums1[count1 - 1];
            if (count1 != nums1.length) r1 = nums1[count1];
            
            if (count2 != 0) l2 = nums2[count2 - 1];
            if (count2 != nums2.length) r2 = nums2[count2];

            int maxOfLeft = Math.max(l1, l2);
            int minOfRight = Math.min(r1, r2);

            if (maxOfLeft <= minOfRight) {
                return isEven? (maxOfLeft + minOfRight) / 2.0 : maxOfLeft;
            }

            if (l1 < l2) l = count1; // move right.
            else r = count1 - 1; // move left.
        }
    }
}
