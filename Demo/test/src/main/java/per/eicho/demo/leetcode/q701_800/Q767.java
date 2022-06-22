package per.eicho.demo.leetcode.q701_800;

import java.util.PriorityQueue;

/**
 * <p>767. Reorganize String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reorganize-string/">
 *   767. Reorganize String</a>
 */
public final class Q767 {

    private static final class Info {
        final char ch;
        int count;

        Info(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public String reorganizeString(String s) {
        // 1. 1 <= s.length <= 500
        // 2. s consists of lowercase English letters.
        final PriorityQueue<Info> heap = new PriorityQueue<>((i1, i2) -> {
            if (i1.count != i2.count) return Integer.compare(i2.count, i1.count); // descending.
            return Character.compare(i1.ch, i2.ch);
        });
        final int[] count = new int[26];
        final int n = s.length();
        for (int i = 0; i < n; i++) count[s.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) continue;
            final char ch = (char)(i + 'a');
            heap.offer(new Info(ch, count[i]));
        }

        final StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(' ');
        Info info = heap.poll();
        for (int i = 0; i < n; i += 2) {
            if (info.count == 0) info = heap.poll();
            sb.setCharAt(i, info.ch);
            info.count--;
        }

        for (int i = 1; i < n; i += 2) {
            if (info.count == 0) info = heap.poll();
            sb.setCharAt(i, info.ch);
            info.count--;            
        }

        for (int i = 1; i < n; i++) if (sb.charAt(i - 1) == sb.charAt(i)) return "";
        return sb.toString();
    }
}
