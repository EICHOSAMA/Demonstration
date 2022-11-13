package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public final class HJ023 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            final String str = in.next();
            processInput(str);
        }

        in.close();
    }

    private static void processInput(String str) {
        // 保证输入的字符串中仅出现小写字母
        int[] counts = new int[26];
        Arrays.fill(counts, Integer.MAX_VALUE);
        int minCount = Integer.MAX_VALUE;
        final int len = str.length();

        for (int i = 0; i < len; i++) {
            final int ch = str.charAt(i) - 'a'; 
            if (counts[ch] == Integer.MAX_VALUE) {
                counts[ch] = 1;
            } else {
                counts[ch]++;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < minCount) {
                minCount = counts[i];
            }
        }

        final StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            final char ch = str.charAt(i);
            if (counts[ch - 'a'] == minCount) continue;
            sb.append(ch);
        }

        System.out.println(sb.toString());
    }
}
