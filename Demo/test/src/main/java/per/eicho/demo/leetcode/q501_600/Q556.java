package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;

/**
 * <p>556. Next Greater Element III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/next-greater-element-iii/">
 *   556. Next Greater Element III</a>
 */
public final class Q556 {

    int[] workspace;
    boolean[] mark;

    int[] digits;
    int[] sortedDigits;

    public int nextGreaterElement(int n) {
        // 1. 1 <= n <= 2^31 - 1
        digits = toDigits(n);
        sortedDigits = Arrays.copyOf(digits, digits.length);
        Arrays.sort(sortedDigits);
        workspace = Arrays.copyOf(digits, digits.length);
        mark = new boolean[digits.length];

        if (!findNext(0, true)) return -1;
        final long num = toNum(workspace);
        return (num > Integer.MAX_VALUE) ? -1 : (int)num;
    }

    private long toNum(int[] digits) {
        long num = 0;
        for (int i = 0; i < digits.length; i++) {
            num = num * 10 + digits[i];
        }
        return num;
    }

    private boolean findNext(final int p, boolean firstTime) {
        if (p == digits.length) return !firstTime;
        
        if (firstTime) {
            int i;
            for (i = 0; i < sortedDigits.length; i++) {
                if (mark[i]) continue;
                if (digits[p] == sortedDigits[i]) {
                    mark[i] = true;
                    workspace[p] = sortedDigits[i];
                    if (findNext(p + 1, firstTime)) return true;
                    mark[i] = false;
                    break;
                }
            }
    
            final int digit = digits[p];
    
            for (i = i + 1; i < sortedDigits.length; i++) {
                if (mark[i]) continue;
                if (sortedDigits[i] == digit) continue;
                mark[i] = true;
                workspace[p] = sortedDigits[i];
                return findNext(p + 1, false);
            }
            return false;
        } else {
            for (int i = 0; i < sortedDigits.length; i++) {
                if (mark[i]) continue;
                mark[i] = true;
                workspace[p] = sortedDigits[i];
                return findNext(p + 1, firstTime);
            }
            return true;
        }
    }

    private int[] toDigits(int n) {
        int cnt = 1;
        int num = 1;
        while (n / num >= 10) {
            num *= 10;
            cnt++;
        }
        final int[] digits = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            digits[i] = n / num;
            n %= num;
            num /= 10;
        }

        return digits;
    }

    public static void main(String[] args) {
        Q556 q556 = new Q556();
        System.out.println(q556.nextGreaterElement(12));
        System.out.println(q556.nextGreaterElement(21));
        System.out.println(q556.nextGreaterElement(101));
    }
}
