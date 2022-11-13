package per.eicho.demo.nowcoder.nc;

public final class NC149 {

    public static void main(String[] args) {
        NC149 nc149 = new NC149();
        System.out.println(nc149.kmp("ababab", "ababababa"));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算模板串S在文本串T中出现了多少次
     * @param S string字符串 模板串
     * @param T string字符串 文本串
     * @return int整型
     */
    public int kmp (String S, String T) {
        // len(S): [1, 50_0000], len(T): [1, 100_0000]
        final String target = S;
        final String str = T;
        final int[] pmt = createPartialMatchTable(target); 
        final int n = str.length();
        final int m = target.length();

        int i = 0, j = 0; // i for taget, j for str
        int count = 0;
        while (j < n) {
            if (i == m) {
                count++;
                i = pmt[i - 1];
                continue;
            }

            final char chI = target.charAt(i);
            final char chJ = str.charAt(j);

            if (chI == chJ) {
                i++; j++; continue;
            } else if (i == 0) {
                j++; 
            } else {
                i = pmt[i - 1]; // 移动到候选人位置, assert pmt[i - 1] < i;
            }
        }
        if (i == m) count++;

        return count;
    }

    private int[] createPartialMatchTable(String target) {
        final int m = target.length();
        final int[] pmt = new int[m];

        int i = 0, j = 1;
        while (j < m) {
            final char chI = target.charAt(i);
            final char chJ = target.charAt(j);

            if (chI == chJ) {
                // pmt[j] = i + 1; i++; j++; ↓
                pmt[j++] = ++i;
            } else if (i == 0) {
                pmt[j++] = 0;
            } else {
                i = pmt[j - 1];
            }
        }

        return pmt; 
    }
}
