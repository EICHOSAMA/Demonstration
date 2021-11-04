package per.eicho.demo.leetcode.q501_600;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>599. Minimum Index Sum of Two Lists 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-index-sum-of-two-lists/">599. Minimum Index Sum of Two Lists</a>
 */
public final class Q599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        
        // 1. regist data into kvp
        Map<String, Integer> kvp = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            kvp.put(list1[i], i);
        }

        // 2.
        int minIndexSum = Integer.MAX_VALUE;
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < list2.length; i++) {
            final String restaurant = list2[i];
            final int index = kvp.getOrDefault(restaurant, -1);

            // 1. not exist
            if (index == -1) continue;

            // 2. exist
            final int sum = index + i;

            if (sum > minIndexSum) continue;

            if (sum < minIndexSum) {
                result.clear();
                minIndexSum = sum;
            } 

            result.add(restaurant);
        }

        return result.toArray(new String[result.size()]);
    }
}
