package per.eicho.demo.leetcode.q701_800;

/**
 * <p>706. Design HashMap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-hashmap/">706. Design HashMap</a>
 */
public final class Q706 {
    private static class MyHashMap {

        final static int KEY_NOT_EXISTS = -1;
        final static int CAPACITY = 1000;
        final HashTableNode[] hashTable;
        
        private class HashTableNode {

            final int key;
            int val;
            HashTableNode next;

            HashTableNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        /**
         * initializes the object with an empty map.
         */
        public MyHashMap() {
            hashTable = new HashTableNode[CAPACITY];
        }
        
        /**
         * inserts a (key, value) pair into the HashMap. 
         * If the key already exists in the map, update the corresponding value.
         */
        public void put(int key, int value) {
            final int hashCode = hash(key);
            final int index = hashTableIndex(hashCode);

            if (hashTable[index] == null) {
                hashTable[index] = new HashTableNode(key, value);
                return;
            }
            
            HashTableNode lastCursor = null;
            HashTableNode cursor = hashTable[index];
            while (cursor != null) {
                if (cursor.key == key) {
                    // already exists, update the corresponding value.
                    cursor.val = value;
                    return; 
                }
                
                lastCursor = cursor;
                cursor = cursor.next; // move to next;
            }

            lastCursor.next = new HashTableNode(key, value);
        }
        
        /**
         * returns the value to which the specified key is mapped, 
         * or -1 if this map contains no mapping for the key.
         */
        public int get(int key) {
            final int hashCode = hash(key);
            final int index = hashTableIndex(hashCode);

            HashTableNode cursor = hashTable[index];
            while (cursor != null) {
                if (cursor.key == key) {
                    return cursor.val;
                }

                cursor = cursor.next;
            }

            return KEY_NOT_EXISTS;
        }
        
        /**
         * removes the key and its corresponding value 
         * if the map contains the mapping for the key.
         */
        public void remove(int key) {
            final int hashCode = hash(key);
            final int index = hashTableIndex(hashCode);   

            HashTableNode lastCursor = null;
            HashTableNode cursor = hashTable[index];
            while (cursor != null) {
                if (cursor.key == key) {
                    if (lastCursor != null) {
                        // case1: not root node
                        lastCursor.next = cursor.next;    
                    } else {
                        // case2: root node
                        hashTable[index] = cursor.next;
                    }
                    return;
                } 
                
                // case3: key not match, move to next
                lastCursor = cursor;
                cursor = cursor.next;
            }
        }

        private int hash(int key) {
            return (key * 31 + 7) >> 3;
        }

        private int hashTableIndex(int hashCode) {
            int result = hashCode % CAPACITY; 
            return result >= 0 ? result: result + CAPACITY;
        }
    }

    public static void main(String[] args) {
        MyHashMap mhm = new MyHashMap();

        System.out.println(mhm.get(1)); 
        
        mhm.put(1, 100);
        System.out.println(mhm.get(1));

        mhm.put(1, 1001);
        System.out.println(mhm.get(1));
        

        mhm.remove(1);
        System.out.println(mhm.get(1));
    }
}
