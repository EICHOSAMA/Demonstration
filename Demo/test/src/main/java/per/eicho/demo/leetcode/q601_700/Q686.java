package per.eicho.demo.leetcode.q601_700;


/**
 * <p>686. Repeated String Match 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/repeated-string-match/">686. Repeated String Match</a>
 */
public final class Q686 {
    public int repeatedStringMatch(String a, String b) {
        /**
         * Constraints:
         *   1. 1 <= a.length <= 104
         *   2. 1 <= b.length <= 104
         *   3. a and b consist of lower-case English letters.
         */
        StringBuilder sb = new StringBuilder(b.length());
        int time = 0;
        while (sb.length() < b.length()) {
            sb.append(a);
            time++;
        }

        if (match(sb.toString(), b) == true) return time; 
        time++;
        sb.append(a);
        if (match(sb.toString(), b) == true) return time; 
        return -1;
    }

    private boolean match(String str, String target) {
        if (str == null || str.length() == 0) return false;
        if (target == null || target.length() == 0) return false;

        // 1. 预处理, 构筑 Partial Match Table
        final int[] partialMatchTable = createPartialMatchTable(target);

        // 2.
        int i = 0, j = 0;

        while (true) {
            // 边界条件
            if (j == target.length()) return true; // 匹配成功
            if (i == str.length()) return false;   // 匹配到字符串尾，未能找到相同子串

            final char ch1 = str.charAt(i);
            final char ch2 = target.charAt(j);

            if (ch1 == ch2) {
                // 该位字符匹配成功, 位移到下一位
                i++;
                j++;
                continue;
            }

            // 第一位字符匹配失败
            if (j == 0) {
                i++;
                continue;
            }

            // 第j位字符匹配失败, 通过查表位移到替补方案位继续处理

            // 查表
            final int pmtJ = partialMatchTable[j - 1];

            // 位数 = 已匹配的字符数 - 部分匹配值
            final int offset = j - pmtJ;
            
            // 位移
            j -= offset;
        }
    }

    private int[] createPartialMatchTable(String target) {
        //assert target != null;
        //assert target.length() != 0;

        final int size = target.length();
        final int[] pmt = new int[size];
        
        int index = 0; // 前缀指针，也可推导长度信息
        int position = 1; // 后缀指针，position, pmt[0] 总是0，从1开始
        
        while (position < size) {
            // case1: 对比位字符相同，记录pmt并移动两个指针
            if (target.charAt(position) == target.charAt(index)) {
                pmt[position] = index + 1;
                position++;
                index++;
                continue;
            }
            // case2: 对比位字符不相同, 且Prefix字符位是0的情况，不匹配该位pmt记为0，并移动后缀指针
            if (index == 0) {
                pmt[position] = 0;
                position++;
                continue;
            }
            
            // 易错case↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            // case3: 对比位字符不相同, 且Prefix字符位不是0的情况
            // 隐藏信息1: SubString[0, index - 1] = SubString[position - index, position - 1]
            // assert true == target.substring(0, index).equals(target.substring(position - index, position));
            // 隐藏信息2: Char[index] != Char[Position]
            // assert target.charAt(position) != target.charAt(index);

            // 利用隐藏信息1和已经计算出的pmt[index - 1]信息（最长匹配前缀后缀的长度)
            // 来实现调整匹配中的长度信息。（前面有提到 → 前缀指针，也可推导长度信息)
            index = pmt[index - 1];
            // 隐藏信息3: 长度调整后满足0，或者非0时，前缀后缀相等
            // assert index == 0 || 
            //       true == target.substring(0, index).equals(target.substring(position - index, position));
        }

        return pmt;
    }

}
