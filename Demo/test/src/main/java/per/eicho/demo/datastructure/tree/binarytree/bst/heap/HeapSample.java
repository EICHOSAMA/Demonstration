package per.eicho.demo.datastructure.tree.binarytree.bst.heap;

import java.util.Arrays;

/**
 * <p>堆的示例代码</p>
 */
@SuppressWarnings("unused")
final class HeapSample {

    private static abstract class AbstractHeap implements Heap {

        private static final int DEFAULT_CAPACITY = 16;

        int capacity;
        int[] heap;
        int size;
    
        AbstractHeap(int initialCapacity) {
            if (initialCapacity < 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
            }

            capacity = initialCapacity;
            heap = new int[initialCapacity];
        }

        AbstractHeap() {
            this(DEFAULT_CAPACITY);
        }

        protected void expand() {
            heap = Arrays.copyOf(heap, capacity *= 2);
        }

        /**
         * <p>抽象方法 - 结点值对比</p>
         * 
         * <pre>
         *  Heap应保证，greater(father, son) == true。
         *  大小堆则通过不同的返回值来实现。
         * </pre>
         */
        protected abstract boolean greater(int num1, int num2);

        public void insert(int num) {
            if (size == capacity) expand();

            heap[size] = num;
            shiftup(size++);
        }

        public int peek() {
            checkSize();
            return heap[0];
        }

        public int pop() {
            checkSize();

            final int topElement = heap[0];
            heap[0] = heap[--size];
            heapify(0, size);
            return topElement;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() { 
            return size == 0;
        }

        private void shiftup(int p) {
            // bottom → up
            int father;
            while ((father = (p - 1) / 2) >= 0) {
                final int num1 = heap[father];
                final int num2 = heap[p];
                
                if (greater(num1, num2)) break;
                swap(father, p);
                p = father;
            }
        }

        private void heapify(int p, int bound) {
            // up → bottom
            int son = p * 2 + 1;
            while (son < bound) {
                if (son + 1 < bound && greater(heap[son + 1], heap[son])) son++;
                if (greater(heap[p], heap[son])) break;

                swap(p, son);
                p = son;
            }
        }
    
        private void swap(int i, int j) {
            final int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void checkSize() {
            if (size == 0) {
                throw new IllegalStateException("Illegal State: Heap is empty.");
            }
        }
    }


    private static class MinHeap extends AbstractHeap {

        MinHeap(int initialCapacity) { 
            super(initialCapacity);
        }

        MinHeap() {
            super();
        }

        @Override
        protected boolean greater(int num1, int num2) {
            return num1 <= num2;
        }

    }

    private static class MaxHeap extends AbstractHeap {

        MaxHeap(int initialCapacity) { 
            super(initialCapacity);
        }

        MaxHeap() {
            super();
        }

        @Override
        protected boolean greater(int num1, int num2) {
            return num1 >= num2;
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        minHeap.insert(1);
        System.out.println(minHeap.pop());
        minHeap.insert(2);
        System.out.println(minHeap.peek());
        minHeap.insert(3);
        System.out.println(minHeap.peek());
        minHeap.insert(1);
        System.out.println(minHeap.peek());
        
        System.out.println(minHeap.size == 3);
        System.out.println(minHeap.pop() == 1);
        System.out.println(minHeap.pop() == 2);
        System.out.println(minHeap.pop() == 3);

        MaxHeap maxHeap = new MaxHeap();

        maxHeap.insert(1);
        System.out.println(maxHeap.pop());
        maxHeap.insert(2);
        System.out.println(maxHeap.peek());
        maxHeap.insert(3);
        System.out.println(maxHeap.peek());
        maxHeap.insert(1);
        System.out.println(maxHeap.peek());
        
        System.out.println(maxHeap.size == 3);
        System.out.println(maxHeap.pop() == 3);
        System.out.println(maxHeap.pop() == 2);
        System.out.println(maxHeap.pop() == 1);
    }

}
