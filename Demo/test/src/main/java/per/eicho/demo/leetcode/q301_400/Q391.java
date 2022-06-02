package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>391. Perfect Rectangle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/perfect-rectangle/">
 *   391. Perfect Rectanglek</a>
 */
public final class Q391 {

    private static final int LEFT_EDGE = 0;
    private static final int RIGHT_EDGE = 1;
    
    public boolean isRectangleCover(int[][] rectangles) {
        // 1. 1 <= rectangles.length <= 2 * 10^4
        // 2. rectangles[i].length == 4
        // 3. -10^5 <= xi, yi, ai, bi <= 10^5
        final int n = rectangles.length;
        final int[][] edges = new int[n * 2][4]; // [x, y1, y2, L or R];

        int p = 0;;
        for (int[] rectangle : rectangles) {
            // rectangles[i] = [xi, yi, ai, bi]
            // The bottom-left point of the rectangle is (xi, yi)
            // the top-right   point of the rectangle is (ai, bi).
            int[] edge = edges[p++];

            edge[0] = rectangle[0];
            edge[1] = rectangle[1];
            edge[2] = rectangle[3];
            edge[3] = LEFT_EDGE;

            edge = edges[p++];
            edge[0] = rectangle[2];
            edge[1] = rectangle[1];
            edge[2] = rectangle[3];
            edge[3] = RIGHT_EDGE;
        }

        Arrays.sort(edges, (e1, e2) -> {
            if (e1[0] != e2[0]) return Integer.compare(e1[0], e2[0]); // x
            if (e1[1] != e2[1]) return Integer.compare(e1[1], e2[1]); // y1
            if (e1[2] != e2[2]) return Integer.compare(e1[2], e2[2]); // y2
            return Integer.compare(e1[3], e2[3]);
        });

        final List<int[]> lLines = new ArrayList<>();
        final List<int[]> rLines = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            
            final int x = edges[i][0];
            final int l = i;
            int r = i;

            lLines.clear();
            rLines.clear();

            while (r + 1 < edges.length && edges[r + 1][0] == x) r++;
            // Range [l, r]

            for (int j = l; j <= r; j++) {
                final int[] edge = edges[j];
                final int[] line = new int[]{edge[1], edge[2]};

                final List<int[]> list = edge[3] == LEFT_EDGE ? lLines : rLines;
                if (list.isEmpty()) {
                    list.add(line);
                } else {
                    int[] lastLine = list.get(list.size() - 1);
                    if (line[0] < lastLine[1]) return false;
                    if (line[0] == lastLine[1]) {
                        lastLine[1] = line[1];
                    } else {
                        list.add(line);
                    }
                }
            }

            if (l == 0 || r == edges.length - 1) {
                if (lLines.size() + rLines.size() != 1) return false;
            } else {
                if (lLines.size() != rLines.size()) return false;
                for (int j = 0; j < lLines.size(); j++) {
                    final int[] lLine = lLines.get(j);
                    final int[] rLine = rLines.get(j);
                    if (lLine[0] != rLine[0] || lLine[1] != rLine[1]) return false;
                }
            }

            i = r;
        }
        return true;
    }

    public static void main(String[] args) {
        Q391 q391 = new Q391();
        System.out.println(q391.isRectangleCover(new int[][]{
            new int[]{1,1,3,3},
            new int[]{3,1,4,2},
            new int[]{3,2,4,4},
            new int[]{1,3,2,4},
            new int[]{2,3,3,4} 
        }));
    }
}
