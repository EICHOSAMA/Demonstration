package per.eicho.demo.leetcode.q1501_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1592. Rearrange Spaces Between Words 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rearrange-spaces-between-words/">
 *   1592. Rearrange Spaces Between Words</a>
 */
public final class Q1592 {
    public String reorderSpaces(String text) {
        // 1. 1 <= text.length <= 100
        // 2. text consists of lowercase English letters and ' '.
        // 3. text contains at least one word.
        int spaceCnt = 0;
        List<String> words = new ArrayList<>();
        final int n = text.length();
        final StringBuilder wordSb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            final char ch = text.charAt(i);
            if (ch == ' ') {
                spaceCnt++;
                if (wordSb.length() != 0) {
                    words.add(wordSb.toString());
                    wordSb.setLength(0);
                }
            } else {
                wordSb.append(ch);
            }
        }
        if (wordSb.length() != 0) {
            words.add(wordSb.toString());
        }
        
        final StringBuilder sb = new StringBuilder();
        final int spaceCntBetweenWord;
        int extraSpaceCnt;
        if (words.size() > 1) {
            spaceCntBetweenWord = spaceCnt / (words.size() - 1);
            extraSpaceCnt = spaceCnt % (words.size() - 1);
        } else {
            spaceCntBetweenWord = 0;
            extraSpaceCnt = spaceCnt;
        }

        sb.append(words.get(0));
        for (int i = 1; i < words.size(); i++) {
            for (int j = 0; j < spaceCntBetweenWord; j++) sb.append(' ');
            sb.append(words.get(i));
        }

        while (extraSpaceCnt != 0) {
            sb.append(' ');
            extraSpaceCnt--;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Q1592 q1592 = new Q1592();
        System.out.println("[" + q1592.reorderSpaces("  hello") + "]");
    }
}
