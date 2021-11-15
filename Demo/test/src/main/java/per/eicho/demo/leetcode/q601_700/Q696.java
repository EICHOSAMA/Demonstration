package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>696. Count Binary Substrings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-binary-substrings/">696. Count Binary Substrings</a>
 */
public final class Q696 {
    public int countBinarySubstrings(String s) {
        final int size = s.length();
        final List<Integer> counts = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            final char c = s.charAt(i);
            int count = 1;
            while (i + 1 < size && s.charAt(i + 1) == c) {
                count++;
                i++;
            }
            counts.add(count);
        }

        int result = 0;
        for (int i = 0; i < counts.size() - 1; i++) {
            result += Math.min(counts.get(i), counts.get(i + 1));
        }
        return result;
    }

    public static void main(String[] args) {
        Q696 q696 = new Q696();

        int result = q696.countBinarySubstrings("00110");
        System.out.println(result);
    }
}
