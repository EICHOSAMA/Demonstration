package per.eicho.demo.algorithm.sort;

import java.util.Arrays;

/**
 * <p>基数排序</p>
 * <pre>
 *   基数排序有两种实现方式，分别是
 *     1. 最高位优先(Most Significant Digit first)法， 简称MSD法
 *     2. 最低位优先(Least Significant Digit first)法，简称LSD法
 * </pre>
 * 
 * @author toranekojp
 * 
 * @see <a href="https://baike.baidu.com/item/基数排序">百度百科 - 基数排序</a>
 * @see <a href="https://en.wikipedia.org/wiki/Radix_sort">Wikipedia - Radix sort</a>
 */
public class RadixAscendingSort extends AbstractAscendingSort {

    @Override
    protected void doSort(int[] nums) {
        final int n = nums.length;
        if (n == 1) return;

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

    }
    
    /**
     * <p>获取最大值</p>
     * 
     * <pre>
     *  遍历给定数组，获取数组内最大值。
     * </pre>
     * 
     * @param nums 处理对象数组, non-null
     * @return 数组内最大值
     */
    private int getMaxVal(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }
}
