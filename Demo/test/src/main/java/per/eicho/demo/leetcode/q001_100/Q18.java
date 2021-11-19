package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 18. 4Sum 的题解代码
 * 
 * 
 * <pre>
 *  Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *    1. 0 <= a, b, c, d < n
 *    2. a, b, c, and d are distinct.
 *    3. nums[a] + nums[b] + nums[c] + nums[d] == target
 *   You may return the answer in any order.
 * </pre>
 * @see <a href="https://leetcode.com/problems/4sum/">18. 4Sum</a>
 */
final public class Q18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // prepared sorted, unmodifiable data. 
        final List<Integer> numList = Collections.unmodifiableList(doSortInAscOrder(nums));

        final SearchingContext context = new SearchingContext(numList, target);
        doSearch(context, 0);
        // java int[] passed by value or reference.
        return context.getResultList();
    }

    private void doSearch(final SearchingContext context, final int searchingIndex) {        
        // select or not.
        if (context.size() == 4) {
            if (context.sum == context.target) {
                context.addSolution2Result();
            }
            return;
        }

        // border check
        if (searchingIndex > context.lastIndex) {
            return;
        }

        // Search Pruning
        if (context.size() == 3) {

            final int index = context.binarySearch(context.target - context.sum, searchingIndex, context.lastIndex);
            if (index == -1) {
                return;
            }

            context.push(index);
            doSearch(context, index);
            context.pop();
            return;
        }


        // use
        context.push(searchingIndex);
        doSearch(context, searchingIndex + 1);
        context.pop();
        
        // not use
        doSearch(context, searchingIndex + 1);
    }

    private List<Integer> doSortInAscOrder(int[] nums) {
        return Arrays.stream(nums)
            .boxed()
            .sorted()
            .collect(Collectors.toList());
    }

    static final class SearchingContext {

        final Map<Integer, List<Integer>> solutions;
        final List<Integer> numList;
        final int target;
        final int lastIndex;

        private final Stack<Integer> nums;
        private final Stack<Integer> indexes;
        private int sum;   

        SearchingContext(List<Integer> numList, int target) {
            this.solutions = new HashMap<>();
            this.numList = numList;
            this.target = target;
            lastIndex = this.numList.size() - 1;

            nums = new Stack<Integer>();
            indexes = new Stack<Integer>();
            sum = 0;
        }

        Integer getNumByIndex(int index) { return numList.get(index); }

        int size() { return nums.size(); }

        void push(int index) {
            indexes.push(index);
            
            Integer num = numList.get(index);
            nums.push(num); 
            sum += num;
        }

        void pop() {
            indexes.pop(); 
            sum -= nums.pop();
        }

        void addSolution2Result() {
            final Integer hashCode = getHashCode(nums); 
            if (solutions.containsKey(hashCode) ) {
                return;
            }
            solutions.put(hashCode, new ArrayList<Integer>(nums));
        }

        private Integer getHashCode(final List<Integer> nums) {
            int hashCode = 17;

            for (int num: nums) {
                hashCode = hashCode * 31 + num; 
            }
            
            return hashCode;
        }

        final List<List<Integer>> getResultList() {
            return new ArrayList<>(solutions.values());
        }

        private int binarySearch(final Integer target, final int left, final int right) {
            if (left > right) {
                return -1;
            }

            if (left == right) {
                if (numList.get(left).intValue() == target.intValue()) {
                    return left;
                } else {
                    return -1;
                }
            }

            final int mid = (left + right + 1) / 2;

            final Integer midNum = numList.get(mid);
            if (target < midNum) {
                return binarySearch(target, left, mid - 1);
            } else {
                return binarySearch(target, mid, right);
            }
        }
    }

    public static void main(String[] args) {
        Q18 q18 = new Q18();
        
        int[] nums = new int[]{-493,-470,-464,-453,-451,-446,-445,-407,-406,-393,-328,-312,-307,-303,-259,-253,-252,-243,-221,-193,-126,-126,-122,-117,-106,-105,-101,-71,-20,-12,3,4,20,20,54,84,98,111,148,149,152,171,175,176,211,218,227,331,352,389,410,420,448,485};
        List<List<Integer>> results = q18.fourSum(nums, 1057);
        for (List<Integer> result: results) {
            for (Integer num: result) {
                System.out.print(num);
                System.out.print(" ");    
            }
            System.out.println();
        }
    }
}
