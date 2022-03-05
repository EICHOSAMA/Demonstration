package per.eicho.demo.leetcode.q301_400;

/**
 * <p>338. Counting Bits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/counting-bits/">338. Counting Bits</a>
 */
public final class Q338 {
    public int[] countBits(int num) {
        final int[] result = new int[num + 1];
        int offsset = 1;
        while (true) {
            // 0 - offset - 1;
            // offset - offset * 2 - 1
            // offset *= 2;
            for (int i = offsset; i < offsset * 2 ; i++) {
                if (i > num)
                    return result;
                result[i] = result[i - offsset] + 1;
            }
            offsset *= 2;
        }        
    }
}
