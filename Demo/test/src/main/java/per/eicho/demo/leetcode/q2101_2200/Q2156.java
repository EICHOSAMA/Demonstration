package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2156. Find Substring With Given Hash Value 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-substring-with-given-hash-value/">
 *   2156. Find Substring With Given Hash Value</a>
 */
public final class Q2156 {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        // 1. 1 <= k <= s.length <= 2 * 10^4
        // 2. 1 <= power, modulo <= 10^9
        // 3. 0 <= hashValue < modulo
        // 4. s consists of lowercase English letters only.
        // 5. The test cases are generated such that an answer always exists.
        return subStrHash(s, (long)power, (long)modulo, k, (long)hashValue);
    }

    private String subStrHash(String s, long power, long modulo, int k, long hashValue) {
        final int n = s.length();

        // 1. convert str to val array.
        final long[] vals = str2vals(s);

        // 2. calculate hash value of last window. 
        // hash(s, p, m) = (val(s[0]) * p^0 + val(s[1]) * p^1 + ... + val(s[k-1]) * p^k-1) mod m.
        long hash = 0L;
        long p = 1L;
        for (int i = n - k ; i < n ; i++) {
            hash = (hash + vals[i] * p) % modulo;
            p = (p * power) % modulo;
        }

        // p = power^k % modulo.
        int l = n - k, r = n; // [l, r)
        int firstL = n, firstR = n; 
        if (hash == hashValue) {
            firstL = l;
            firstR = r;
        }
        while (l > 0) {
            hash = (vals[--l] + (hash * power) - vals[--r] * p) % modulo;
            if (hash < 0) hash += modulo;

            if (hash == hashValue) {
                firstL = l;
                firstR = r;
            }
        }
        return s.substring(firstL, firstR);
    }

    private long[] str2vals(String s) {
        final long[] vals = new long[s.length()];
        for (int i = 0; i < s.length(); i++) vals[i] = s.charAt(i) - 'a' + 1;
        return vals;
    }

    public static void main(String[] args) {
        Q2156 q2156 = new Q2156();
        //System.out.println(q2156.subStrHash("leetcode", 7, 20, 2, 0));
        System.out.println(q2156.subStrHash("fbxzaad", 31, 100, 3, 32));
    }
}
