package per.eicho.demo.leetcode.q101_200;

import java.util.Arrays;

/**
 * <p>155. Min Stack 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/min-stack/">155. Min Stack</a>
 */
@SuppressWarnings("unused")
public final class Q155 {
    private static final class MinStack {

        private int[] minsAndNums;
        private int numsCount;

        /** initialize your data structure here. */
        public MinStack() {
            minsAndNums = new int[10];
            minsAndNums[0] = Integer.MAX_VALUE;

            numsCount = 0;
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            final int usedCount = numsCount * 2 + 1;
            if (usedCount + 2 > minsAndNums.length) expandStorage();

            numsCount++;
            minsAndNums[usedCount] = x;
            minsAndNums[usedCount + 1] = Math.min(minsAndNums[usedCount - 1], x);
        }

        /**
         * Expand the internal storage for storing minimum and number information.
         */
        private void expandStorage() {
            minsAndNums = Arrays.copyOf(minsAndNums, (minsAndNums.length * 3 / 2) + 1);
        }

        /**
         * Removes the element on top of the stack.
         */
        public void pop() {
            if (numsCount == 0) return;
            numsCount--;
        }

        /**
         * Get the top element.
         */
        public int top() {
            // There isn't any specification said that return 0 when stack is empty.
            // Normal stack will throw an exception if the stack is empty.
            if (numsCount == 0) return 0;
            return minsAndNums[numsCount * 2 - 1]; // numsCount = 2 0,1,2,3,4
        }

        /**
         * Retrieve the minimum element in the stack.
         */
        public int getMin() {
            return minsAndNums[numsCount * 2];
        }
}
}
