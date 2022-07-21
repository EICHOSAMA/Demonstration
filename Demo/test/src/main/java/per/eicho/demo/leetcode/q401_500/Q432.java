package per.eicho.demo.leetcode.q401_500;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>432. All O`one Data Structure 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/all-oone-data-structure/">
 *   432. All O`one Data Structure</a>
 */
@SuppressWarnings("unused")
public final class Q432 {
    private static final class AllOne {

        private static final class LinkedListNode {
            int count = 0;
            LinkedListNode left = null;
            LinkedListNode right = null;

            final String str;

            LinkedListNode(String str) {
                this.str = str;
            }

        }

        final LinkedListNode vituralHead;
        final LinkedListNode vituralTail;

        final Map<Integer, LinkedListNode> len2LeftMostNodeMap;
        final Map<Integer, LinkedListNode> len2RightMostNodeMap;

        final Map<String, LinkedListNode> key2NodeMap;  

        public AllOne() {
            vituralHead = new LinkedListNode("");
            vituralTail = new LinkedListNode("");
            vituralHead.right = vituralTail;
            vituralTail.left = vituralHead;

            key2NodeMap = new HashMap<>();
            len2LeftMostNodeMap = new HashMap<>();
            len2RightMostNodeMap = new HashMap<>();
        }
        
        public void inc(String key) {
            if (!key2NodeMap.containsKey(key)) {
                final LinkedListNode node = new LinkedListNode(key);
                node.count = 1;

                if (!len2RightMostNodeMap.containsKey(1)) {
                    len2LeftMostNodeMap.put(1, node);
                    len2RightMostNodeMap.put(1, node);

                    node.left = vituralHead;
                    node.right = vituralHead.right;
                    vituralHead.right.left = node;
                    vituralHead.right = node;
                } else {
                    final LinkedListNode rightMostNode = len2RightMostNodeMap.get(1);

                    node.right = rightMostNode.right;
                    node.left = rightMostNode;

                    rightMostNode.right.left = node;
                    rightMostNode.right = node;

                    len2RightMostNodeMap.put(1, node);
                }

                key2NodeMap.put(key, node);
            } else {
                // key already exists
                final LinkedListNode node = key2NodeMap.get(key);
                final int oldCount = node.count++;
                final int newCount = node.count;

                LinkedListNode leftMostNode = len2LeftMostNodeMap.get(oldCount);
                LinkedListNode rightMostNode = len2RightMostNodeMap.get(oldCount);

                // remove from leftMostNode <--> rightMostNode sub LinkedList
                // and Maintain those two maps (len2LeftMostNodeMap, len2RightMostNodeMap)
                if (leftMostNode == rightMostNode) {
                    // assert leftMostNode == Node
                    // then len of sub linkedlist is 1.
                    len2LeftMostNodeMap.remove(oldCount);
                    len2RightMostNodeMap.remove(oldCount);

                    leftMostNode = null;
                    rightMostNode = null;
                } else if (leftMostNode == node) {
                    // the len of sub linkedlist > 1
                    len2LeftMostNodeMap.put(oldCount, node.right);

                    leftMostNode = node.right;
                } else if (rightMostNode == node) {
                    // the len of sub linkedlist > 1
                    len2RightMostNodeMap.put(oldCount, node.left);

                    rightMostNode = node.left;
                } else {
                    // the len of sub linkedlist > 2 (do nothing)
                }

                node.left.right = node.right;
                node.right.left = node.left;
                
                if (!len2LeftMostNodeMap.containsKey(newCount)) {
                    len2LeftMostNodeMap.put(newCount, node);
                    len2RightMostNodeMap.put(newCount, node);

                    if (rightMostNode != null) {
                        insertAfterNode(rightMostNode, node);
                    } else {
                        // keep original position.
                        insertAfterNode(node.left, node);
                    }
                } else {
                    insertAfterNode(len2RightMostNodeMap.put(newCount, node), node);
                }
            }
        }
    
        /**
         * Decrements the count of the string key by 1. 
         * If the count of key is 0 after the decrement, 
         * remove it from the data structure. 
         * It is guaranteed that key exists in the data 
         * structure before the decrement.
         */
        public void dec(String key) {
            final LinkedListNode node = key2NodeMap.get(key);
            final int oldCount = node.count--;
            final int newCount = node.count;

            LinkedListNode leftMostNode = len2LeftMostNodeMap.get(oldCount);
            LinkedListNode rightMostNode = len2RightMostNodeMap.get(oldCount);

            if (leftMostNode == rightMostNode) {
                // assert leftMostNode == Node
                // then len of sub linkedlist is 1.
                len2LeftMostNodeMap.remove(oldCount);
                len2RightMostNodeMap.remove(oldCount);

                leftMostNode = null;
                rightMostNode = null;
            } else if (leftMostNode == node) {
                // the len of sub linkedlist > 1
                len2LeftMostNodeMap.put(oldCount, node.right);

                leftMostNode = node.right;
            } else if (rightMostNode == node) {
                // the len of sub linkedlist > 1
                len2RightMostNodeMap.put(oldCount, node.left);

                rightMostNode = node.left;
            } else {
                // the len of sub linkedlist > 2 (do nothing)
            }

            node.left.right = node.right;
            node.right.left = node.left;

            if (newCount == 0) {
                key2NodeMap.remove(key);
                node.left = null;
                node.right = null;
                return;
            }

            if (!len2LeftMostNodeMap.containsKey(newCount)) {
                len2LeftMostNodeMap.put(newCount, node);
                len2RightMostNodeMap.put(newCount, node);

                if (leftMostNode != null) {
                    insertAfterNode(leftMostNode.left, node);
                } else {
                    // keep original position.
                    insertAfterNode(node.left, node);
                }
            } else {
                insertAfterNode(len2RightMostNodeMap.put(newCount, node), node);
            }
        }
        
        /**
         * Returns one of the keys with the maximal count. 
         * If no element exists, return an empty string "".
         */
        public String getMaxKey() {
            return vituralTail.left.str;
        }
        
        /**
         * Returns one of the keys with the minimum count. 
         * If no element exists, return an empty string "".
         */
        public String getMinKey() {
            return vituralHead.right.str;
        }

        private void insertAfterNode(LinkedListNode l, LinkedListNode node) {
            final LinkedListNode r = l.right;
            node.left = l;
            node.right = r;
            l.right = node;
            r.left = node;
        }
    }   

    public static void main(String[] args) {
        final AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }
}
