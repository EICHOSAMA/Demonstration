package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ003 {

    private static final int STATE_N_NOT_READED = 0;
    private static final int STATE_N_READED = 1;
    
    public static void main(String[] args) {
        // 数据范围： 1 ≤ n ≤ 1000  ，
        // 输入的数字大小满足 1 ≤ val ≤ 500 
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int state = STATE_N_NOT_READED;
        int n = 0;
        int[] nums = null;
        int p = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (state == STATE_N_READED) {
                nums[p++] = in.nextInt();
                if (p == n) {
                    processInput(n, nums);
                    state = STATE_N_NOT_READED; // next case
                    continue;
                }
            } else {
                n = in.nextInt();
                nums = new int[n];
                p = 0;

                state = STATE_N_READED;
            }
        }

        in.close();
    }

    private static void processInput(int n, int[] nums) {
        // 请你删去其中重复的数字，即相同的数字只保留一个，
        // 把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
        boolean[] buckets = new boolean[501]; // 1 ≤ val ≤ 500 
        for (int i = 0; i < n; i++) {
            buckets[nums[i]] = true;
        }
        for (int i = 1; i < buckets.length; i++) {
            if (!buckets[i]) continue;
            System.out.println(i);
        }
    }
}
