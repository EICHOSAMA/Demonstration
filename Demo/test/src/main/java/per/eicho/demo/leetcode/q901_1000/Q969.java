package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>969. Pancake Sorting 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/pancake-sorting/">
 *   969. Pancake Sorting</a>
 */
public final class Q969 {
    public List<Integer> pancakeSort(int[] arr) {
        // 1. 1 <= arr.length <= 100
        // 2. 1 <= arr[i] <= arr.length
        // 3. All integers in arr are unique 
        // (i.e. arr is a permutation of the integers from 1 to arr.length).        
        final List<Integer> result = new LinkedList<>();
        final int n = arr.length;
        int target = n;
        int p = n - 1; // last;
        int[] workspace = new int[n];
        while (target > 1) {
            if (arr[p] == target) {
                p--;
                target--;
                continue;
            }

            final int position = find(arr, target);
            if (position != 0) result.add(position + 1);
            result.add(p + 1);

            System.arraycopy(arr, 0, workspace, 0, p + 1);
            
            int i = 0;
            for (int ni = p; ni >= position + 1; i++, ni--) arr[i] = workspace[ni];
            System.arraycopy(workspace, 0, arr, i, position + 1);
            p--;
            target--;
            OutputUtils.println(arr);
        }
        return result;
    }

    private int find(int[] arr, int target) {
        int i = 0;
        for (int n = arr.length; i < n; i++) {
            if (arr[i] == target) break; 
        }
        return i;
    }

    public static void main(String[] args) {
        Q969 q969 = new Q969();
        List<Integer> result = q969.pancakeSort(new int[]{3,1,2});
        for (int num : result) {
            System.out.println(num);
        }
    }
}
