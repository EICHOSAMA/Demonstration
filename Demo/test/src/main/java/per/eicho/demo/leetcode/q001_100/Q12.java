package per.eicho.demo.leetcode.q001_100;

/**
 * <p>12. Integer to Roman 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/integer-to-roman/">12. Integer to Roman</a>
 */
public final class Q12 {
    private static int[] numbers = new int[]{1,4,5,9,10,40,50,90,100,400,500,900,1000}; // 0 - 12
    private static String[] literials = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public static String intToRoman(int num) {
        // num in [1, 3999];
        StringBuilder sb = new StringBuilder();
        int max;
        while (num != 0) {
            max = find(num, 0, 12);
            sb.append(literials[max]);
            num -= numbers[max];
        }

        return sb.toString();
    }

    public static int find(int num, int l, int r) {
        if (num < numbers[l])
            return l - 1;
        if (num >= numbers[r])
            return r;

        int i = (l + r + 1) / 2;
        int number = numbers[i];
        if (number == num)
            return  i;

        if (number < num) {
            return find(num, i, r);
        } else {
            // number > num
            return find(num, l, i - 1);
        }
    }
}
