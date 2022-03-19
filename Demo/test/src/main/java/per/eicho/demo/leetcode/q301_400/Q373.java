package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <p>373. Find K Pairs with Smallest Sums 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-k-pairs-with-smallest-sums/">
 *   373. Find K Pairs with Smallest Sums</a>
 */
public final class Q373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 1. 1 <= nums1.length, nums2.length <= 10^5
        // 2. -10^9 <= nums1[i], nums2[i] <= 10^9
        // 3. nums1 and nums2 both are sorted in ascending order.
        // 4. 1 <= k <= 10^4
        if (nums1.length > nums2.length) {
            final int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        final int len1 = nums1.length;
        final int len2 = nums2.length;
        if ((long)k > (long)len1 * (long)len2) k = len1 * len2;

        final List<List<Integer>> result = new ArrayList<>(k);

        final int[][] positions = new int[len1][3]; // <p1, p2, sum>
        for (int i = 0; i < len1; i++) {
            final int[] position = positions[i];
            position[0] = i;
            position[1] = 0;
            position[2] = nums1[position[0]] + nums2[position[1]];
        }

        final PriorityQueue<int[]> minHeap = new PriorityQueue<>((p1, p2) -> Integer.compare(p1[2], p2[2]));
        
        for (int[] position : positions) minHeap.add(position);
        
        for (int i = 0; i < k; i++) {
            final int[] position = minHeap.poll();
            
            result.add(Arrays.asList(nums1[position[0]], nums2[position[1]]));
            position[1]++;
            if (position[1] < len2) {
                position[2] = nums1[position[0]] + nums2[position[1]];
                minHeap.add(position);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q373 q373 = new Q373();
        List<List<Integer>> result = q373.kSmallestPairs(new int[]{1,2}, new int[]{3}, 3);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + ",");
            }
            System.out.println();
        }
    }
}
