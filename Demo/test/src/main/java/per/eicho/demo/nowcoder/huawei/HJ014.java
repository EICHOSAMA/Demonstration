package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public final class HJ014 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            final int n = in.nextInt();
            final String[] strs = new String[n];
            int p = 0;
            while (p < n && in.hasNext()) {
                strs[p++] = in.next();
            }

            processInput(n, strs);
        }

        in.close();
    }

    private static void processInput(int n, String[] strs) {
        Arrays.sort(strs);
        print(strs);
    }

    private static void print(String[] strs) {
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
