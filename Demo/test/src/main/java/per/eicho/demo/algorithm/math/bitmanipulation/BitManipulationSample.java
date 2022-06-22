package per.eicho.demo.algorithm.math.bitmanipulation;

/**
 * <p>记录一些二进制操作的sample类</p>
 * 
 * <pre>
 *  
 * </pre>
 * 
 * @see <a href="https://wiki.kfd.me/_en/wiki/Two%27s_complement">[Prerequisite Knowledge] Wiki - Two's complement - 补码</a>
 * @see <a href="http://graphics.stanford.edu/~seander/bithacks.html">Stanford - Bit Twiddling Hacks</a>
 */
final class BitManipulationSample {

    private BitManipulationSample() {}
    
    /**
     * <p>获取最低位比特1</p>
     * 
     * <pre>
     *  对于给定的数字，获取其二进制表达(补码)中最低位为1的比特。
     * 
     *  1. 当没有任何一位bit为1的时候(n = 0), 返回0
     *  2. 当极端的输入 
     *       Input : {@link Integer#MIN_VALUE} (补码表达: 0b10000000_00000000_00000000_00000000)
     *       Output: 0b10000000_00000000_00000000_00000000
     * </pre>
     * 
     * @param n 对象数字 ∈ [-2^31, 2^31), 即[-2_147_483_648, 2_147_483_648)
     * @return 二进制表达中最低为的0
     * 
     * @see <a href="https://stackoverflow.com/questions/12247186/find-the-lowest-set-bit">Reference: Find the lowest set bit - StackOverflow</a>
     */
    static int getLowestNonZeroBit(int n) {
        return n & -n;
    }

    /**
     * <p>翻转所有比特位</p>
     * 
     * <pre>
     *  对于给定的数字，翻转其所有比特位并返回翻转后的结果。
     * 
     *  i.e.
     *   1. -1 (0b11111111_11111111_11111111_11111111) 
     *                          ↓
     *       0 (0b00000000_00000000_00000000_00000000)
     *   2. max_int (0b01111111_11111111_11111111_11111111)
     *                          ↓
     *      min_int (0b10000000_00000000_00000000_00000000)
     * </pre>
     * 
     * @param n 对象数字 ∈ [-2^31, 2^31)
     * @return 翻转后的结果。
     */
    static int reverse(int n) {
        // [-2^31, -1] - [0, 2^31 - 1];
        // if (n >= 0) return n == Integer.MAX_VALUE ? Integer.MIN_VALUE : -(n + 1);
        // return n == -1 ? 0 : -(n + 1);
        return n ^ 0b11111111_11111111_11111111_11111111;
    }
}
