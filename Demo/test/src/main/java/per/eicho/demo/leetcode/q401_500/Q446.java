package per.eicho.demo.leetcode.q401_500;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>446. Arithmetic Slices II - Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/arithmetic-slices-ii-subsequence/">
 *   446. Arithmetic Slices II - Subsequence</a>
 */
public final class Q446 {
    public int numberOfArithmeticSlices(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. -2^31 <= nums[i] <= 2^31 - 1
        // Map<Integer, Integer> count =
        final int n = nums.length;
        if (n <= 2) return 0;
        final long[] lNums = new long[n];
        for (int i = 0; i < n; i++) lNums[i] = nums[i];
        return numberOfArithmeticSlices(lNums);
    }

    private int numberOfArithmeticSlices(long[] nums) {
        final int n = nums.length;
        // Map<Target Num, Map<Diff, Count>>
        final Map<Long, Map<Long, Integer>> map = new HashMap<>();


        long diff = nums[1] - nums[0];
        long target = nums[1] + diff;
        map.put(target, new HashMap<>());
        map.get(target).put(diff, 1);

        int result = 0;
        for (int i = 2; i < n; i++) {
            final long num = nums[i];

            if (map.containsKey(num)) {
                // Map<Diff, Count>
                final Map<Long, Integer> distance2CountMap = map.get(num); 
                for (Map.Entry<Long, Integer> entry : distance2CountMap.entrySet()) {
                    diff = entry.getKey();
                    final int count = entry.getValue();

                    result += count;
                    
                    target = num + diff;

                    if (!map.containsKey(target)) map.put(target, new HashMap<>());
                    final Map<Long, Integer> d2cMap = map.get(target);
                    d2cMap.putIfAbsent(diff, 0);
                    d2cMap.put(diff, d2cMap.get(diff) + count);
                }
            }

            for (int j = 0; j < i; j++) {
                final long numJ = nums[j];
                diff = num - numJ;
                target = num + diff;

                if (!map.containsKey(target)) map.put(target, new HashMap<>());
                final Map<Long, Integer> d2cMap = map.get(target);
                d2cMap.putIfAbsent(diff, 0);
                d2cMap.put(diff, d2cMap.get(diff) + 1);
            }
        }

        return result;        
    }

    public static void main(String[] args) {
        Q446 q446 = new Q446();
        System.out.println(q446.numberOfArithmeticSlices(new int[]{7, 7, 7, 7, 7}));
        System.out.println(q446.numberOfArithmeticSlices(new int[]{2,4,6,8,10}));
    }
}

