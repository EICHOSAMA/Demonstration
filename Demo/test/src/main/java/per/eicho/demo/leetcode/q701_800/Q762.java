package per.eicho.demo.leetcode.q701_800;

import java.util.HashSet;

/**
 * <p>762. Prime Number of Set Bits in Binary Representation 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/">762. Prime Number of Set Bits in Binary Representation</a>
 */
public final class Q762 {

    private static final HashSet<Integer> PRIME_NUM_SET;

    static {
        PRIME_NUM_SET = new HashSet<>();
        
        PRIME_NUM_SET.add(2);
        PRIME_NUM_SET.add(3);
        PRIME_NUM_SET.add(5);
        PRIME_NUM_SET.add(7);
        PRIME_NUM_SET.add(11);
        PRIME_NUM_SET.add(13);
        PRIME_NUM_SET.add(17);
        PRIME_NUM_SET.add(19);
    }

    /**
     * <p>
     *  Given two integers left and right, return the count of numbers 
     *  in the inclusive range [left, right] having a prime number of set bits in their binary representation.
     *  
     *  Recall that the number of set bits an integer has is the number of 1's present when written in binary.
     * </p>
     * 
     * @param left  1 <= left <= right <= 10^6
     * @param right 1 <= left <= right <= 10^6
     * @return
     */
    public int countPrimeSetBits(int left, int right) {
        int result = 0;

        // 1. get bits
        final int[] bits = toBits(left);

        // 2. main process
        for (int i = left; i <= right; i++) {
            // 2.1. count set bits;
            int setBitsNum = countSetBits(bits);

            if (isPrime(setBitsNum)) result++;

            // 2.2 move to next
            int remain = 1;
            int p = 0;
            while (remain != 0) {
                bits[p] += remain;
                remain = bits[p] / 2;
                bits[p] %= 2;
                p++;
            }
        }

        return result;
    }

    private int[] toBits(int num) {
        // right <= 10^6 (1111 0100 0010 0100 0000) 20bits
        int[] bits = new int[20];

        int p = 0;
        while (num != 0) {
            bits[p++] = num % 2;
            num /= 2;
        }

        return bits;
    }

    private int countSetBits(int[] bits) {
        int result = 0;
        for (int bit : bits) {
            if (bit == 1) result++;
        }
        return result;
    }

    private boolean isPrime(int num) {
        return PRIME_NUM_SET.contains(num);
    }

    public static void main(String[] args) {
        Q762 q762 = new Q762();

        System.out.println(q762.countPrimeSetBits(1, 10));
    }
}
