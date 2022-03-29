package per.eicho.demo.leetcode.q401_500;

/**
 * <p>405. Convert a Number to Hexadecimal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/convert-a-number-to-hexadecimal/">405. Convert a Number to Hexadecimal</a>
 */
public final class Q405 {
    private static final char[] mapTable = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public String toHex(int num) {
        // -2^31 <= num <= 2^31 - 1
        if (num == 0) return "0";

        final int MASK_LAST_FOUR_BITS = 0x0F;
        final StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int lastFourBits = num & MASK_LAST_FOUR_BITS;
            sb.append(mapTable[lastFourBits]);
            num = num >>> 4;
        }
        return sb.reverse().toString();
    }
}
