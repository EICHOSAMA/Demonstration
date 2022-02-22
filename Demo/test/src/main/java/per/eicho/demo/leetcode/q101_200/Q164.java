package per.eicho.demo.leetcode.q101_200;

import java.util.Arrays;

/**
 * <p>164. Maximum Gap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-gap/">164. Maximum Gap</a>
 */
public final class Q164 {
    public int maximumGap(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 0 <= nums[i] <= 10^9   
        final int n = nums.length;
        if (n == 1) return 0;     

        final int maxVal = getMaxVal(nums);        
        final int[] count = new int[10]; // [0, 9]
        final int[] buffer = new int[n]; // 工作数组，临时存储中间数据

        long exp = 1;
        while (maxVal >= exp) {
            // 1. init
            Arrays.fill(count, 0);

            // 2. count
            for (int num : nums) {
                final int digit = (num / (int)exp) % 10;
                count[digit]++;
            }

            // 3. 转换 count 数组 ⇒ lastIndex 数组
            //    本质是在长度为N的数组上分配"桶"的大小
            //    转换后数组内的信息为各桶的lastIndex (exslusive, hint: 合计Count为N，LastIndex为N-1)
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            // 4. 处理数据并填充至buffer临时数组
            for (int i = n - 1; i >= 0; i--) {
                final int num = nums[i];
                final int digit = (num / (int)exp) % 10;
                final int index = --count[digit];

                buffer[index] = num;
            }

            // 5. 回填 buffer ⇒ nums
            System.arraycopy(buffer, 0, nums, 0, n);

            // 6. 移动 exp 至下一位
            exp *= 10;
        }

        int result = 0;
        for (int i = 1; i < n; i++) {
            result = Math.max(result, nums[i] - nums[i - 1]);
        }
        return result;
    }

    private int getMaxVal(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }
}
