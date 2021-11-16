package per.eicho.demo.leetcode.q601_700;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>697. Degree of an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/degree-of-an-array/">697. Degree of an Array</a>
 */
public final class Q697 {

    private static final class Q697ProcessInfo {

        final int firstIndex;
        
        int lastIndex;
        int count;

        Q697ProcessInfo(int index) {
            this.firstIndex = index;
            count = 1;
            lastIndex = index;
        }

        void refreshLastIndex(int index) {
            count++;
            lastIndex = index;
        }
    }


    public int findShortestSubArray(int[] nums) {
        // 1. nums.length will be between 1 and 50,000.
        // 2. nums[i] will be an integer between 0 and 49,999
        final Map<Integer, Q697ProcessInfo> map = new HashMap<>();

        // 1. gather information
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];

            Q697ProcessInfo info = map.get(num);
            if (info == null) {
                info = new Q697ProcessInfo(i);
                map.put(num, info);
            } else {
                info.refreshLastIndex(i);
            }

            maxCount = Math.max(info.count, maxCount);
        }

        // 2. get result from gathered information

        // 2.1 find 
        final int degree = maxCount;
        return map.values().stream()
            .filter(info -> info.count == degree)
            .map(info -> info.lastIndex - info.firstIndex + 1)
            .mapToInt(num -> num.intValue())
            .min().orElse(Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        Q697 q697 = new Q697();

        int result = q697.findShortestSubArray(new int[]{1, 2, 2, 3, 1});
        System.out.println(result); 
    }
}
