package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ022 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            final int data = in.nextInt();
            if (data == 0) break;
            processInput(data);
        }
        in.close();
    }

    private static void processInput(int n) {
        // n: [1, 100]
        System.out.println(calculate(n));
    }

    private static int calculate(int n) {
        if (n <= 3) return n / 2; // 0->0 1->0 2->1 3->1
        final int num = n / 3;
        return num + calculate(num + (n % 3));
    }
}
