package per.eicho.demo.leetcode.q001_100;

/**
 * <p>76. Minimum Window Substring 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-window-substring/">76. Minimum Window Substring</a>
 */
public final class Q76 {

    /**
     * time complexity  : O(n + m) 
     * space complexity : O(n + m)
     */
    public String minWindow(String s, String t) {
        
        // 1. process String t to get [appearnce] & [count] info.
        final int[] appearnce = new int['z' - 'A' + 1];
        int count = 0;
        for (int i = 0; i < t.length(); i++) {
            final int index = t.charAt(i) - 'A'; 
            appearnce[index]++;

            if (appearnce[index] == 1) count++; // each letter count only once
        }

        final int length = s.length();
        int minWindowsLen = Integer.MAX_VALUE;
        String result = "";
        int l = 0, r = -1;
        while (true) {
            int index;

            // 1. move Right
            while (count > 0) {
                r++;
                if (r == length) return result;
                index = s.charAt(r) - 'A';
                appearnce[index]--;
                if (appearnce[index] == 0) count--;
            }
            assert count == 0;

            // 2. move Left
            while (count == 0) {
                index = s.charAt(l) - 'A';
                appearnce[index]++;
                if (appearnce[index] > 0) count++;
                l++;
                if (l == length) break;
            }
            assert count != 0;

            // 3. compare s.subString[l - 1, r] (both inclusive)
            if (r - l < minWindowsLen) {
                minWindowsLen = r - l;
                result = s.substring(l - 1, r + 1);
            }

            if (l == length) return result;
        }
    }

    public static void main(String[] args) {
        Q76 q76 = new Q76();
        String res = q76.minWindow("a" ,"a");
        System.out.println(res);

    }
}
