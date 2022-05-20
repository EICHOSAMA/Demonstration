package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>451. Sort Characters By Frequency 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-characters-by-frequency/">
 *   451. Sort Characters By Frequency</a>
 */
public final class Q451 {
    public String frequencySort(String s) {
        // 1. 1 <= s.length <= 5 * 10^5
        // 2. s consists of uppercase and lowercase English letters and digits.
        // [A-Z] 65-90, [a-z] 97-122, [0-9] 48-57
        final int offset = '0';
        final int n = s.length();
        int[][] counts = new int['z' - '0' + 1][2];
        for (int i = 0; i < counts.length; i++) counts[i][0] = i + offset;

        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);
            final int idx = ch - offset;
            counts[idx][1]++;
        }

        Arrays.sort(counts, (c1, c2) -> {
            if (c1[1] != c2[1]) return Integer.compare(c2[1], c1[1]); // descending.
            return Integer.compare(c1[0], c2[0]);
        });

        final StringBuilder sb = new StringBuilder();

        for (int[] count : counts) {
            final int size = count[1]; 
            if (size == 0) continue;
            final char ch = (char)count[0];
            for (int i = 0; i < size; i++) sb.append(ch);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println((int)'A'); // 65
        System.out.println((int)'Z'); // 90
        
        System.out.println((int)'a'); // 97 
        System.out.println((int)'z'); // 122

        System.out.println((int)'0'); // 48
        System.out.println((int)'9'); // 57

        
    }
}
