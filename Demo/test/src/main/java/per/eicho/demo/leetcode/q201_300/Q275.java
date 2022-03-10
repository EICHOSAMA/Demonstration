package per.eicho.demo.leetcode.q201_300;

/**
 * <p>274. H-Index 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/h-index/">
 *   274. H-Index</a>
 */
public final class Q275 {
    public int hIndex(int[] citations) {
        // 1. n == citations.length
        // 2. 1 <= n <= 5000
        // 3. 0 <= citations[i] <= 1000        
        final int n = citations.length;
        int previousCitation = 0;
        for (int i = 0; i < n; i++) {
            final int h = n - i;
            if (citations[i] >= h && previousCitation <= h) return h;
            previousCitation = citations[i];
        }
        return 0;
    }
}
