package per.eicho.demo.leetcode.q401_500;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>460. LFU Cache 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/lfu-cache/">460. LFU Cache</a>
 */
public final class Q460 {
    private static final class LFUCache {

        private static final class Node {

            final int key;
            int val;
            int cnt; // At most 2 * 10^5 calls will be made to get and put.

            Node prev;
            Node next;

            Node(int key, int val) {
                // 0 <= key <= 10^5
                // 0 <= value <= 10^9                
                this.key = key;
                this.val = val;
                this.cnt = 1;
            }

            void used() { cnt++; }

            void update(int newVal) { 
                val = newVal;
                used(); 
            }

        }

        final int capacity;
        final Map<Integer, Node> map;
        final Map<Integer, Node> indexes; // <Count, First Node>
        final Node virtualHead;
        final Node virtualTail;

        public LFUCache(int capacity) {
            // 0 <= capacity <= 10^4
            this.capacity = capacity;
            map = new HashMap<>(capacity);

            virtualHead = new Node(-1, -1); // At most 2 * 10^5 calls will be made to get and put.
            virtualTail = new Node(-2, -1);
            virtualHead.cnt = 200_000 + 10;
            virtualHead.next = virtualTail;
            
            virtualTail.cnt = 0;
            virtualTail.prev = virtualHead;

            indexes = new HashMap<>();
            indexes.put(virtualHead.cnt, virtualHead);
            indexes.put(virtualTail.cnt, virtualTail);
        }
        
        /**
         * Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
         */
        public int get(int key) {
            // 0 <= key <= 10^5       
            final Integer iKey = key;
            if (!map.containsKey(iKey)) return -1;
            
            final Node node = map.get(iKey);
            // maintain linkedlist.
            node.used();
            adjustNode(node);

            return node.val;
        }
        
        /**
         * Update the value of the key if present, or inserts the key if not already present. 
         * When the cache reaches its capacity, it should invalidate and remove the least frequently used key 
         * before inserting a new item. 
         * 
         * For this problem, when there is a tie (i.e., two or more keys with the same frequency), 
         * the least recently used key would be invalidated.
         */
        public void put(int key, int value) {
            if (capacity == 0) return;
            // 0 <= key <= 10^5
            // 0 <= value <= 10^9
            final Integer iKey = key;

            if (map.containsKey(iKey)) {
                final Node node = map.get(iKey);
                node.update(value);

                // 1. maintain linked list.
                adjustNode(node);

            } else {
                // remove the least frequently used key before inserting a new item.
                removeLFUIfNecessary();

                final Node node = new Node(key, value);
                
                // 1. add to map.
                map.put(iKey, node);

                // 2. add to linked list.
                appendNode(node);
            }
        }

        private void appendNode(final Node node) {
            Node index = virtualTail; // indexes.get(0);

            final Node insertP;
            if (index.prev.cnt == node.cnt) {
                insertP = indexes.get(index.prev.cnt).prev;
            } else {
                insertP = index.prev;
            }
            insertAfter(node, insertP);
            indexes.put(node.cnt, node);
        }

        private void adjustNode(final Node node) {
            Node index = indexes.get(node.cnt - 1);

            if (index.key == node.key) {
                final int cnt = node.cnt - 1;
                if (index.next.cnt == cnt) {
                    indexes.put(cnt, index.next); // update index to the next node.
                } else {
                    indexes.remove(cnt);
                }
                index = index.next;
            }

            // remove from list temporarily.
            removeFromLinkedList(node);
            
            final Node insertP;
            if (index.prev.cnt == node.cnt) {
                insertP = indexes.get(index.prev.cnt).prev;
            } else {
                // index.prev.cnt > node.cnt
                insertP = index.prev;
            }
            insertAfter(node, insertP);
            indexes.put(node.cnt, node);
        }

        private void insertAfter(final Node node, Node cursor) {
            node.next = cursor.next;
            node.prev = cursor;
            node.next.prev = node;
            cursor.next = node;
        }

        private void removeFromLinkedList(final Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null;
        }

        private void removeLFUIfNecessary() {
            if (map.size() == capacity) {
                // remove the least frequently used key before inserting a new item.
                final Node targetNode = virtualTail.prev;
                
                // 1. remove from map.
                map.remove(targetNode.key);

                // 2. remove from linked list. & indexes
                Node index = indexes.get(targetNode.cnt);
                if (index.key == targetNode.key) {
                    if (index.next.cnt == index.cnt) {
                        index = index.next;
                        indexes.put(index.cnt, index); // update index to the next node.
                    } else {
                        indexes.remove(index.cnt);
                    }
                }

                removeFromLinkedList(targetNode);
            }
        }

    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("1:" + cache.get(1));
        cache.put(3, 3);
        System.out.println("2:" + cache.get(2));
        System.out.println("3:" + cache.get(3));
        cache.put(4, 4);
        System.out.println("4:" + cache.get(1));
        System.out.println("5:" + cache.get(3));
        System.out.println("6:" + cache.get(4));        
    }
}
