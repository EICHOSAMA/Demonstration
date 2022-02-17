package per.eicho.demo.leetcode.q1101_1200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>1122. Relative Sort Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/relative-sort-array/">
 *   1122. Relative Sort Array</a>
 */
public final class Q1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 1. 1 <= arr1.length, arr2.length <= 1000
        // 2. 0 <= arr1[i], arr2[i] <= 1000
        // 3. All the elements of arr2 are distinct.
        // 4. Each arr2[i] is in arr1.        
        final Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : arr1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        int p = 0;
        for (Integer iNum : arr2) {
            final int num = iNum;
            int count = map.get(iNum);
            while (count != 0) {
                arr1[p++] = num;
                count--;
            }
            map.remove(iNum);
        }

        int[] remain = new int[map.size()];
        int p2 = 0;
        for (int key : map.keySet()) {
            remain[p2++] = key;
        }
        Arrays.sort(remain);

        for (Integer iNum : remain) {
            final int num = iNum;
            int count = map.get(iNum);
            while (count != 0) {
                arr1[p++] = num;
                count--;
            }
            map.remove(iNum);
        }        

        return arr1;
    }
}
