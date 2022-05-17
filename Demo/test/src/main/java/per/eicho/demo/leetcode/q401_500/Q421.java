package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>421. Maximum XOR of Two Numbers in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/">
 *   421. Maximum XOR of Two Numbers in an Array</a>
 */
public final class Q421 {
    public int findMaximumXOR(int[] nums) {
        // 1. 1 <= nums.length <= 2 * 10^5
        // 2. 0 <= nums[i] <= 2^31 - 1
        final int n = nums.length;
        if (n == 1) return 0;        
        final int max = getMaxNum(nums);
        final int maxBit = getMaxBit(max); // maxBit = 2^x (x >= 0)
    
        int count = 0;
        for (int num : nums) {
            if (num >= maxBit) count++;
        }

        if (count == n) {
            for (int i = 0; i < n; i++) {
                nums[i] -= maxBit;
            }
            return findMaximumXOR(nums);
        } else {
            final List<Integer> small = new ArrayList<>(n - count);
            final List<Integer> big = new ArrayList<>(count);
            for (int num : nums) {
                if (num >= maxBit) {
                    big.add(num - maxBit);
                } else {
                    small.add(num);
                }
            }

            return noname(small, big, maxBit / 2, maxBit);
        }   
    }

    private int noname(List<Integer> small, List<Integer> big, int maxBit, int current) {
        if (small.size() == 0 || big.size() == 0) return -1;
        if (maxBit == 0) return current;

        final int c1 = countBigger(small, maxBit);
        final int c2 = countBigger(big, maxBit);
        if (c1 == small.size() && c2 == big.size()) {
            for (int i = 0; i < small.size(); i++) small.set(i, small.get(i) - maxBit);
            for (int i = 0; i < big.size(); i++) big.set(i, big.get(i) - maxBit);
            return noname(small, big, maxBit / 2, current);
        } else if (c1 == 0 && c2 == 0) {
            return noname(small, big, maxBit / 2, current);
        }

        List<Integer> s1 = new ArrayList<>(c1); // >= maxbit
        List<Integer> s2 = new ArrayList<>(small.size() - c1); // < maxbit
        List<Integer> b1 = new ArrayList<>(c2); // >= maxbit
        List<Integer> b2 = new ArrayList<>(big.size() - c2); // < maxbit
        
        for (Integer s : small) {
            if (s >= maxBit) {
                s1.add(s - maxBit);
            } else {
                s2.add(s);
            }
        }

        for (Integer b : big) {
            if (b >= maxBit) {
                b1.add(b - maxBit);
            } else {
                b2.add(b);
            }
        }

        int l = noname(s1, b2, maxBit / 2, current + maxBit);
        int r = noname(s2, b1, maxBit / 2, current + maxBit);

        return Math.max(l, r);
    }
 
    private int getMaxNum(int[] nums) {
        // 2. 0 <= nums[i] <= 2^31 - 1
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        return max;
    }

    private int countBigger(List<Integer> nums, int maxBit) {
        int count = 0;
        for (Integer num : nums) {
            if (num >= maxBit) count++;
        }
        return count;
    }

    private int getMaxBit(int num) {
        long result = 1;
        while (num >= result * 2) result *= 2;
        return (int)result;
    }

    public static void main(String[] args) {
        Q421 q421 = new Q421();
        Random random = new Random();
        int[] input = new int[200_000];
        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(Integer.MAX_VALUE);
        }

        System.out.println(System.currentTimeMillis());
        System.out.println(q421.findMaximumXOR(input));
        System.out.println(System.currentTimeMillis());
    }
}
