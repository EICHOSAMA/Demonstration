package per.eicho.demo.leetcode.q801_900;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>870. Advantage Shuffle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/advantage-shuffle/">
 *  870. Advantage Shuffle</a>
 */
public final class Q870 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        // 1. 1 <= nums1.length <= 10^5
        // 2. nums2.length == nums1.length
        // 3. 0 <= nums1[i], nums2[i] <= 10^9
        final int n = nums1.length;
        final int[] sortedNums2 = new int[n];
        System.arraycopy(nums2, 0, sortedNums2, 0, n);
        
        Arrays.sort(nums1);
        Arrays.sort(sortedNums2);


        int p1 = 0;
        int p2 = 0;
        int[] result = new int[n];
        final Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        final LinkedList<Integer> unused = new LinkedList<>();
        while (p1 < n) {
            final int num1 = nums1[p1];
            final int num2 = sortedNums2[p2];
            if (num1 <= num2) {
                p1++;
                unused.add(num1);
            } else {
                // num1 > num2
                p1++;
                p2++;
                if (!map.containsKey(num2)) map.put(num2, new LinkedList<>());
                map.get(num2).add(num1);
            }
        }

        for (int i = 0; i < n; i++) {
            final int num2 = nums2[i];

            if (!map.containsKey(num2)) {
                result[i] = unused.pollFirst();
                continue;
            }

            final LinkedList<Integer> candidates = map.get(num2);
            result[i] = candidates.pollFirst();
            if (candidates.size() == 0) map.remove(num2);
        }

        return result;
    }

    public static void main(String[] args) {
        Q870 q870 = new Q870();
        int[] arg1 = new int[]{2,7,11,15};
        int[] arg2 = new int[]{1,10,4,11};
        OutputUtils.println(q870.advantageCount(arg1, arg2));
    }

}
