package per.eicho.demo.leetcode.q701_800;

import java.util.Arrays;

/**
 * <p>748. Shortest Completing Word 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-completing-word/">748. Shortest Completing Word</a>
 */
public final class Q748 {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        // 1. Process lincensePlate into timeInfo & charNum
        final int[] timeInfo = new int[26];
        final int charNum = processLicensePlate(licensePlate, timeInfo);

        // 2. main process
        int len = Integer.MAX_VALUE;
        String result = null;
        
        for (String word : words) {
            if (!isCompletingWord(word, Arrays.copyOf(timeInfo, timeInfo.length), charNum)) continue;

            if (word.length() < len) {
                result = word;
                len = word.length();
            }
        } 

        return result;
    }

    private boolean isCompletingWord(final String word, final int[] timeInfoCpy, final int charNum) {
        // consists of lower case English letters.
        int remain = charNum;

        for (int i = 0; i < word.length(); i++) {
            final int ch = word.charAt(i);
            final int index = ch - 'a';

            timeInfoCpy[index]--;

            if (timeInfoCpy[index] == 0) {
                remain--;

                if (remain <= 0) return true; 
            }
        }

        return false;
    } 

    private int processLicensePlate(String licensePlate, final int[] timeInfo) {
        int characterCount = 0;

        for (int i = 0; i < licensePlate.length(); i++) {
            final int ch = licensePlate.charAt(i);

            if ('a' <= ch && ch <= 'z') {
                int index = ch - 'a';
                timeInfo[index]++;

                // for each character count only once
                if (timeInfo[index] == 1) characterCount++; 
            } else if ('A' <= ch && ch <= 'Z') {
                int index = ch - 'A';
                timeInfo[index]++;

                // for each character count only once
                if (timeInfo[index] == 1) characterCount++; 
            }
        }

        return characterCount;
    }
}
