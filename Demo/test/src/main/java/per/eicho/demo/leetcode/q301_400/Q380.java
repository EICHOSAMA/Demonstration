package per.eicho.demo.leetcode.q301_400;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * <p>380. Insert Delete GetRandom O(1) 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/">
 *   380. Insert Delete GetRandom O(1)</a>
 */
@SuppressWarnings("unused")
public final class Q380 {

    private static final class RandomizedSet {

        private final HashMap<Integer, Integer> id2Val;
        private final HashMap<Integer, Integer> val2Id;

        private final Random random;
        
        int id;
    
        /** Initializes the RandomizedSet object. */
        public RandomizedSet() {
            id = 0;

            id2Val = new HashMap<>();
            val2Id = new HashMap<>();
            random = new Random(System.nanoTime());
        }
        
        /** 
         * Inserts an item val into the set if not present. 
         * Returns true if the item was not present, false otherwise. 
         */
        public boolean insert(int val) {
            final Integer iVal = val;
            if (val2Id.containsKey(iVal)) return false;
            
            final Integer iID = id++;
            val2Id.put(iVal, iID);
            id2Val.put(iID, iVal);
            return true;
        }
        
        /**
         * Removes an item val from the set if present. 
         * Returns true if the item was present, false otherwise.
         */
        public boolean remove(int val) {
            final Integer iVal = val;
            if (!val2Id.containsKey(iVal)) return false;

            final Integer iID = val2Id.remove(iVal);
            id2Val.remove(iID);

            if (id > 10 && (id / id2Val.size() >= 2)) compact();

            return true;
        }

        private void compact() {
            id2Val.clear();
            id = 0;
            for (Map.Entry<Integer, Integer> val2Id : val2Id.entrySet()) {
                final Integer val = val2Id.getKey();
                
                id2Val.put(id++, val);
            }

            for (Map.Entry<Integer, Integer> id2Val : id2Val.entrySet()) {
                final Integer iID = id2Val.getKey();
                final Integer iVal = id2Val.getValue();
                
                val2Id.put(iVal, iID);
            }
        }
        
        /**
         * Returns a random element from the current set of elements 
         *   (it's guaranteed that at least one element exists when this method is called). 
         * Each element must have the same probability of being returned.
         */
        public int getRandom() {
            final int bound = id; // [0, id)
            System.out.println(bound);

            Integer iID;
            do {
                iID = random.nextInt(bound);
                System.out.println("random" + iID);
            } while (!id2Val.containsKey(iID));

            return id2Val.get(iID);
        }
    }
}
