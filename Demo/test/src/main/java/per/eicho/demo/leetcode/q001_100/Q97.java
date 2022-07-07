package per.eicho.demo.leetcode.q001_100;

/**
 * <p>97. Interleaving String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/interleaving-string/">97. Interleaving String</a>
 */
public final class Q97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        // 1. 0 <= s1.length, s2.length <= 100
        // 2. 0 <= s3.length <= 200
        // 3. s1, s2, and s3 consist of lowercase English letters.
        final int len1 = s1.length();
        final int len2 = s2.length();
        final int len3 = s3.length();
        
        if (len1 + len2 != len3) return false;
        
        final boolean f[] = new boolean[len2 + 1];
        
        f[0] = true;
        for (int j = 1; j < len2; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                f[j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= len1; i++) {
            f[0] = f[0] && s1.charAt(i - 1) == s3.charAt(i - 1);

            for (int j = 1; j <= len2; j++) {
                final int k = i + j - 1;
                f[j] = (f[j] && s1.charAt(i - 1) == s3.charAt(k)) || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(k));
            }
        }
        return f[len2];
    }
}
