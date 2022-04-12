package per.eicho.demo.leetcode.q301_400;

/**
 * <p>345. Reverse Vowels of a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-vowels-of-a-string/">
 *   345. Reverse Vowels of a String</a>
 */
public class Q345 {
    public String reverseVowels(String s) {
        // 1. 1 <= s.length <= 3 * 10^5
        // 2. s consist of printable ASCII characters.
        //a i u e o
        char[] result = s.toCharArray();
        s = null;
        int l = 0, r = result.length - 1;
        while (l < r) {
            while (isVowels(result[l]) == false && l < r) l++;
            while (isVowels(result[r]) == false && r > l) r--;

            if (l != r) {
                // switch.
                result[l] ^= result[r];
                result[r] ^= result[l];
                result[l] ^= result[r];
                l++;
                r--;
            }
        }
        return String.valueOf(result);
    }

    private boolean isVowels(char ch) {
        ch = Character.toLowerCase(ch);
        switch (ch) {
            case 'a':
            case 'i':
            case 'u':
            case 'e':
            case 'o':
                return true;
            default:
                return false;
        }
    }
}
