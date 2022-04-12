package per.eicho.demo.leetcode.q301_400;

/**
 * <p>393. UTF-8 Validation 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/utf-8-validation/">393. UTF-8 Validation</a>
 */
public class Q393 {
    final static int MASK_8_8 = 0b1000_0000; // [8,8]
    final static int VALIDATE_VALUE_8_8 = 0b0000_0000; // 0

    final static int MASK_8_7 = 0b1100_0000; // [6,8]
    final static int VALIDATE_VALUE_8_7 = 0b1000_0000; // 10

    final static int MASK_8_6 = 0b1110_0000; // [6,8]
    final static int VALIDATE_VALUE_8_6 = 0b1100_0000; // 110

    final static int MASK_8_5 = 0b1111_0000; // [5,8]
    final static int VALIDATE_VALUE_8_5 = 0b1110_0000; // 1110

    final static int MASK_8_4 = 0b1111_1000; // [4,8]
    final static int VALIDATE_VALUE_8_4 = 0b1111_0000; // 1111 0

    public boolean validUtf8(int[] data) {
        // 1 <= data.length <= 2 * 10^4
        // 0 <= data[i] <= 255        
        int count = 0; // required "10xx_xxxx" data count.
        for (int oneByte: data) {
            if (count != 0) {
                if ((oneByte & MASK_8_7) != VALIDATE_VALUE_8_7) return false;
                count--;
                continue;
            }

            if ((oneByte & MASK_8_8) == VALIDATE_VALUE_8_8) {
                continue;
            } else if ((oneByte & MASK_8_6) == VALIDATE_VALUE_8_6) {
                count = 1; // byte(110xxxxx) followed by one byte.
                continue;
            } else if ((oneByte & MASK_8_5) == VALIDATE_VALUE_8_5) {
                count = 2; // byte(1110xxxx) followed by two bytes.
                continue;
            } else if ((oneByte & MASK_8_4) == VALIDATE_VALUE_8_4) {
                count = 3; // byte(11110xxx) followed by three bytes.
                continue;
            }
            return false;
        }
        return count == 0; // 0: true; !0: data insufficient.
    }
}
