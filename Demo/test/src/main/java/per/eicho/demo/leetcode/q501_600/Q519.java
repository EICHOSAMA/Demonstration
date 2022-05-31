package per.eicho.demo.leetcode.q501_600;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * <p>519. Random Flip Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/random-flip-matrix/">519. Random Flip Matrix</a>
 */
@SuppressWarnings("unused")
public final class Q519 {

    private static final class Solution {
        
        final int m;
        final int n;
        final int total;
        final Random random;

        final Set<Integer> used; // < 0.8
        List<Integer> unused; // > 0.8

        /** Initializes the object with the size of the binary matrix m and n. */
        public Solution(int m, int n) {
            // 1. 1 <= m, n <= 10^4
            // 2. There will be at least one free cell for each call to flip.      
            this.m = m;
            this.n = n;
            total = m * n;
            random = new Random(System.currentTimeMillis());
            used = new HashSet<>();
        }
        
        /** Returns a random index [i, j] of the matrix where matrix[i][j] == 0 and flips it to 1. */
        public int[] flip() {
            // 3. At most 1000 calls will be made to flip and reset.

            if (used.size() / total < 0.8) {
                int idx;
                do {
                    idx = random.nextInt(total);
                } while (used.contains(idx));
                used.add(idx);

                if (used.size() / total >= 0.8) {
                    unused = new LinkedList<>();
                    for (int i = 0; i < total; i++) {
                        if (!used.contains(i)) unused.add(i);
                    }
                }
                return new int[]{idx / n, idx % n};
            } else {
                final int index = random.nextInt(unused.size());
                final int idx = unused.remove(index);
                used.add(idx);
                return new int[]{idx / n, idx % n};
            }
        }
        
        /** Resets all the values of the matrix to be 0. */
        public void reset() {
            used.clear();
            if (unused != null) unused.clear();
        }
    }
}
