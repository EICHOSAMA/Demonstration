package per.eicho.demo.nowcoder.huawei;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public final class HJ041 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = -1;
        int[][] data = null;
        int row = 0;
        int p = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (n == -1) {
                n = in.nextInt();
                data = new int[2][n];
            } else {
                data[row][p++] = in.nextInt();

                if (p == n) {
                    p = 0;
                    if (++row == 2) {
                        // data ready.
                        processInput(n, data[0], data[1]);

                        n = -1;
                        data = null;
                        row = 0;
                        p = 0;
                    }
                }
            }
        }

        in.close();
    }

    private static void processInput(int n, int[] weights, int[] counts) {
        // n: [1, 10], wi: [1, 2000], ci: [1, 10]
        // 现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
        final Set<Integer> possiableNums = new HashSet<>();
        possiableNums.add(0);

        for (int i = 0; i < n; i++) {
            final int weight = weights[i];
            final int count = counts[i];
            final List<Integer> candidates = new ArrayList<>(possiableNums.size() * count);

            for (Integer possibleNum : possiableNums) {
                for (int j = 1; j <= count; j++) {
                    candidates.add(j * weight + possibleNum);
                }
            }

            possiableNums.addAll(candidates);
        }

        System.out.println(possiableNums.size());
    }
}
