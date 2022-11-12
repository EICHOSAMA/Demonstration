package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;
import java.util.regex.Pattern;

public final class HJ005 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        final Pattern pattern = Pattern.compile("0x[0-9a-zA-Z]+"); 
        while (in.hasNext(pattern)) { // 注意 while 处理多个 case
            final String val = in.next(pattern).substring(2).toUpperCase();
            processInput(val);
        }

        in.close();
    }

    private static void processInput(String hexadecimalStr) {
        int result = 0;
        for (int i = 0, len = hexadecimalStr.length(); i < len; i++) {
            result = result * 16 + hex2Digit(hexadecimalStr.charAt(i));
        }
        System.out.println(result);
    }

    private static int hex2Digit(char ch) {
        if ('0' <= ch && ch <= '9') return ch - '0';
        return ch - 'A' + 10;
    }
}
