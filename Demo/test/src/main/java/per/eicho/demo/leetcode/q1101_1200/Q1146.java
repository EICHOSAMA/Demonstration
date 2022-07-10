package per.eicho.demo.leetcode.q1101_1200;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1146. Snapshot Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/snapshot-array/">
 *   1146. Snapshot Array</a>
 */
@SuppressWarnings("unused")
public final class Q1146 {
    private static final class SnapshotArray {

        final List<List<int[]>> array;
        int snapID = 0;

        public SnapshotArray(int length) {
            array = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                final List<int[]> history = new ArrayList<>();
                history.add(new int[]{snapID, 0});
                array.add(history);
            }
        }
        
        public void set(int index, int val) {
            final List<int[]> history = array.get(index);
            final int[] lastElement = history.get(history.size() - 1);
            if (lastElement[0] == snapID) {
                lastElement[1] = val;
            } else {
                history.add(new int[]{snapID, val});
            }
        }
        
        public int snap() {
            return snapID++;
        }
        
        public int get(int index, int snap_id) {
            final List<int[]> history = array.get(index);

            int p = -1;
            while (p + 1 < history.size() && snap_id >= history.get(p + 1)[0]) p++;
            // history.get[p] <= snap_id < history.get[p + 1]
            return history.get(p)[1];
        }
    }
}
