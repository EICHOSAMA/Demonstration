package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ020 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            processInput(in.nextLine());
        }

        in.close();
    }

    private static void processInput(String password) {
        // 密码要求:
        // 1.长度超过8位
        // 2.包括大小写字母.数字.其它符号,以上四种至少三种
        // 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）  
        if (password.length() <= 8) {
            System.out.println("NG");
            return;
        }

        if (!containsMoreThan3TypesOfChar(password)) {
            System.out.println("NG");
            return;
        }

        if (containsDuplicateSubString(password)) {
            System.out.println("NG");
            return;
        }

        System.out.println("OK");
    }

    private static boolean containsDuplicateSubString(String password) {
        // May be KMP is useful.
        for (int i = 0, bound = i - 2; i < bound; i++) {
            for (int j = i + 2, len = j - i + 1; j + len - 1 < password.length() ; j++, len = j - i + 1) {
                boolean isEqual = true;
                for (int k = 0; k < len; k++) {
                    if (password.charAt(i + k) != password.charAt(j + k)) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) return true;
            }
        }
        return false;
    }

    private static boolean containsMoreThan3TypesOfChar(String password) {
        int mark = 0b0000;
        for (int i = 0; i < password.length(); i++) {
            final char ch = password.charAt(i);
            if (isDigit(ch)) {
                mark |= 0b0001;
            } else if (isLowerCase(ch)) {
                mark |= 0b0010;
            } else if (isUpperCase(ch)) {
                mark |= 0b0100;
            } else {
                mark |= 0b1000;
            }
        }
        mark ^= 0b1111;
        mark -= (mark & (-mark));
        return mark == 0;
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private static boolean isUpperCase(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }

    private static boolean isLowerCase(char ch) {
        return 'a' <= ch && ch <= 'z';
    }
}
