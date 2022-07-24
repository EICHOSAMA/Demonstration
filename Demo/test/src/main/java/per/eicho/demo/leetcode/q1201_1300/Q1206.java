package per.eicho.demo.leetcode.q1201_1300;

import java.util.Random;

/**
 * <p>1206. Design Skiplist 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-skiplist/">
 *   1206. Design Skipliste</a>
 */
@SuppressWarnings("unused")
public final class Q1206 {
    private static final class Skiplist {

        private static final class Node {

            final int layer;
            final Node[] next;
            final int val;

            Node(int val, int layer) {
                // assert 1 <= layer <= MAX_LV
                this.val = val;
                this.layer = layer;
                next = new Node[layer];
            }

        }

        private static final int MAX_LV = 16; // round_up(log(50_000)) 
        private final Random random;

        private final Node vituralHead;

        /** Initializes the object of the skiplist. */
        public Skiplist() {
            // 1. 0 <= num, target <= 2 * 10^4
            vituralHead = new Node(-1, MAX_LV);
            random = new Random(System.currentTimeMillis());
        }
        
        /** 
         * Returns true if the integer 
         * target exists in the Skiplist or false otherwise. 
         */
        public boolean search(int target) {
            Node cursor = vituralHead;
            int cLayer = vituralHead.layer - 1;

            while (cLayer >= 0) {
                final Node next = cursor.next[cLayer];
                if (next == null || target < next.val) {
                    cLayer--;
                    continue;
                }
                // next.val <= target
                if (next.val == target) return true;
                // next.val < target
                cursor = next;
            }
            return false;
        }
        
        /** Inserts the value num into the SkipList. */
        public void add(int num) {
            // 1. 0 <= num, target <= 2 * 10^4
            // 2. At most 5 * 10^4 calls will be made to 
            //    search, add, and erase.
            final Node node = new Node(num, random.nextInt(MAX_LV) + 1);
            final int layer = node.layer;

            Node cursor = vituralHead;
            int cLayer = layer - 1; // cursor layer.

            while (cLayer >= 0) {
                final Node next = cursor.next[cLayer];
                if (next == null || node.val < next.val) {
                    cursor.next[cLayer] = node;
                    node.next[cLayer--] = next;
                    continue;
                }

                // num >= next.val;
                cursor = next;
            }
        }
        
        /**
         * Removes the value num from the Skiplist and returns true. 
         * If num does not exist in the Skiplist, 
         * do nothing and return false. 
         * If there exist multiple num values, 
         * removing any one of them is fine.
         */
        public boolean erase(int num) {
            Node cursor = vituralHead;
            int cLayer = vituralHead.layer - 1;

            Node target = null;
            while (cLayer >= 0) {
                final Node next = cursor.next[cLayer];
                if (next == null || num < next.val) {
                    cLayer--;
                    continue;
                }
                // next.val <= num
                if (next.val == num) {
                    if (target == null) target = next;

                    if (target != next) {
                        cursor = next;
                        continue;
                    } 

                    cursor.next[cLayer] = target.next[cLayer];
                    cLayer--;
                } else {
                    // next.val < num
                    cursor = next;
                }
            }
            return target != null;
        }
    }
    
}
