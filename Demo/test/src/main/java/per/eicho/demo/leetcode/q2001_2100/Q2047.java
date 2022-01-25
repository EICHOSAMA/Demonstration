package per.eicho.demo.leetcode.q2001_2100;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>2047. Number of Valid Words in a Sentence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-valid-words-in-a-sentence/">
 *   2047. Number of Valid Words in a Sentence</a>
 */
public final class Q2047 {
    public int countValidWords(String sentence) {
        // 1. 1 <= sentence.length <= 1000
        // 2. sentence only contains lowercase English letters, digits, ' ', '-', '!', '.', and ','.
        // 3. There will be at least 1 token.
        int count = 0;
        final String[] tokens = sentence.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            final String token = tokens[i];
            if (token.length() == 0) continue;
            if (isValidWord(token)) count++;
        }

        return count;
    }

    private boolean isValidWord(String token) {
        // A token is a valid word if all three of the following are true:
        //   1. It only contains lowercase letters, hyphens, and/or punctuation (no digits).
        //   2. There is at most one hyphen '-'. If present, it must be surrounded by lowercase characters ("a-b" is valid, but "-ab" and "ab-" are not valid).
        //   3. There is at most one punctuation mark. If present, it must be at the end of the token ("ab,", "cd!", and "." are valid, but "a!b" and "c.," are not valid).
        // Examples of valid words include "a-b.", "afad", "ba-c", "a!", and "!".
        int hyphenCount = 0;
        if (token.charAt(0) == '-' || token.charAt(token.length() - 1) == '-') return false;

        for (int i = 0; i < token.length(); i++) {
            final char ch = token.charAt(i);
            if ('0' <= ch && ch <= '9') return false;
            if (ch == '-') {
                if (hyphenCount >= 1) return false;
                hyphenCount++;
                if (!isLowercaseLetter(token.charAt(i - 1)) || !isLowercaseLetter(token.charAt(i + 1))) return false;
                continue;
            }

            if (ch == '!' || ch == '.' || ch == ',') {
                if (i != token.length() - 1) return false;        
                continue;
            }
        }
        return true;
    }

    private boolean isLowercaseLetter(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    public static void main(String[] args) {
        String sentence = "cat and  dog";
        OutputUtils.println(sentence.split(" "));
    }
}
