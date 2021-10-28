package per.eicho.demo.leetcode.q1_100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>89. Gray Code 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/gray-code/">89. Gray Code</a>
 */
final public class Q89 {
    public List<Integer> grayCode(int n) {
        // 1 <= n <= 16
        final List<Integer> result = new ArrayList<>((int)Math.pow(2, n));
        
        result.add(0);
        Integer offset = 1;
        for (int i = 0; i < n; i++) {
            final Integer tempOffSet = offset;
            
            final List<Integer> tempList = new ArrayList<>(result)
                .stream()
                .map(num -> num + tempOffSet)
                .collect(Collectors.toList());
            Collections.reverse(tempList);

            result.addAll(tempList);
            // OFFSET -, 0 [0] 
            // OFFSET 1, 1 [0,1]
            // OFFSET 2, 2 [0,1,3,2]
            // OFFSET 4, 3 [0,1,3,2,6,7,5,4]
            // OFFSET 8, 4 [0,1,3,2,6,7,5,4,12,13,15,14,10,11,9,8]

            offset *= 2;
        }
        return result;
    }
}
