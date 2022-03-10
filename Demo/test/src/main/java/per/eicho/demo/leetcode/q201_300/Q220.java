package per.eicho.demo.leetcode.q201_300;

import java.util.TreeSet;

/**
 * <p>220. Contains Duplicate III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/contains-duplicate-iii/">220. Contains Duplicate III</a>
 */
public final class Q220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 1. 1 <= nums.length <= 2 * 10^4
        // 2. -2^31 <= nums[i] <= 2^31 - 1
        // 3. 0 <= k <= 10^4
        // 4. 0 <= t <= 2^31 - 1
        return containsNearbyAlmostDuplicate(nums, k, (long)t);
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, long t) {
        final int n = nums.length;        
        final TreeSet<Long> sortedSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            final long num = nums[i];
            final Long ceiling = sortedSet.ceiling(num - t);
            if (ceiling != null && ceiling <= num + t) return true;
            
            sortedSet.add(num);
            if (sortedSet.size() > k) {
                sortedSet.remove((long)nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q220 q220 = new Q220();
        boolean result = q220.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0);
        System.out.println(result);
    }
}
