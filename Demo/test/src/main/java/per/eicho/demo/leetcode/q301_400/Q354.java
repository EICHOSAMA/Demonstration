package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>354. Russian Doll Envelopes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/russian-doll-envelopes/">
 *   354. Russian Doll Envelopes</a>
 */
public final class Q354 {

    private static final int WIDTH = 0;
    private static final int HEIGHT = 1;    

    public int maxEnvelopes(int[][] envelopes) {
        // 1. 1 <= envelopes.length <= 10^5
        // 2. envelopes[i].length == 2
        // 3. 1 <= wi, hi <= 10^5
        final int n = envelopes.length; 
        
        // 1. sort (key1 width [0], key2 height [1])
        sortEnvelopes(envelopes);
        
        final List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][HEIGHT]);
        for (int i = 1; i < n; i++) {
            final int height = envelopes[i][HEIGHT];
            if (height > f.get(f.size() - 1)) {
                f.add(height);
            } else {
                int index = binarySearch(f, height);
                f.set(index, height);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int target) {
        int l = 0, r = f.size() - 1;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (f.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private void sortEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[WIDTH] != e2[WIDTH]) return Integer.compare(e1[WIDTH], e2[WIDTH]); 
            return Integer.compare(e2[HEIGHT], e1[HEIGHT]);
        });
    } 

    public static void main(String[] args) {
        Q354 q354 = new Q354();
        // System.out.println(q354.maxEnvelopes(
        //     new int[][]{
        //         new int[]{5,4}, new int[]{6,4}, new int[]{6,7}, new int[]{2,3}}));
        System.out.println(q354.maxEnvelopes(
                new int[][]{
                    new int[]{46,89}, new int[]{50,53}, new int[]{52,68}, new int[]{72,45}, new int[]{77,81}})); // 3
                    //[[46,89],[50,53],[52,68],[72,45],[77,81]]
                    
    }
}
