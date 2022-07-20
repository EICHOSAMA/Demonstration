package per.eicho.demo.leetcode.q1401_1500;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import per.eicho.demo.leetcode.debug.OutputUtils;
/**
 * <p>1488. Avoid Flood in The City 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/avoid-flood-in-the-city/">
 *   1488. Avoid Flood in The City</a>
 */
public final class Q1488 {
    public int[] avoidFlood(int[] rains) {
        // 1. 1 <= rains.length <= 10^5
        // 2. 0 <= rains[i] <= 10^9
        final Map<Integer, Integer> lakeMap = new HashMap<>();
        final Deque<Integer> zeroIdxList = new LinkedList<>(); // <idx of 0>
        final int n = rains.length;
        final int[] result = new int[n];
        Arrays.fill(result, -1);

        for (int i = 0; i < n; i++) {
            final int lake = rains[i];

            if (lake == 0) {
                // no rain, can dry any lake.
                if (lakeMap.size() == 0) {
                    result[i] = 1; // input [0,0,0,4] get output [1,1,1,-1]
                    continue;
                }

                zeroIdxList.add(i);
                result[i] = 1;
            } else {
                if (lakeMap.containsKey(lake)) {
                    // need to find a day to dry this ${lake} lake.
                    // TODO.
                    final int preDate = lakeMap.put(lake, i);
                    if (zeroIdxList.size() == 0) return new int[]{};
                    if (zeroIdxList.peekLast() < preDate) return new int[]{};
                    

                    final Iterator<Integer> idxListIterator = zeroIdxList.iterator();
                    while (idxListIterator.hasNext()) {
                        final int next = idxListIterator.next();
                        if (next < preDate) continue;
                        // next > predate
                        result[next] = lake;
                        idxListIterator.remove();
                        break;
                    }
                } else {
                    lakeMap.put(lake, i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Q1488 q1488 = new Q1488();
        OutputUtils.println(q1488.avoidFlood(new int[]{1,2,0,0,2,1}));
    }
}
