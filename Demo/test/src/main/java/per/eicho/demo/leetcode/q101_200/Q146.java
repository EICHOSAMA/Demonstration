package per.eicho.demo.leetcode.q101_200;

import java.util.HashMap;

/**
 * <p>146. LRU Cache 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/lru-cache/">146. LRU Cache</a>
 */
public final class Q146 {
    private static final class LRUCache {

        private static final class Node {

            final Integer key;
            Integer val;

            Node left;
            Node right;

            Node(Integer key, Integer val) {
                this.key = key;
                this.val = val;
            }
        }

        final int capacity;
        final HashMap<Integer, Node> kvp;
        
        int count;
        Node head;
        Node tail;

        /** Initialize the LRU cache with positive size capacity. */
        public LRUCache(int capacity) {
            // 1 <= capacity <= 3000
            this.capacity = capacity;
            kvp = new HashMap<>(capacity);
        
            head = new Node(null, null);
            tail = new Node(null, null);

            head.right = tail;
            tail.left = head;

            count = 0;
        }
        
        /** Return the value of the key if the key exists, otherwise return -1. */
        public int get(int key) {
            // The functions get and put must each run in O(1) average time complexity.
            // 0 <= key <= 10^4
            final Node node = kvp.get(key);

            if (node == null) return -1;

            removeNodeFromList(node);
            insertToHead(node);
 
            return node.val;
        }
        
        /** 
         * Update the value of the key if the key exists. 
         * Otherwise, add the key-value pair to the cache. 
         * If the number of keys exceeds the capacity from this operation, 
         * evict the least recently used key.
         */
        public void put(int key, int value) {
            // The functions get and put must each run in O(1) average time complexity.
            // 0 <= key <= 10^4
            // 0 <= value <= 10^5
            final Integer iKey = key;
            final Integer iVal = value;

            if (kvp.containsKey(iKey)) {
                // update the value of the key
                final Node node = kvp.get(iKey);
                node.val = iVal;

                removeNodeFromList(node);
                insertToHead(node);
            } else {
                // otherwise, add the key-value pair to the cache.
                count++;

                final Node node = new Node(iKey, iVal);
                kvp.put(key, node);
                
                insertToHead(node);
            }

            if (count > capacity) {
                final Node lastUsedNode = tail.left;
                removeNodeFromList(lastUsedNode);
                kvp.remove(lastUsedNode.key);
                count--;
            }

        }

        private void removeNodeFromList(Node node) {
            node.left.right = node.right;
            node.right.left = node.left;
        }

        private void insertToHead(Node node) {
            node.left = head;
            node.right = head.right;
            head.right.left = node;
            head.right = node;
        }
    }
    

    public static void main(String[] args) {
        LRUCache lCache = new LRUCache(2);
        lCache.put(1, 1);
        lCache.put(2, 2);
        System.out.println(lCache.get(1));
        lCache.put(3, 3);
        System.out.println(lCache.get(2));
    }
}
