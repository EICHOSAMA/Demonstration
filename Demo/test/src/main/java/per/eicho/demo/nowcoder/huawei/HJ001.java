package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ001 {
   
    public static void main(String[] args) {
        /*
         * 计算字符串最后一个单词的长度，单词以空格隔开，
         * 字符串长度小于5000。（注：字符串末尾不以空格为结尾）
         */
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int lenOfLastWord = 0;
        while (in.hasNext()) { // 注意 while 处理多个 case
            String str = in.next();
            for (int p = str.length() - 1;;) {
                while (p >= 0 && str.charAt(p) != ' ') p--;
                lenOfLastWord = str.length() - p - 1;
                break;
            }
        }
        System.out.println(lenOfLastWord);

        in.close();
    }
}
