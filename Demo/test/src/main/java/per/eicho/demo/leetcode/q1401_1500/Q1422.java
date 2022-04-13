package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1422. Maximum Score After Splitting a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-score-after-splitting-a-string/">
 *   1422. Maximum Score After Splitting a String</a>
 */
public final class Q1422 {
    public int maxScore(String s) {
        // 1. 2 <= s.length <= 500
        // 2. The string s consists of characters '0' and '1' only.        
        int n = s.length();
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') count1++;
        }

        n--;
        int result = count1;
        if (s.charAt(0) == '0') {
            result++;
        } else {
            result--;
        }
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                count0++;
                result = Math.max(result, count0 + count1);
            } else {
                count1--;
            }
        }

        return result;
    }
}
