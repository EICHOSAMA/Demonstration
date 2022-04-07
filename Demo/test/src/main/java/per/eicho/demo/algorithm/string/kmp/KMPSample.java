package per.eicho.demo.algorithm.string.kmp;

/**
 * <p>KMP实例类</p>
 * 
 * <pre>
 *  在解决在一个字符串里寻找另一个目标字符串是否出现的问题时，暴力地枚举每一个开头并作整个字符串对比的BF算法是非常不效率的。
 *  当字符串长度为n，目标字符串长度为m的时候，其时间复杂度能达到惊人的O(n*m)。
 * 
 *  而KMP(Knuth-Morris-Pratt) 字符串匹配算法，则能利用隐藏的信息去把枚举起始匹配位置的过程，变成跳跃式地。
 * （即不枚举每一个起始匹配位置，利用上一次匹配的结果推算出下一个起始匹配位置。）
 * </pre>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Knuth-Morris-Pratt_algorithm">kmp - wikipedia</a>
 */
public final class KMPSample {
    
    /**
     * <p>判定目标字符串是否是给定字符串的子串</p>
     */
    public boolean match(String str, String target) {
        final int n = str.length();
        final int m = target.length();
        
        if (str == null || n == 0) return false;
        if (target == null || m == 0) return false;

        // 1. 预处理, 构筑 Partial Match Table
        final int[] pmt = createPartialMatchTable(target);

        // 2.
        int i = 0, j = 0;

        while (true) {
            // 边界条件
            if (j == m) return true; // 匹配成功
            if (i == n) return false;   // 匹配到字符串尾，未能找到相同子串

            if (str.charAt(i) == target.charAt(j)) {
                // case1: str[i] == target[j], 移动至下一位
                i++;
                j++;
            } else if (j == 0) {
                // case2: str[i] != target[0](第一位字符就匹配失败), 直接移动i至下一位
                i++;
            } else {
                // case3: str[i] != target[j] (j > 0), 非第一位字符匹配失败。
                //  即：str[i - j, i - 1] == target[0, j - 1] (前j个字符匹配成功)
                //  则通过查pmt表位移到替补方案位继续处理
                j = pmt[j - 1]; // 移动j等于变相移动匹配开始位置
            }
        }
    }

    /**
     * <p>预处理 - 创建pmt表</p>
     * 
     * <pre>
     *  所谓部分匹配表pmt, 其第i位存储的信息 pmt[i]代表的含义是
     *  
     *  目标字符串截至到第i位时的最长相同前缀后缀的长度。
     *    目标字符串 abcabc
     *    pmt表    　000123  
     * 
     * </pre>
     * 
     * @param target 目标字符串
     * @return pmt表, 数组长度为target的长度。
     */
    private int[] createPartialMatchTable(String target) {
        assert target != null;
        assert target.length() != 0;

        final int size = target.length();
        final int[] pmt = new int[size];
        
        int prefixP = 0; // 前缀指针，也可推导已匹配长度信息
        int suffixP = 1; // 后缀指针，position, pmt[0] 总是0，从1开始
        while (suffixP < size) {
            if (target.charAt(suffixP) == target.charAt(prefixP)) {
                // case1: target[prefixP] == target[suffixP], 记录当前位pmt为已匹配长度信息。
                pmt[suffixP++] = ++prefixP;
            } else if (prefixP == 0) {
                // case2: target[0] != target[suffixP] (与第一位不匹配)，那么最长公共前缀后缀长度为0。
                pmt[suffixP++] = 0;
            } else {
                // case3: target[prefixP] != target[suffixP] 对比位字符不相同。
                //  隐藏信息：已匹配长度 = prefixP , 已匹配 Range[0, prefixP) = Range[suffixP - prefixP, suffixP)
                prefixP = pmt[prefixP - 1];
            }
        }
        return pmt;
    }

    public static void main(String[] args) {
        final KMPSample kmp = new KMPSample();

        int[] test = kmp.createPartialMatchTable("aabaaac");
        for (int i = 0; i < test.length; i++) System.out.print(test[i]);
        // aabaaac
        // 0101200
        System.out.println(kmp.match("aabaaabaaac", "aabaaac"));
    }
}
