package per.eicho.demo.leetcode.q1201_1300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>1202. Smallest String With Swaps 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-string-with-swaps/">
 *   1202. Smallest String With Swaps</a>
 */
public final class Q1202 {

    private static final class DisjointSetForest {
   
        int[] parent;
        int[] rank;
    
        public DisjointSetForest(int n) {
            parent = new int[n];
            rank = new int[n];
    
            for (int i = 0; i < n; i++) parent[i] = i;
        }
    
        public int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]); // 路径压缩, path compression
        }
    
        public void union(int x, int y) {
            final int rootX = find(x);
            final int rootY = find(y);
            if (rootX == rootY) return; // 已经在同一集合里。
            
            final int rankX = rank[x];
            final int rankY = rank[y];
            // Key: 小的合并到大的。
            if (rankX < rankY) {
                parent[rootX] = rootY;
            } else if (rankX > rankY) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY; // or parent[rootY] = rootX;
                rank[rootX]++ ; // Rank UP or rank[rootY]++
            }
        }
    }


    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // 1. 1 <= s.length <= 10^5
        // 2. 0 <= pairs.length <= 10^5
        // 3. 0 <= pairs[i][0], pairs[i][1] < s.length
        // 4. s only contains lower case English letters.
        final int n = s.length();
        final DisjointSetForest ds = new DisjointSetForest(n);
        for (List<Integer> pair : pairs) ds.union(pair.get(0), pair.get(1));
        
        final Map<Integer, StringBuilder> sbs = new HashMap<>();
        final Map<Integer, char[]> sortedParties = new HashMap<>();
        final Map<Integer, int[]> cursor = new HashMap<>(); // int[] as a mutable integer.

        for (int i = 0; i < n; i++) {
            final int rootI = ds.find(i);

            if (!sbs.containsKey(rootI)) sbs.put(rootI, new StringBuilder());
            final StringBuilder sb = sbs.get(rootI);
            sb.append((char)s.charAt(i));
        }

        for (Map.Entry<Integer, StringBuilder> entry : sbs.entrySet()) {
            final char[] chars = entry.getValue().toString().toCharArray();
            Arrays.sort(chars);
            sortedParties.put(entry.getKey(), chars);
            cursor.put(entry.getKey(), new int[]{0});
        }

        final StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            final Integer rootI = ds.find(i);
            sb.append(sortedParties.get(rootI)[cursor.get(rootI)[0]++]);
        }

        return sb.toString();
    }
}
