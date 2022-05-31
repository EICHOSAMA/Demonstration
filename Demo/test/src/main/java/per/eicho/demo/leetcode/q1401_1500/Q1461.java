package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1461. Check If a String Contains All Binary Codes of Size K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/">
 *   1461. Check If a String Contains All Binary Codes of Size K</a>
 */
public final class Q1461 {
    public boolean hasAllCodes(String s, int k) {
        // 1. 1 <= s.length <= 5 * 10^5
        // 2. s[i] is either '0' or '1'.
        // 3. 1 <= k <= 20
        final int n = s.length();
        final int cnt = power(2, k);
        final int mask = cnt - 1; // 0b111111...1
        if (n - k + 1 < cnt) return false;
        final boolean[] marks = new boolean[cnt];

        int r = 1; // [l, r)
        int idx = s.charAt(0) - '0'; // 0 or 1
        while (r < k) idx = (idx << 1) | (s.charAt(r++) - '0');
        marks[idx] = true;
        
        while (r < n) {
            idx = ((idx << 1) & mask) | (s.charAt(r++) - '0');
            marks[idx] = true;
        }

        for (boolean mark : marks) if (!mark) return false;
        return true;
    }

    private int power(int a, int b) {
        if (b == 1) return 2;
        final int half = power(a, b / 2);
        return half * half * (b % 2 == 1 ? 2 : 1);
    }

    public static void main(String[] args) {
        Q1461 q1461 = new Q1461();
        System.out.println(q1461.hasAllCodes("00110110", 2));
    }
}
