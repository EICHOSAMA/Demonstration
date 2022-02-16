package per.eicho.demo.leetcode.q1001_1100;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1018. Binary Prefix Divisible By 5 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-prefix-divisible-by-5/">
 *   1018. Binary Prefix Divisible By 5</a>
 */
public final class Q1018 {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. nums[i] is either 0 or 1.        
        final List<Boolean> result = new ArrayList<>(nums.length);
        
        int remain = 0;
        for (int num : nums) {
            remain <<= 1;
            remain += num;
            
            remain %= 5;
            result.add(remain == 0); 
        }
        
        return result;
    }

    public static void main(String[] args) {
        Q1018 q1018 = new Q1018();
        System.out.println(q1018.prefixesDivBy5(new int[]{1,1,0,0,0,1,0,0,1}));
    }
}
