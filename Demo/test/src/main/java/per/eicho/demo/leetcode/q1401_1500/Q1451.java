package per.eicho.demo.leetcode.q1401_1500;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * <p>1451. Rearrange Words in a Sentence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rearrange-words-in-a-sentence/">
 *   1451. Rearrange Words in a Sentence</a>
 */
public final class Q1451 {
    public String arrangeWords(String text) {
        // 1. text begins with a capital letter and then contains
        //    lowercase letters and single space between words.
        // 2. 1 <= text.length <= 10^5
        // <Len, List<Start Idx>>
        final TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        final int n = text.length();
        int l = 0, r = 0; // [l, r)
        while (r < n) {     
            while (r < n && text.charAt(r) != ' ') r++;

            final int len = r - l;
            if (!treeMap.containsKey(len)) treeMap.put(len, new ArrayList<>());
            treeMap.get(len).add(l);

            l = r + 1;
            r = l;
        }

        final StringBuilder sb = new StringBuilder(text.length());
        while (!treeMap.isEmpty()) {
            final int len = treeMap.firstKey();
            final List<Integer> idxList = treeMap.pollFirstEntry().getValue();
            
            for (int i = 0, size = idxList.size(); i < size; i++) {
                final int startIdx = idxList.get(i);

                // 'a' : 97
                // 'A' : 65
                final char firstCh = text.charAt(startIdx);
                sb.append(firstCh >= 'a' ? firstCh : (char)(firstCh + 32));
                for (int j = startIdx + 1, bound = startIdx + len - 1; j <= bound; j++) {
                    sb.append(text.charAt(j));
                }
                
                if (treeMap.isEmpty() && i == size - 1) continue;
                sb.append(' ');
            }
        }

        final char firstCh = sb.charAt(0);
        sb.setCharAt(0, firstCh <= 'Z' ? firstCh : (char)(firstCh - 32));
        return sb.toString();
    }

    public static void main(String[] args) {
        Q1451 q1451 = new Q1451();
        System.out.println(q1451.arrangeWords("Leetcode is cool"));
    }
}
