package per.eicho.demo.leetcode.q101_200;

/**
 * <p>134. Gas Station 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/gas-station/">134. Gas Station</a>
 */
public final class Q134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        final int n = gas.length;

        int gas_unknown = 0;

        int candidate = -1;
        int i = 0;
        while (i < n) {
            int gasSurplus = gas[i] - cost[i]; 
            i++;
                
            if (gasSurplus < 0) {
                gas_unknown += gasSurplus;
                continue;
            }

            // candidate
            candidate = i - 1; 
            while (i < n && ((gasSurplus + (gas[i] - cost[i])) >= 0)) {
                gasSurplus += (gas[i] - cost[i]);
                i++;
            }

            // merge
            gas_unknown += gasSurplus;

            if (i != n) continue; 
            if (gas_unknown >= 0) return candidate;
        }
        return -1;
    }

    public static void main(String[] args) {
        Q134 q134 = new Q134();
        int result = q134.canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1});
        System.out.println(result);
    }
}
