package per.eicho.demo.leetcode.q301_400;

/**
 * <p>338. Counting Bits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/counting-bits/">338. Counting Bits</a>
 */
public final class Q338 {
    public int[] countBits(int num) {
        // 1. 0 <= n <= 10^5
        final int[] result = new int[num + 1];
        int offsset = 1;
        int i = 0;
        while (i < num) {
            int l = offsset, r = offsset * 2; // [l, r)
            if (r > num) r = num + 1;
            // 0 - offset - 1;
            // offset - offset * 2 - 1
            // offset *= 2;
            for (i = l; i < r ; i++) result[i] = result[i - offsset] + 1;
            offsset <<= 1;
        }
        return result;
    }
}
