package per.eicho.demo.leetcode.q501_600;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>560. Subarray Sum Equals K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/subarray-sum-equals-k/">560. Subarray Sum Equals K</a>
 */
public final class Q560 {
    public int subarraySum(int[] nums, int k) {
        // 1. 1 <= nums.length <= 2 * 10^4
        // 2. -1000 <= nums[i] <= 1000
        // 3. -10^7 <= k <= 10^7        
        
        final int n = nums.length;

        // 1. main process
        // Map<sum, LinkedList<0based-Index>>
        final Map<Integer, LinkedList<Integer>> map = new HashMap<>(n);
        final int[] sums = new int[n];
        sums[0] = nums[0];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sums[i] = sum;

            // save info to map.
            final Integer iSum = sum;
            if (!map.containsKey(iSum)) {
                map.put(iSum, new LinkedList<>());
            }

            map.get(iSum).addLast(i);
        }

        int result = 0;
        sum = 0;
        for (int i = 0; i < n; i++) {
            final int offset = k + sum;
            final Integer iOffset = offset;

            if (map.containsKey(iOffset)) {
                result += map.get(iOffset).size();
            }
            
            // remove current idx.
            sum += nums[i];
            map.get(sum).removeFirst();
        }
        
        return result;
    }

    public static void main(String[] args) {
        Q560 q560 = new Q560();

        System.out.println(q560.subarraySum(new int[]{1,2,3}, 3));
    }
}
