package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ065 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            final String str1 = in.nextLine();
            final String str2 = in.nextLine();
            processInput(str1, str2);
        }

        in.close();
    }

    private static final int IDX_LEFT = 0;
    private static final int IDX_RIGHT = 1;
    private static final int IDX_VALUE = 2;

    private static void processInput(String str1, String str2) {
        if (str1.length() > str2.length()) {
            processInput(str2, str1);
            return;
        }

        final int bound = str1.length();
        int[] maxLenWindow = new int[]{0, 0, 0};
        final int[] sums1 = getSums(str1);
        final int[] sums2 = getSums(str2);
        // 枚举滑动窗口大小。
        for (int windowSize = 1; windowSize <= bound; windowSize++) {
            // 创建滑动窗口

            final int[] window1 = genWindow(str1, windowSize);
            outer: for (;;) {
                final int[] window2 = genWindow(str2, windowSize);
                for (;;) {
                    if (isEqual(window1, window2, str1, str2)) {
                        System.arraycopy(window1, 0, maxLenWindow, 0, window1.length);
                        break outer;
                    }

                    if (window2[IDX_RIGHT] < str2.length()) {
                        move2Right(window2, str2, sums2);
                    } else {
                        break;
                    }   
                }

                if (window1[IDX_RIGHT] < str1.length()) {
                    move2Right(window1, str1, sums1);
                } else {
                    break;
                }
            }
        }

        if (maxLenWindow[IDX_RIGHT] - maxLenWindow[IDX_LEFT] == 0) {
            System.out.println();
        } else {
            System.out.println(str1.substring(maxLenWindow[IDX_LEFT], maxLenWindow[IDX_RIGHT]));
        }
    }

    private static boolean isEqual(int[] window1, int[] window2, String str1, String str2) {
        if (window1[IDX_VALUE] != window2[IDX_VALUE]) return false;
        int p1 = window1[IDX_LEFT];
        int p2 = window2[IDX_LEFT];
        while (p1 < window1[IDX_RIGHT]) {
            if (str1.charAt(p1++) != str2.charAt(p2++)) return false;
        }
        return true;
    }

    private static void move2Right(int[] window, String str, int[] sums) {
        window[IDX_VALUE] -= (sums[window[IDX_RIGHT]] - sums[window[IDX_LEFT]]);
        window[IDX_LEFT]++;
        final char ch = str.charAt(window[IDX_RIGHT]++);
        window[IDX_VALUE] += (window[IDX_RIGHT] - window[IDX_LEFT]) * ch;
    }

    private static int[] genWindow(String str1, int windowSize) {
        int[] window = new int[]{0, 0, 0}; // [l, r)
        while (window[IDX_RIGHT] < windowSize) {
            final int idx = window[IDX_RIGHT]++;
            final char ch = str1.charAt(idx);
            window[IDX_VALUE] += (idx + 1) * ch;
        }
        return window;
    }

    private static int[] getSums(String str) {
        final int n = str.length();
        final int[] sums = new int[n + 1];
        int p = 0;
        int sum = 0;
        do {
            sums[p] = sum;
            sum += str.charAt(p++);
        } while (p < n);
        sums[p] = sum;
        return sums;
    }
}
