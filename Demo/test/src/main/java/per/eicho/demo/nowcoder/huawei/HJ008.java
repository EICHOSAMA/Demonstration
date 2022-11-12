package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public final class HJ008 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = -1;
        int[][] data = null;
        int x = 0;
        int y = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (n != -1) {
                data[x][y++] = in.nextInt();
                if (y == 2) {
                    y = 0;
                    x++;
                }

                if (x == n) {
                    processInput(n, data);
                    n = -1;
                    data = null;
                    x = 0;
                    y = 0;
                }
            } else {
                n = in.nextInt();
                data = new int[n][2];
            }
        }

        in.close();
    }

    @SuppressWarnings("unchecked")
    private static void processInput(int n, int[][] data) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] kv : data) {
            final int idx = kv[0];
            final int val = kv[1];
            
            if (!map.containsKey(idx)) {
                map.put(idx, val);
            } else {
                map.put(idx, map.get(idx) + val);
            }
        }

        final Object[] entries = new Object[map.size()];
        int p = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) entries[p++] = entry;

        Arrays.sort(entries, (o1, o2) -> {
            Map.Entry<Integer, Integer> e1 = (Entry<Integer, Integer>) o1;
            Map.Entry<Integer, Integer> e2 = (Entry<Integer, Integer>) o2;

            return Integer.compare(e1.getKey(), e2.getKey());
        });

        for (Object object : entries) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>)object;
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
