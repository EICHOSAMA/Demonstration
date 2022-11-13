package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public final class HJ027 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int n = -1;
        String[] strs = null;
        String x = null;
        int k = -1;

        while (in.hasNextLine()) { // 注意 while 处理多个 case
            final String line = in.nextLine();
            final String[] parts = line.split("\\s");

            n = Integer.valueOf(parts[0]);
            if (parts.length != (1 + n + 1 + 1)) continue;
            strs = Arrays.copyOfRange(parts, 1, 1 + n);
            x = parts[1 + n];
            k = Integer.valueOf(parts[1 + n + 1]);

            processInput(n, strs, x, k);
        }
        
        in.close();
    }

    private static void processInput(int n, String[] strs, String x, int k) {
        Arrays.sort(strs);
        String kthBro = null;
        final int[] countInfo4X = getCountInfo(x);
        int count = 0;
        for (int i = 0; i < n; i++) {
            final String str = strs[i];
            if (isBrotherWord(str, x, countInfo4X)) {
                if (++count == k) { kthBro = str; }
            }
        }

        System.out.println(count);
        System.out.println(kthBro == null ? "" : kthBro);
    }

    private static int[] getCountInfo(String x) {
        final int[] counts = new int[26];
        for (int i = 0; i < x.length(); i++) {
            // 描述没写：默认测试用例全小写，去尝试过用例
            counts[x.charAt(i) - 'a']++;
        }
        return counts;
    }

    private static boolean isBrotherWord(String str, String x, int[] countInfo4X) {
        if (str.length() != x.length()) return false;
        if (str.equals(x)) return false;

        final int[] countInfo4Str = getCountInfo(str);
        for (int i = 0; i < 26; i++) {
            if (countInfo4Str[i] != countInfo4X[i]) return false;
        }

        return true;
    }
}
