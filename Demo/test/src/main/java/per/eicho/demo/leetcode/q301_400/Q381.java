package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>381. Insert Delete GetRandom O(1) - Duplicates allowed 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/">
 *   381. Insert Delete GetRandom O(1) - Duplicates allowed</a>
 */
@SuppressWarnings("unused")
public final class Q381 {
    private static final class RandomizedCollection {

        private final Map<Integer, Integer> id2Val;
        private final Map<Integer, List<Integer>> val2Ids;

        private final Random random;
        
        int id;
    
        /** Initializes the RandomizedSet object. */
        public RandomizedCollection() {
            id = 0;

            id2Val = new HashMap<>();
            val2Ids = new HashMap<>();
            random = new Random(System.nanoTime());
        }
        
        /** 
         * Inserts an item val into the multiset, even if the item is already present. 
         * Returns true if the item is not present, false otherwise.
         */
        public boolean insert(int val) {
            final Integer iVal = val;
            boolean result = false;
            if (!val2Ids.containsKey(iVal)) {
                val2Ids.put(iVal, new LinkedList<>());
                result = true;
            }
            
            final Integer iID = id++;
            val2Ids.get(iVal).add(iID);
            id2Val.put(iID, iVal);
            return result;
        }
        
        /**
         * Removes an item val from the multiset if present. Returns true if the item is present, false otherwise. 
         * Note that if val has multiple occurrences in the multiset, we only remove one of them.
         */
        public boolean remove(int val) {
            final Integer iVal = val;
            if (!val2Ids.containsKey(iVal)) return false;

            final Integer iID = val2Ids.get(iVal).remove(0);
            id2Val.remove(iID);

            if (id > 10 && (id / id2Val.size() >= 2)) compact();
            if (val2Ids.get(iVal).size() == 0) val2Ids.remove(iVal);

            return true;
        }

        private void compact() {
            id2Val.clear();
            id = 0;
            for (Map.Entry<Integer, List<Integer>> val2Id : val2Ids.entrySet()) {
                final Integer val = val2Id.getKey();
                
                final int size = val2Id.getValue().size(); 
                for (int i = 0; i < size; i++) id2Val.put(id++, val);
                val2Id.getValue().clear();
            }

            for (Map.Entry<Integer, Integer> id2Val : id2Val.entrySet()) {
                final Integer iID = id2Val.getKey();
                final Integer iVal = id2Val.getValue();
                
                val2Ids.get(iVal).add(iID);
            }
        }
        
        /**
         *  Returns a random element from the current multiset of elements. 
         *  The probability of each element being returned is linearly 
         *  related to the number of same values the multiset contains.
         */
        public int getRandom() {
            final int bound = id; // [0, id)
            Integer iID;
            do {
                iID = random.nextInt(bound);
            } while (!id2Val.containsKey(iID));

            return id2Val.get(iID);
        }
    }
}
