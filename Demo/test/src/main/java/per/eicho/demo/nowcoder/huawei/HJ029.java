package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ029 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String[] strs = new String[2];
        int row = 0;
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            strs[row++] = in.nextLine();
            if (row == 2) {
                processInput(strs[0], strs[1]);
                row = 0;
            }
        }

        in.close();
    }

    private static void processInput(String str2enc, String str2dec) {
        System.out.println(encrypt(str2enc));
        System.out.println(decrypt(str2dec));
    }

    private static String encrypt(String str2enc) {
        final int n = str2enc.length();
        final StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            final char ch = str2enc.charAt(i);
            sb.append(encrypt(ch));
        }
        return sb.toString();
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private static boolean isUpperCase(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }

    private static char encrypt(char ch) {
        if (isDigit(ch)) return (char)(((int)ch - '0' + 1) % 10 + '0');
        if (isUpperCase(ch)) {
            ch = (char)(ch + 1);
            if (ch > 'Z') ch = 'A';
            ch = toLowerCase(ch);
        } else {
            ch = (char)(ch + 1);
            if (ch > 'z') ch = 'a';
            ch = toUpperCase(ch);
        }
        return ch;
    }

    private static char toUpperCase(char ch) {
        // to upper case
        ch = (char)(ch - 'a' + 'A');
        return ch;
    }

    private static char toLowerCase(char ch) {
        // to lower case
        ch = (char)(ch - 'A' + 'a');
        return ch;
    }

    private static char decrypt(char ch) {
        if (isDigit(ch)) return (char)(((int)ch - '0' - 1 + 10) % 10 + '0');
        if (isUpperCase(ch)) {
            ch = (char)(ch - 1);
            if (ch < 'A') ch = 'Z';
            ch = toLowerCase(ch);
        } else {
            ch = (char)(ch - 1);
            if (ch < 'a') ch = 'z';
            ch = toUpperCase(ch);
        }
        return ch;
    }

    private static String decrypt(String str2dec) {
        final int n = str2dec.length();
        final StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            final char ch = str2dec.charAt(i);
            sb.append(decrypt(ch));
        }
        return sb.toString();
    }
}
