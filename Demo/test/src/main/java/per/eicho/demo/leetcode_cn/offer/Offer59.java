package per.eicho.demo.leetcode_cn.offer;

import java.util.TreeMap;

/**
 * <p>剑指 Offer 59 - I. 滑动窗口的最大值 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/">
 *   剑指 Offer 59 - I. 滑动窗口的最大值</a>
 */
public final class Offer59 {
    private static final int L = 0;
    private static final int R = 1;

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 你可以假设 k 总是有效的，在输入数组 不为空 的情况下，1 ≤ k ≤ nums.length。
        if (k == 1) return nums;
        final int n = nums.length;
        final int[] result = new int[n - k + 1];

        final int[] range = new int[]{0, 0}; // range [l, r)
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>((idx1, idx2) -> {
            final int num1 = nums[idx1];
            final int num2 = nums[idx2];
            if (num1 != num2) return Integer.compare(nums[idx1], nums[idx2]);
            return Integer.compare(idx1, idx2);
        });

        while (range[R] < n) {
            while (range[R] < n && range[R] - range[L] < k) {
                treeMap.put(range[R], nums[range[R]]);
                range[R]++;
            }

            result[range[L]] = treeMap.lastEntry().getValue();
            treeMap.remove(range[L]++);
        }

        return result;
    }

    public static void main(String[] args) {
        final Offer59 offer59 = new Offer59();
        offer59.maxSlidingWindow(new int[]{-7,-8,7,5,7,1,6,0}, 4);
    }
}
