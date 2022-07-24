package per.eicho.demo.leetcode.debug;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>输入数据生成用工具类</p>
 * 
 * <pre>
 *  仿LeetCode的Testcase输入，解析文本生成对应数据。
 * </pre>
 */
public final class InputUtils {
    
    public static final List<List<Integer>> gen2DList(String dataStr) {
        // dataStr sample:
        //   "[[-4,-10,-15,24,26],[0,9,12,20],[5,18,22,30]]" 
        final List<List<Integer>> data = new ArrayList<>();
        if (dataStr == null || dataStr.length() <= 2) return data;
        
        final int n = dataStr.length();
        int l = 1, r = n - 1;
        int p = l;
        while (p <= r) {
            char ch = dataStr.charAt(p);

            if (ch == '[') {
                final List<Integer> list = new ArrayList<>();

                int sign = 0;
                int num = 0;
                while (']' != (ch = dataStr.charAt(++p))) {

                    if (ch == ',') {
                        // commit
                        list.add(num * sign);
                        sign = 1;
                        num = 0;
                    } else if (ch == '-') {
                        sign = -1;
                    } else {
                        // isDigit
                        if (sign == 0) sign = 1;
                        num = num * 10 + (ch - '0');
                    }
                }

                if (sign != 0) list.add(num * sign);

                data.add(list);
            }

            p++;
        }

        return data;
    }

    public static final int[][] gen2DArray(String dataStr) {
        final List<List<Integer>> list = gen2DList(dataStr);
        final int m = list.size();
        int[][] array = new int[m][];
        for (int i = 0; i < m; i++) {
            final List<Integer> row = list.get(i);
            final int n = row.size();
            array[i] = new int[n];

            for (int j = 0; j < n; j++) array[i][j] = row.get(j);
        }

        return array;
    }

    private InputUtils() {}
}
