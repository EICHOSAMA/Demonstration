package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public final class HJ101 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = -1;
        int[] nums = null;
        int p = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (n == -1) {
                n = in.nextInt();
                nums = new int[n];
            } else if (p < n) {
                nums[p++] = in.nextInt();
            } else {
                final int sortMode = in.nextInt();
                processInput(n, nums, sortMode);
                
                n = -1;
                nums = null;
                p = 0;
            }
        }

        in.close();
    }

    private static void processInput(int n, int[] nums, int mode) {
        // Mode: 0代表升序排序，1代表降序排序
        Arrays.sort(nums);
        if (mode == 1) {
            final int half = n / 2;
            for (int l = 0, r = n - 1 ; l < half; l++, r--) {
                nums[l] ^= nums[r];
                nums[r] ^= nums[l];
                nums[l] ^= nums[r];
            }
        }

        println(n, nums);
    }

    private static void println(int n, int[] nums) {
        for (int i = 0; ; ) {
            System.out.print(nums[i]);
            if (++i < n) {
                System.out.print(' ');
            } else {
                break;
            }
        }
    }

}
