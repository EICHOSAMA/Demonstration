package per.eicho.demo.leetcode.q701_800;

/**
 * <p>717. 1-bit and 2-bit Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/1-bit-and-2-bit-characters/">717. 1-bit and 2-bit Characters</a>
 */
public final class Q717 {
    public boolean isOneBitCharacter(int[] bits) {
        final boolean[] f = new boolean[bits.length];

        int index = bits.length - 1;
        f[index] = true; 

        while(index > 0) {
            if (!f[index]) {
                index--;
                continue; // 到達できないケース
            }

            final int iM1 = index - 1;
            if (iM1 >= 0 && bits[iM1] == 0) {
                f[iM1] = true;
            }

            final int iM2 = index - 2;
            if (iM2 >= 0 && bits[iM2] == 1) {
                f[iM2] = true;
            }

            index--;
        }
        return f[0];
    }
}
