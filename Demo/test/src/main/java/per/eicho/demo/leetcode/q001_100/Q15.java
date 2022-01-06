package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>15. 3Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/3sum/">15. 3Sum</a>
 */
public final class Q15 {
    public List<List<Integer>> threeSum(int[] nums) {
        final List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums); // sort.

        for (int i = 0; i < nums.length; i++) {
            int firstElement = nums[i];
            if (firstElement > 0) break;

            //assert firstElement <= 0;

            for (int j = i + 1; j < nums.length; j++) {
                int secondElement = nums[j];
                //assert secondElement >= firstElement;

                int thirdElement = 0 - secondElement - firstElement;

                if (thirdElement < secondElement) break;
                //assert thirdElement >= secondElement;


                if (j + 1 < nums.length) {
                    int k = binarySearch(nums, j + 1, nums.length - 1, thirdElement);
                    if (k == -1) continue;

                    List<Integer> triple = Arrays.asList(firstElement, secondElement, thirdElement);
                    int exist = result.indexOf(triple);
                    if (exist == -1)
                        result.add(triple);
                }
            }
        }

        return result;
    }

    private int binarySearch(final int[] nums, final int l, final int r, final int target) {
        if (l == r)
            return nums[l] == target? l: -1;
        final int mid = (l + r + 1) / 2;
        if (target < nums[mid])
            return binarySearch(nums, l, mid - 1, target); // search left.
        return binarySearch(nums, mid, r, target);
    }
}
