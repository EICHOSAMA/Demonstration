package per.eicho.demo.nowcoder.huawei;

import java.util.Scanner;

public final class HJ042 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLong()) { // 注意 while 处理多个 case
            final long n = in.nextLong();
            processInput(n);
        }

        in.close();
    }

    private static void processInput(long n) {
        // 具体规则如下:
        //   1.在英语读法中三位数字看成一整体，后面再加一个计数单位。
        //     从最右边往左数，三位一单位，例如12,345 等
        //   2.每三位数后记得带上计数单位 分别是thousand, million, billion.
        //   3.公式：
        //     - 百万以下千以上的数 X thousand X, 
        //     - 10亿以下百万以上的数：X million X thousand X, 
        //     - 10 亿以上的数：X billion X million X thousand X. 
        //     每个X分别代表三位数或两位数或一位数。
        //   4.在英式英语中百位数和十位数之间要加and，美式英语中则会省略，
        //   我们这个题目采用加上and，百分位为零的话，这道题目我们省略and
        if (n == 0L) {
            System.out.println(WORDS_OF_0_TO_19_STEP_1[0]);
        } else {
            System.out.println(transform(n));
        }
    }

    private static final long GROUP_LEVEL_UNIT_BILLION = 1_000_000_000L;
    private static final long GROUP_LEVEL_UNIT_MILLION = 1_000_000L;
    private static final long GROUP_LEVEL_UNIT_THOUSAND = 1_000L;
    private static final long[] GROUP_LEVEL_UNITS = new long[]{
        GROUP_LEVEL_UNIT_BILLION, GROUP_LEVEL_UNIT_MILLION, GROUP_LEVEL_UNIT_THOUSAND};
    private static final String[] GROUP_LEVEL_UNIT_NAMES = new String[]{"billion", "million", "thousand"};
    private static final String[] WORDS_OF_0_TO_19_STEP_1 = new String[]{
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    private static final String[] WORDS_OF_20_TO_90_STEP_10 = new String[] {
        "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static String transform(long n) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < GROUP_LEVEL_UNITS.length; i++) {
            final long unit = GROUP_LEVEL_UNITS[i];

            if (n >= unit) { 
                sb.append(transform(n / unit)).append(' ')
                  .append(GROUP_LEVEL_UNIT_NAMES[i]).append(' ');
                n %= unit;
            }
        }

        if (n >= 100) {
            sb.append(WORDS_OF_0_TO_19_STEP_1[(int)(n / 100)]).append(' ')
              .append("hundred").append(' ');
            n %= 100;
            if (n != 0) sb.append("and").append(' ');   
        }

        if (n >= 20) {
            sb.append(WORDS_OF_20_TO_90_STEP_10[(int)((n / 10) - 2)]).append(' ');
            n %= 10;
            if (n != 0) sb.append(WORDS_OF_0_TO_19_STEP_1[(int)n]).append(' ');
        } else if (n > 0) {
            sb.append(WORDS_OF_0_TO_19_STEP_1[(int)n]).append(' ');
        }

        sb.deleteCharAt(sb.length() - 1); // remove last ' '
        return sb.toString(); 
    }
}
