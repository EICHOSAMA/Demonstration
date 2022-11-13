package per.eicho.demo.nowcoder;

import java.util.Scanner;

public final class HJ046 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) { // 注意 while 处理多个 case
            final String str = in.next();
            final int k = in.nextInt();
            
            System.out.println(str.substring(0, k)); // [0, k)
        }

        in.close();
    }
}
