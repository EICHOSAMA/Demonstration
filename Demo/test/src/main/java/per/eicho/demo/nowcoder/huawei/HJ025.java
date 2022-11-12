package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class HJ025 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int[][] nums = null;
        int row = -1;
        int n = 0;
        int p = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (row != -1) {
                if (n == -1) {
                    n = in.nextInt();
                    nums[row] = new int[n];
                    continue;
                }

                nums[row][p++] = in.nextInt();
                if (p == n) {
                    row++;
                    if (row == 2) {
                        processInput(nums[0], nums[1]);
                        row = -1;
                        n = 0;
                        p = 0;
                    } else {
                        n = -1;
                        p = 0;
                    }
                }

                
                if (p == n) {
                    row++;
                    n = -1;
                    row++;
                }
            } else {
                row = 0;
                n = in.nextInt();
                nums = new int[2][n];
                nums[0] = new int[n];
                p = 0;
            }
        }

        in.close();
    }

    private static void processInput(int[] l, int[] r) {
        Arrays.sort(r);

        final String[] lStrings = toStrings(l);

        // 重复元素不处理，输入的数据都是都等于0的int。
        int size = 0;
        List<Integer> output = new LinkedList<>();
        output.add(size);
        for (int i = 0, prev = -1; i < r.length; i++) {
            final int ri = r[i];
            if (ri == prev) continue; // 重复元素不处理

            prev = ri;
            final List<Integer> indexes = processElement(lStrings, toString(ri));

            if (indexes.size() == 0) continue; // 没有被包含不输出
            size += (1 + 1 + indexes.size() * 2);

            output.add(ri);
            output.add(indexes.size());
            for (Integer index : indexes) {
                output.add(index);
                output.add(l[index]);
            }
        }

        // output
        output.set(0, size);
        int count = output.size();
        for (Integer integer : output) {
            count--;
            System.out.print(integer);
            if (count != 0) System.out.print(' ');
        }
    }

    private static String[] toStrings(int[] l) {
        final int n = l.length;
        final String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = toString(l[i]);
        }
        return result;
    }

    private static String toString(int num) {
        return String.valueOf(num);
    }

    private static List<Integer> processElement(String[] lStrings, String ri) {
        final List<Integer> indexes = new LinkedList<>();
        final int n = lStrings.length;
        for (int i = 0; i < n; i++) {
            final String lString = lStrings[i];
            if (!lString.contains(ri)) continue;
            indexes.add(i);
        }
        return indexes;
    }
}
