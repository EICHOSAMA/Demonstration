package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ033 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            final String input = in.nextLine();
            processInput(input);
        }

        in.close();
    }

    private static void processInput(String input) {
        final String[] parts = input.split("\\.");

        if (parts.length == 1) {
            // to IP Address
            System.out.println(toIPAddress(Long.valueOf(parts[0])));
        } else {
            // to Long
            System.out.println(toInt(parts));
        }
    }

    private static String toIPAddress(long num) {
        final long mask = 0xFF;
        final StringBuilder sb = new StringBuilder(3 * 4 + 3);
        final int[] parts = new int[4];
        int p = 3;
        while (p >= 0) {
            parts[p--] = (int)(num & mask);
            num >>= 8;
        }

        for (int i = 0, n = parts.length; i < n; ) {
            sb.append(parts[i]);
            if (++i < n) sb.append('.');
        }
        return sb.toString();
    }

    private static long toInt(String[] parts) {
        long num = 0;
        for (String part : parts) {
            final int iPart = Integer.valueOf(part);
            num <<= 8;
            num ^= iPart;
        }
        return num;
    }
}
