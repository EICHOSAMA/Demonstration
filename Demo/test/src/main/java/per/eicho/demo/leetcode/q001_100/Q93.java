package per.eicho.demo.leetcode.q001_100;

import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>93. Restore IP Addresses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/restore-ip-addresses/">93. Restore IP Addresses</a>
 */
public final class Q93 {

    private List<String> result;
    private int[] dotPositions;

    public List<String> restoreIpAddresses(String s) {
        // 1. 0 <= s.length <= 20
        // 2. s consists of digits only.
        result = new LinkedList<>();
        dotPositions = new int[4];
        search(s, 0, 3);
        return result;
    }

    private void search(final String s, int p, int remain) {
        // auto boxing

        if (remain == 0) {
            // check
            final int l = dotPositions[3];
            final int r = s.length();

            // length too long
            if (r - l > 3) return;
            if (l >= s.length()) return;

            // leading zero case
            if (s.charAt(l) == '0' && r - l != 1) return;

            int num = 0;
            for (int i = l; i < r; i++) {
                final int ch = s.charAt(i) - '0';

                num *= 10;
                num += ch;
            }

            // Each integer is between 0 and 255 (inclusive)
            if (num > 255) return;

            StringBuilder sb = new StringBuilder();
            int j = 1;
            for (int i = 0; i < s.length(); i++) {
                if (j < 4 && i == dotPositions[j]) {
                    sb.append('.');
                    j++;
                }
                sb.append((int)(s.charAt(i) - '0'));
            }
            result.add(sb.toString());
            System.out.println(sb.toString());
            return;
        }

        // assert remain > 0;
        final int index = 4 - remain;
        final int lastPosition = dotPositions[index - 1];

        if (lastPosition >= s.length()) return;

        final int maxLength = s.charAt(lastPosition) == '0' ? 1 : 3;

        int num = 0;
        for (int len = 1; len <= maxLength; len++) {
            final int idx = lastPosition + len - 1;
            if (idx >= s.length()) return;

            num *= 10;
            num += (s.charAt(idx) - '0');
            // Each integer is between 0 and 255 (inclusive)
            if (num > 255) break; 

            dotPositions[index] = lastPosition + len;
            search(s, p + len, remain - 1);
            dotPositions[index] = 0;
        }
    }

    public static void main(String[] args) {
        Q93 q93 = new Q93();
        List<String> result = q93.restoreIpAddresses("1111");

        OutputUtils.println(result);
    }
}
