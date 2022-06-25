package per.eicho.demo.leetcode.q201_300;

/**
 * <p>201. Bitwise AND of Numbers Range 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/bitwise-and-of-numbers-range/">
 *   201. Bitwise AND of Numbers Range</a>
 */
public final class Q201 {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        //assert 0 <= m && n <= 0x7fff_ffff;
        
        int l = 0x40_00_00_00;
        int r = l;
        while (m < l) l >>= 1;
        while (n < r) r >>= 1;
        if (l != r) return 0;
        
        return l + rangeBitwiseAnd(m - l, n - l);
    }
}
