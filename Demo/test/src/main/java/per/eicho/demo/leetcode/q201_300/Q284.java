package per.eicho.demo.leetcode.q201_300;

import java.util.Iterator;

/**
 * <p>284. Peeking Iterator 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/peeking-iterator/">
 *   284. Peeking Iterator</a>
 */
@SuppressWarnings("unused")
public final class Q284 {
    private static final class PeekingIterator implements Iterator<Integer> {
        final Iterator<Integer> innerIterator;
        Integer nextPointer = null;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            innerIterator = iterator;
            if (innerIterator.hasNext()) {
                nextPointer = iterator.next();
            }
        }
        
        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return nextPointer;
        }
        
        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            final Integer next = nextPointer;
            nextPointer = innerIterator.hasNext() ? innerIterator.next() : null;
            return next;
        }
        
        @Override
        public boolean hasNext() {
            return nextPointer != null;
        }
    }
}
