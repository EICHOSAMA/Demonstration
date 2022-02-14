package per.eicho.demo.leetcode.q001_100;

/**
 * <p>58. Length of Last Word 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/length-of-last-word/">58. Length of Last Word</a>
 */
public final class Q58 {
    public int lengthOfLastWord(String s) {
        // 1. 1 <= s.length <= 10^4
        // 2. s consists of only English letters and spaces ' '.
        // 3. There will be at least one word in s.        
        s = s.trim();
        final int lastIndexOfSpace = s.lastIndexOf(' ');
        final int length = s.length();
        return lastIndexOfSpace == -1 ? length: length - lastIndexOfSpace - 1;
    }
}
