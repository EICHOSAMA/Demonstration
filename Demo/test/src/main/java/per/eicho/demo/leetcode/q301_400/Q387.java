package per.eicho.demo.leetcode.q301_400;

/**
 * <p>387. First Unique Character in a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/first-unique-character-in-a-string/">
 *   387. First Unique Character in a String</a>
 */
public class Q387 {
    public int firstUniqChar(String s) {
        // 1. 1 <= s.length <= 10^5
        // 2. s consists of only lowercase English letters.        
        boolean[] appeared = new boolean[26]; // 0 - 25 , 'a' - 'z', init value is false.
        int[] firstIndices = new int[26]; // the same.
        int totalCount = 26;
        for (int i = 0; i < 26; i++) {
            firstIndices[i] = -1;
        }

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (appeared[index] == true) {
                if (firstIndices[index] != -1) {
                    firstIndices[index] = -1;
                    totalCount--;
                    if (totalCount == 0) {
                        return -1; // optimize
                    }
                }
            } else {
                appeared[index] = true;
                firstIndices[index] = i;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            int index = firstIndices[i];
            if (index != -1 && index < result)
                result = index;
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
