package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>984. String Without AAA or BBB 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/string-without-aaa-or-bbb/">
 *   984. String Without AAA or BBB</a>
 */
public final class Q984 {
    public String strWithout3a3b(int a, int b) {
        // 1. 0 <= a, b <= 100
        // 2. It is guaranteed such an s exists for the given a and b.
        if (a > b) return strWithout3a3b(a, b, 'a', 'b');
        return strWithout3a3b(b, a, 'b', 'a'); 
    }

    private String strWithout3a3b(int countA, int countB, char a, char b) {
        // assert countA >= countB;
        final char[] chars = new char[countA + countB];
        int p = 0;
        while (countA > countB && countB > 0) {
            chars[p++] = a;
            chars[p++] = a;
            chars[p++] = b;
            countA -= 2;
            countB -= 1;
        }

        if (countB == 0) {
            while (countA > 0) {
                chars[p++] = a;
                countA--;
            }
        } else {
            while (countA > 0) {
                chars[p++] = a;
                chars[p++] = b;
                countA--;
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        Q984 q984 = new Q984();
        System.out.println(q984.strWithout3a3b(1, 2));
        System.out.println(q984.strWithout3a3b(7, 4));
        System.out.println(q984.strWithout3a3b(9, 4));
    }
}
