package per.eicho.demo.leetcode.q801_900;

/**
 * <p>845. Longest Mountain in Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-mountain-in-array/">
 *  845. Longest Mountain in Array</a>
 */
public final class Q845 {
    public int longestMountain(int[] arr) {
        // 1. 1 <= arr.length <= 10^4
        // 2. 0 <= arr[i] <= 10^4
        int result = 0;
        int p = 0;
        final int n = arr.length;
        while (p + 2 < n) {
            final int l = p;
            int num = arr[p++];
            while (p < n && arr[p] > num) num = arr[p++];

            if (p - l == 1) continue;
            if (p == n) break;
            if (arr[p] == num) continue;
            
            while (p < n && arr[p] < num) num = arr[p++];
            result = Math.max(result, p - l);
            p--;
        }

        return result;
    }

    public static void main(String[] args) {
        Q845 q845 = new Q845();
        System.out.println(q845.longestMountain(new int[]{875,884,239,731,723,685})); // 4
    }
}
