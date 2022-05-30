package per.eicho.demo.leetcode.q401_500;

/**
 * <p>481. Magical String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/magical-string/">
 *   481. Magical String</a>
 */
public final class Q481 {
    
    public int magicalString(int n) {
        // 1 <= n <= 10^5
        if (n <= 3) return 1; // 1221121221221121122

        final int[] str = new int[n + 1];
        str[0] = 1;
        str[1] = 2;

        int i = 1, j = 1;
        int num = 2;
        while (j < n) {
            final int count = str[i++];    
            
            for (int k = 0; k < count; k++) str[j++] = num;
            num = num == 2 ? 1 : 2;
        }

        int count = 0;
        for (int k = 0; k < n; k++) if (str[k] == 1) count++;
        return count;
    }
}
