package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2062. Count Vowel Substrings of a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-vowel-substrings-of-a-string/">
 *   2062. Count Vowel Substrings of a String</a>
 */
public final class Q2062 {
    public int countVowelSubstrings(String word) {
        // 1. 1 <= word.length <= 100
        // 2. word consists of lowercase English letters only.
        
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (!isVowel(word.charAt(i))) continue;
            int mask = mask(0b00000, word.charAt(i));

            for (int j = i + 1; i < word.length(); j++) {
                if (!isVowel(word.charAt(j))) break;
                mask = mask(mask, word.charAt(j));
                if (mask == 0b11111) count++;
            }
        }
        return count;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    private int mask(int originalMask, char vowel) {
        switch (vowel) {
            case 'a': return originalMask | 0b00001;
            case 'e': return originalMask | 0b00010;
            case 'i': return originalMask | 0b00100;
            case 'o': return originalMask | 0b01000;
            default: return originalMask | 0b10000;
        }
    }
}
