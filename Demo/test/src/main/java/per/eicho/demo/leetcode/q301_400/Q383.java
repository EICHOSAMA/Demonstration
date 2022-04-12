package per.eicho.demo.leetcode.q301_400;

/**
 * <p>383. Ransom Note 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/ransom-note/">
 *   383. Ransom Note</a>
 */
public class Q383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        // 1. 1 <= ransomNote.length, magazine.length <= 10^5
        // 2. ransomNote and magazine consist of lowercase English letters.        
        if (ransomNote == null) ransomNote = "";
        if (magazine == null) magazine = "";
        if (magazine.length() < ransomNote.length()) return false;
        int[] counts = new int[26]; // 0 - 25, 'a' - 'z'
        for (int i = 0; i < ransomNote.length() ; i++) {
            counts[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length() ; i++) {
            counts[magazine.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0)
                return false;
        }
        return true;
    }
}
