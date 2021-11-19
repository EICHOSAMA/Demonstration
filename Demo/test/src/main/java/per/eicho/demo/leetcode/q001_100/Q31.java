package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>31. Next Permutation 的题解代码 </p>
 * 
 * <pre>
 *  Implement next permutation, which rearranges numbers into the lexicographically 
 *  next greater permutation of numbers.
 *  If such an arrangement is not possible, it must rearrange it as the 
 *  lowest possible order (i.e., sorted in ascending order).
 *  
 *  The replacement must be in place and use only constant extra memory.
 * 
 * Example 1:
 *    Input: nums = [1,2,3]
 *    Output: [1,3,2]
 * 
 * Example 2:
 *    Input: nums = [3,2,1]
 *    Output: [1,2,3]
 * 
 * Example 3:
 *    Input: nums = [1,1,5]
 *    Output: [1,5,1]
 * 
 * Example 4:
 *    Input: nums = [1]
 *    Output: [1]
 *  
 * Constraints:
 *   1. 1 <= nums.length <= 100
 *   2. 0 <= nums[i] <= 100
 * </pre>
 * @see <a href="https://leetcode.com/problems/next-permutation/">31. Next Permutation</a>
 */
public class Q31 {
    public void nextPermutation(int[] nums) {
        // 1. create tree. 
        final TreeNode root = TreeNode.CreateRootNode(nums);
        
        // 2. main process.
        root.nextPermutation();

        // 3. merge result.
        root.mergeToArray(nums);
    }
    
    static final class TreeNode {
        TreeNode sonNode;
        TreeNode fatherNode;

        int num;
        int nth;

        /**
         * integer list sorted in ascending order.
         * which represent all the possible values of current node.
         */
        final List<Integer> possibleValues; 
        final int size;

        TreeNode(List<Integer> possibleValues, int[] nums, final int index, TreeNode fatherNode) {
            this.fatherNode = fatherNode;

            // 1. set node value.
            num = nums[index];

            // 2. defensive copying.
            this.possibleValues = Collections.unmodifiableList(new ArrayList<>(possibleValues)); 
            size = possibleValues.size();

            // 3. nth in possibleValues.
            int tempNth = -1;
            for (int i = 0; i < possibleValues.size(); i++) {
                final int possibleValue = possibleValues.get(i);
                if (possibleValue == num) {
                    tempNth = i;
                    break;
                }
            }
            nth = tempNth;
            
            if (size > 1) {
                possibleValues.remove(nth);
                sonNode = new TreeNode(possibleValues, nums, index + 1, this);
            } else {
                sonNode = null;
            }
        }
        
        TreeNode(List<Integer> possibleValues, TreeNode fatherNode) {
            this.fatherNode = fatherNode;

            // 1. set node value.
            num = possibleValues.get(0);

            // 2. defensive copying.
            this.possibleValues = Collections.unmodifiableList(new ArrayList<>(possibleValues)); 
            size = possibleValues.size();

            // 3. nth in possibleValues.
            nth = 0;
            
            if (size > 1) {
                possibleValues.remove(nth);
                sonNode = new TreeNode(possibleValues, this);
            } else {
                sonNode = null;
            }
        }

        boolean nextPermutation() {
            if (sonNode != null) {
                if (sonNode.nextPermutation() == true) {
                    return true; // status change succeed.
                }
            }

            // leaf node can not do anything.
            if (size == 1) {
                return false;
            }

            // current layer do not has next possible value.
            if (nth == size - 1) {
                if (this.fatherNode != null) {
                    return false;
                }
                
                // if top level node. (has no father node)
                nth = -1; // see nth++;
            }

            // move next.
            final List<Integer> possibleValues = new LinkedList<>(this.possibleValues);
            
            final Integer currentNum = this.num; // boxing
            // skip same value.
            for (int i = nth + 1; i < possibleValues.size(); i++) {
                if (possibleValues.get(i).compareTo(currentNum) == 0) {
                    continue;
                }

                // change node status
                nth = i;
                num = possibleValues.get(nth);
                possibleValues.remove(nth);
                this.sonNode = new TreeNode(possibleValues, fatherNode);
                return true; // status change failed. ask father node to do this job.
            }

            return false;
        }

        void mergeToArray(int[] result) {
            TreeNode currentNode = this;
            int index = 0;
            
            while (currentNode != null) {
                result[index] = currentNode.num;

                currentNode = currentNode.sonNode;
                index++;
            }
        }

        static TreeNode CreateRootNode(int[] nums) {
            // 1. copy then sort in ascending order.
            final int[] copyOfNums = Arrays.copyOf(nums, nums.length);
            final int[] sortedNums = Arrays.copyOf(nums, nums.length);
            Arrays.sort(sortedNums);

            List<Integer> possibleValues = 
                IntStream.of(sortedNums)
                    .boxed()
                    .collect(Collectors.toList());
            // [Performance optimization] Convert To Linked List.
            possibleValues = new LinkedList<>(possibleValues);
            final TreeNode node = new TreeNode(possibleValues, copyOfNums, 0, null);
            possibleValues = null;
            return node;
        }
    }

    public static void main(String[] args) {
        Q31 q31 = new Q31();

        int[] nums = new int[]{10,9,8,7,6,5,4,3,2,1};
        q31.nextPermutation(nums);

        for (int i: nums) {
            System.out.print(i);
            System.out.print(",");
        }
    }
}
