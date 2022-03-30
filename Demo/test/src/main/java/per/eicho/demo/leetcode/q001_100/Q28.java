package per.eicho.demo.leetcode.q001_100;

/**
 * <p>28. Implement strStr() 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-strstr/">28. Implement strStr()</a>
 */
public final class Q28 {
    public int strStr(String haystack, String needle) {
        // 1. 0 <= haystack.length, needle.length <= 5 * 10^4
        // 2. haystack and needle consist of only lower-case English characters.
        if (needle.length() == 0) return 0;

        final int len1 = haystack.length();
        final int len2 = needle.length();
        int l = 0, r = 0; // [l, r), l inclusive, r exclusive.
        int hashCodeOfNeedle = 0;
        for (int i = 0; i < len2; i++) {
            hashCodeOfNeedle += needle.charAt(i) * needle.charAt(i);
        }
        int hashCodeOfLR = 0;
        while (r < len1) {
            int ch = haystack.charAt(r++); // keep r is exclusive.
            hashCodeOfLR += ch * ch; // include r th element
            if (r - l >= len2) {
                if (hashCodeOfLR == hashCodeOfNeedle) {
                    boolean isEqual = true;
                    for (int i = l; i < r; i++) {
                        if (haystack.charAt(i) != needle.charAt(i-l)) {
                            isEqual = false;
                            break;
                        }
                    }
                    if (isEqual == true) return l;
                }

                hashCodeOfLR -= haystack.charAt(l) * haystack.charAt(l);// exclude l th element
                l++;
            }
        }
        return -1;
    }
}
