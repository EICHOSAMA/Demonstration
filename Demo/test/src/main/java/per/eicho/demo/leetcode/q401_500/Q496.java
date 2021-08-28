package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collector;

import jdk.internal.loader.BootLoader;
import jdk.internal.org.jline.terminal.Cursor;

/**
 * <p>496. Next Greater Element I 的题解代码 </p>
 * 
 * <pre>
 *  You are given two integer arrays nums1 and nums2 both of unique elements, where nums1 is a subset of nums2.
 *  Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *  
 *  The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, return -1 for this number.
 * 
 * Example 1:
 *    Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 *    Output: [-1,3,-1]
 *    Explanation:
 *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 *     For number 1 in the first array, the next greater number for it in the second array is 3.
 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * 
 * Example 2:
 *    Input: nums1 = [2,4], nums2 = [1,2,3,4]
 *    Output: [3,-1]
 *    Explanation:
 *     For number 2 in the first array, the next greater number for it in the second array is 3.
 *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * 
 *  Constraints:
 *   1. 1 <= nums1.length <= nums2.length <= 1000
 *   2. 0 <= nums1[i], nums2[i] <= 10^4
 *   3. All integers in nums1 and nums2 are unique.
 *   4. All the integers of nums1 also appear in nums2.
 * </pre>
 * @see <a href="https://leetcode.com/problems/next-greater-element-i/">496. Next Greater Element I</a>
 */
final public class Q496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*
         1. 1 <= nums1.length <= nums2.length <= 1000
         2. 0 <= nums1[i], nums2[i] <= 104
         3. All integers in nums1 and nums2 are unique.
         4. All the integers of nums1 also appear in nums2.
        */

        // 1. init: gather basic information
        final int len1 = nums1.length;
        final int len2 = nums2.length;

        // 2. prepare DS to store information generated during processing        
        final int[] result = new int[len1];
        /*
         * A more complete and consistent set of LIFO stack operations is provided by the Deque interface and its implementations, 
         * which should be used in preference to this class. (https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html)
         */
        final Deque<Integer> stack = new ArrayDeque<>(); 
        final Map<Integer, Integer> map = new HashMap<>();

        // optimization: int ⇒ Integer, for reducing JAVA boxing cost.
        for (Integer num : nums2) {
            while (!stack.isEmpty() && isLessThan(stack.peekLast(), num)) {
                map.put(stack.removeLast(), num);
            }
            stack.add(num);
        }

        for (int i = 0; i < len1; i++) {
            final int num = nums1[i];
            result[i] = map.getOrDefault(num, -1);
        }

        return result;
    }

    private boolean isLessThan(Integer a, Integer b) {
        // equal to a < b
        return a.compareTo(b) < 0;
    }


    public static void main(String[] args) {
        Q496 q496 = new Q496();

        final int[] result = q496.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
        for (int num : result) {
            System.out.print(num + ",");
        }
    }
}
