package per.eicho.demo.leetcode.q101_200;

import java.util.List;

/**
 * <p>120. Triangle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/triangle/">120. Triangle</a>
 */
public final class Q120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 1. 1 <= triangle.length <= 200
        // 2. triangle[0].length == 1
        // 3. triangle[i].length == triangle[i - 1].length + 1
        // 4. -10^4 <= triangle[i][j] <= 10^4        
        if (triangle == null || triangle.size() == 0) return 0;
        final int len = triangle.size();
        final int[] f = new int[len];
        f[0] = triangle.get(0).get(0);
        
        for (int i = 1; i < len; i++) {
            final List<Integer> row = triangle.get(i);
            
            int index = row.size() - 1;
            f[index] = f[index - 1] + row.get(index);
            
            while (--index > 0) {
                f[index] = Math.min(f[index], f[index - 1]) + row.get(index);
            }
            
            f[index] += row.get(index);
        }
        
        int result = Integer.MAX_VALUE;
        for (int num: f) {
            if (num < result) result = num;
        }
        return result;
    }
}
