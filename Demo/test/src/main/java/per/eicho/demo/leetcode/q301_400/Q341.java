package per.eicho.demo.leetcode.q301_400;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>341. Flatten Nested List Iterator 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flatten-nested-list-iterator/">
 *   341. Flatten Nested List Iterator</a>
 */
@SuppressWarnings("unused")
public final class Q341 {
    private static interface NestedInteger {
        /**
         * @return true if this NestedInteger holds a single integer, rather than a nested list.
         */
        public boolean isInteger();

        /**
         * @return the single integer that this NestedInteger holds, if it holds a single integer
         *  Return null if this NestedInteger holds a nested list
         */
        public Integer getInteger();

        /**
         * 
         * @return the nested list that this NestedInteger holds, if it holds a nested list
         *  Return empty list if this NestedInteger holds a single integer
         */
        public List<NestedInteger> getList();
    }

    private static class NestedIterator implements Iterator<Integer> {

        final Queue<NestedInteger> nestedList;

        NestedInteger cursor;
        NestedIterator helperIterator;

        /**
         * Initializes the iterator with the nested list nestedList.
         * @param nestedList
         */
        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = new LinkedList<>(nestedList);

            moveCursor2Next();
        }
    
        /**
         * Returns the next integer in the nested list.
         */
        @Override
        public Integer next() {
            if (!hasNext()) return null;

            Integer num = null;
            if (helperIterator == null) {
                num = cursor.getInteger();
                moveCursor2Next();
                return num;
            }

            // assert helperIterator.hasNext() == true : "moveCursor2Next will skip empty helperIterator"
            num = helperIterator.next();
            if (!helperIterator.hasNext()) moveCursor2Next();
            return num;
        }
    
        /**
         * Returns true if there are still some integers in the nested list and false otherwise. 
         */
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        private void moveCursor2Next() {
            if (nestedList.isEmpty()) {
                cursor = null;
                helperIterator = null;
                return;
            }

            cursor = nestedList.poll();
            if (!cursor.isInteger()) {
                helperIterator = new NestedIterator(cursor.getList());
                if (!helperIterator.hasNext()) moveCursor2Next(); // skip empty
            } else {
                helperIterator = null;
            }
        }
    }
}
