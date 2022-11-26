package per.eicho.demo.leetcode_cn.offer;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>剑指 Offer 38. 字符串的排列 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/">
 *   剑指 Offer 38. 字符串的排列</a>
 */
public final class Offer38 {
    public String[] permutation(String s) {
        // 1 <= s 的长度 <= 8
        final int len = s.length();
        final StringBuilder sb = new StringBuilder(8);
        final int[] counts = genCounts(s);
        final List<String> words = new LinkedList<>();

        search(len, counts, sb, words);

        return toArray(words);
    }

    private void search(int remain, int[] counts, StringBuilder sb, List<String> words) {
        if (remain == 0) {
            words.add(sb.toString());
            return;
        }

        final int pos = sb.length();
        for (int i = 0, n = counts.length; i < n; i++) {
            if (counts[i] <= 0) continue;
            final char ch = (char)(i + 'a');
            sb.append(ch);
            counts[i]--;
            search(remain - 1, counts, sb, words);
            counts[i]++;
            sb.deleteCharAt(pos);
        }
    }

    private String[] toArray(List<String> words) {
        final int n = words.size();
        final String[] result = new String[n];
        int i = 0;
        for (String word : words) {
            result[i++] = word;
        }
        return result;
    }

    private int[] genCounts(String s) {
        final int[] counts = new int[26];
        final int len = s.length();

        for (int i = 0; i < len; i++) {
            final char ch = s.charAt(i);
            counts[ch - 'a']++;
        }

        return counts;
    }
}
