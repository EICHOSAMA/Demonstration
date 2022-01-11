package per.eicho.demo.leetcode.q701_800;

/**
 * <p>705. Design HashSet 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-hashset/">705. Design HashSet</a>
 */
public final class Q705 {
    private static class MyHashSet {

        final static int CAPACITY = 1000;
        final HashTableNode[] hashTable;
        
        private class HashTableNode {

            final int key;
            HashTableNode next;

            HashTableNode(int key) {
                this.key = key;
            }
        }

        public MyHashSet() {
            hashTable = new HashTableNode[CAPACITY];
        }
        
        /**
         * Inserts the value key into the HashSet.
         */
        public void add(int key) {
            final int hashCode = hash(key);
            final int index = hashTableIndex(hashCode);

            if (hashTable[index] == null) {
                hashTable[index] = new HashTableNode(key);
                return;
            }
            
            HashTableNode lastCursor = null;
            HashTableNode cursor = hashTable[index];
            while (cursor != null) {
                if (cursor.key == key) return; // already exists
                
                lastCursor = cursor;
                cursor = cursor.next; // move to next;
            }

            lastCursor.next = new HashTableNode(key);
        }
        
        /**
         * Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
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
        
        /**
         * Returns whether the value key exists in the HashSet or not.
         */
        public boolean contains(int key) {
            final int hashCode = hash(key);
            final int index = hashTableIndex(hashCode);       
            
            HashTableNode cursor = hashTable[index];
            while (cursor != null) {
                if (cursor.key == key) return true;
                cursor = cursor.next;
            }
            return false;
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
        MyHashSet mhs = new MyHashSet();
        mhs.add(1);
        System.out.println(mhs.contains(1));
        System.out.println(mhs.contains(2));
        System.out.println(mhs.contains(3));
        
        mhs.add(2);
        System.out.println(mhs.contains(1));
        System.out.println(mhs.contains(2));
        System.out.println(mhs.contains(3));
        
        mhs.remove(2);
        System.out.println(mhs.contains(1));
        System.out.println(mhs.contains(2));
        System.out.println(mhs.contains(3));
        
        mhs.remove(2);
        System.out.println(mhs.contains(1));
        System.out.println(mhs.contains(2));
        System.out.println(mhs.contains(3));
    }
    
}
