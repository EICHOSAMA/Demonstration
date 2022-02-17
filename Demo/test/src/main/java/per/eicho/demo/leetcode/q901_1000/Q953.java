package per.eicho.demo.leetcode.q901_1000;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>953. Verifying an Alien Dictionary 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/verifying-an-alien-dictionary/">953. Verifying an Alien Dictionary</a>
 */
public final class Q953 {
    public boolean isAlienSorted(String[] words, String order) {
        // 1. 1 <= words.length <= 100
        // 2. 1 <= words[i].length <= 20
        // 3. order.length == 26
        // 4. All characters in words[i] and order are English lowercase letters.        
        final Map<Character, Integer> weights = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            final Character ch = order.charAt(i);
            weights.put(ch, i + 1);
        }

        for (int i = 0; i < words.length - 1; i++) {
            final String wordA = words[i];
            final String wordB = words[i + 1];

            if (compare(wordA, wordB, weights) > 0) return false; 
        }

        return true;
    }

    private int compare(String wordA, String wordB, Map<Character, Integer> weights) {
        int pA = 0, pB = 0;
        while (pA < wordA.length() || pB < wordB.length()) {
            final int weightA = pA < wordA.length() ? weights.get(wordA.charAt(pA++)) : -1;
            final int weightB = pB < wordB.length() ? weights.get(wordB.charAt(pB++)) : -1;
            
            if (weightA < weightB) return -1; // wordA < wordB
            if (weightA > weightB) return 1; // wordA > wordB
        }

        return 0;
    }
}
