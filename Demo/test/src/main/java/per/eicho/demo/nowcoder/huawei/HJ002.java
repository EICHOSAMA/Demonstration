package per.eicho.demo.nowcoder.huawei;

import java.util.Objects;
import java.util.Scanner;

public final class HJ002 {
    public static void main(String[] args) {
        /*
         * 写出一个程序，接受一个由字母、数字和空格组成的字符串，
         * 和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母） 
         * 
         * e.g.
         *   input : ABCabc
         *           A
         *   output: 2
         *         
         */
        Scanner in = new Scanner(System.in);
        
        int p = 0;
        String[] inputStrs = new String[2];
        while (in.hasNextLine()) {
            inputStrs[p++] = in.nextLine();
            if (2 == p) {
                processInput(inputStrs[0], inputStrs[1]);
                p = 0;
            }
        }

        in.close();
    }

    private static void processInput(String str, String chStr) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(chStr);
        if (chStr.length() != 1) throw new IllegalArgumentException("2nd arg must be string with only one character.");
        

        char target = chStr.charAt(0);
        if (isUpperCaseCharacter(target)) target = (char)(target - 'A' + 'a');

        int count = 0;
        final int n = str.length();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (ch == target) {
                count++;
                continue;
            }

            if (isUpperCaseCharacter(ch) && target == (ch - 'A' + 'a')) count++;
        }
        System.out.println(count);
    }

    private static boolean isUpperCaseCharacter(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }
}
