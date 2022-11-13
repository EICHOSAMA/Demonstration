package per.eicho.demo.algorithm.string.palindromic.manacher;

/**
 * <p>Manacher - 马拉车算法</p>
 * 
 * <pre>
 *  Manacher算法，也被称为马拉车算法(*音*)。是由Manacher在1975年
 *  发现的一种在线性时间内解决最长回文子串问题(Longest palindromic substring)的算法。
 * 
 *  最长回文子串问题：给定一个字符串，找出其中最长的回文子串。
 *  其暴力解法（BF）是枚举中点，其时间复杂度能高达O(N^2)。
 *  而Manacher算法则是通过规避大量无效case，而达到O(N)的线性时间复杂度。
 * 
 *  Wikipedia：
 *    Manacher (1975) invented a linear time algorithm for listing all the palindromes that appear 
 *    at the start of a given string. 
 *  
 *    However, as observed e.g., by Apostolico, Breslauer & Galil (1995), the same algorithm can also 
 *    be used to find all maximal palindromic substrings anywhere within the input string, 
 *    again in linear time. 
 *  
 *    Therefore, it provides a linear time solution to the longest palindromic substring problem. 
 *    Alternative linear time solutions were provided by Jeuring (1994), and by Gusfield (1997), 
 *    who described a solution based on suffix trees. Efficient parallel algorithms are also known for the problem.
 *  
 * </pre>
 */
public final class ManacherSample {

    public static void main(String[] args) {
        ManacherSample manacher = new ManacherSample();
        System.out.println(manacher.maxPalindromicLength("abc"));
        System.out.println(manacher.maxPalindromicLength("abccba"));

        System.out.println(manacher.maxPalindromicLength("1234567898765432"));
    }
    
    public int maxPalindromicLength(String str) {
        if (str == null || str.length() == 0) return 0;

        final char[] manacherStr = toManacherHelperString(str);
        final int n = manacherStr.length;
        final int[] f = new int[n]; // 存储半径信息

        int c = -1; // 中心点
        int rBound = -1; // 右边界

        int maxLen = 1;

        // 枚举中心点
        for (int i = 0; i < n; i++) {
            // 1. 跳过重复计算
            //   核心点，跳过不必要的中心外扩的关键。可以直接根据已有信息算出初始半径。
            //   对于大的回文[c - f[c], c + f[c]]一定有一个i的相对c的对称点(2*c - i)已经被计算过了。
            //                sp   ↓中心点
            //                ↓    ↓    ↓目前位置
            //  |---------l---s----c----i---r-------------|
            //            ↑左边界未记录     ↑右边界
            //                ↑sp点的最大半径一定被计算过（计算顺序为 s-->c-->i (当前正在计算的位置)）
            //                ↑这个信息的利用是跳过不必要中心外扩的核心。
            //   那么利用这个已经计算过的f[getSymmetryPointIndex(c, i)]信息则可以跳过很大一部分重复计算过程。
            int radius = rBound > i ? Math.min(f[getSymmetryPointIndex(c, i)], rBound - i) : 1;

            // 2. 中心外扩
            //   接上跳过的部分完成未完成的中心外扩处理。
            while (0 <= i - radius &&  i + radius < n) {
                if (manacherStr[i + radius] != manacherStr[i - radius]) break;
                radius++;
            }

            // 3. 维护当前边界最右回文子串信息。
            if (i + radius > rBound) {
                rBound = i + radius;
                c = i;
            }

            // Update Result
            maxLen = Math.max(maxLen, f[i] = radius);
        }
        return maxLen - 1;
    }

    /**
     * <p>获取对称点的index</p>
     * 
     * <pre>
     *  i相对c的对称点的index为c - (i - c), 既为 (2c -i)
     * </pre>
     */
    private int getSymmetryPointIndex(int c, int i) {
        return 2 * c - i;
    }

    private char[] toManacherHelperString(String str) {
        final int n = str.length();
        final char[] manacherStr = new char[n * 2 + 1];

        int p = 0;
        for (int i = 0; i < n; i++) {
            manacherStr[p++] = '#';
            manacherStr[p++] = str.charAt(i);
        }
        manacherStr[p] = '#';

        return manacherStr;
    } 
}
