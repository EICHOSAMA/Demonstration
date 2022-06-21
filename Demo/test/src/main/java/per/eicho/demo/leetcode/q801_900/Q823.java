package per.eicho.demo.leetcode.q801_900;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>823. Binary Trees With Factors 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-trees-with-factors/">
 *   823. Binary Trees With Factors</a>
 */
public final class Q823 {

    private static final long MODULE = 1_000_000_000 + 7; 

    public int numFactoredBinaryTrees(int[] arr) {
        // 1. 1 <= arr.length <= 1000
        // 2. 2 <= arr[i] <= 10^9
        // 3. All the values of arr are unique.
        Arrays.sort(arr);
        final Map<Integer, Long> f = new HashMap<>();
        f.put(arr[0], 1L);
        long result = 1;
        for (int i = 1; i < arr.length; i++) {
            final int numI = arr[i];

            long count = 1L;
            for (int j = 0; j < i; j++) {
                final int numJ = arr[j];
                if (numI % numJ != 0) continue;
                final int numK = numI / numJ;
                if (!f.containsKey(numK)) continue;
                count += (f.get(numJ) * f.get(numK));
            }

            count %= MODULE;
            f.put(numI, count);
            result += count;
        }
        
        return (int)(result % MODULE);
    }

    public static void main(String[] args) {
        Q823 q823 = new Q823();
        final int result = q823.numFactoredBinaryTrees(new int[]{46,144,5040,4488,544,380,4410,34,11,5,3063808,5550,34496,12,540,28,18,13,2,1056,32710656,31,91872,23,26,240,18720,33,49,4,38,37,1457,3,799,557568,32,1400,47,10,20774,1296,9,21,92928,8704,29,2162,22,1883700,49588,1078,36,44,352,546,19,523370496,476,24,6000,42,30,8,16262400,61600,41,24150,1968,7056,7,35,16,87,20,2730,11616,10912,690,150,25,6,14,1689120,43,3128,27,197472,45,15,585,21645,39,40,2205,17,48,136});
        System.out.println(result); // 509730797
    }
}
