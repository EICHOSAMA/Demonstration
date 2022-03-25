package per.eicho.demo.leetcode.q201_300;

import java.util.PriorityQueue;

/**
 * <p>295. Find Median from Data Stream 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-median-from-data-stream/">
 *   295. Find Median from Data Stream</a>
 */
@SuppressWarnings("unused")
public final class Q295 {
    private final class MedianFinder {

        final PriorityQueue<Integer> maxHeap;
        final PriorityQueue<Integer> minHeap;
        int size = 0;
        

        /** initializes the MedianFinder object. */
        public MedianFinder() {
            maxHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);
            minHeap = new PriorityQueue<>();
        }
        
        /** adds the integer num from the data stream to the data structure. */
        public void addNum(int num) {
            if (size == 0) {
                minHeap.add(num);
                size++;
                return;
            }

            if (num > minHeap.peek()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
            size++;
            adjust();
        }
        
        private void adjust() {
            int diff = minHeap.size() - maxHeap.size(); 
            if (0 <= diff && diff <= 1) return;

            while (diff > 1) {
                maxHeap.add(minHeap.poll());
                diff = minHeap.size() - maxHeap.size();
            }

            while (diff < 0) {
                minHeap.add(maxHeap.poll());
                diff = minHeap.size() - maxHeap.size();
            }
        }

        /** returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted. */
        public double findMedian() {
            // There will be at least one element in the data structure before calling findMedian.
            if (size % 2 == 1) return minHeap.peek();
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}
