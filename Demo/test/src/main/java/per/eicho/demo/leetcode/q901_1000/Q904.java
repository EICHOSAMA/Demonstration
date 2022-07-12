package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>904. Fruit Into Baskets 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/fruit-into-baskets/">
 *   904. Fruit Into Baskets</a>
 */
public final class Q904 {
    public int totalFruit(int[] fruits) {
        // 1. 1 <= fruits.length <= 10^5
        // 2. 0 <= fruits[i] < fruits.length
        final int n = fruits.length;

        int result = 1;
        int l = 0, r = 1;
        final int[] types = new int[2];
        final int[] counts = new int[2];
        types[0] = fruits[0];
        counts[0]++;
        int typeCount = 1;
        while (r < n) {
            while (r < n) {
                final int type = fruits[r++];
                if (type == types[0]) {
                    counts[0]++;
                    continue;
                }

                if (typeCount < 2) {
                    typeCount++;
                    types[1] = type;
                    counts[1]++;
                    continue;
                }

                if (type == types[1]) {
                    counts[1]++;
                    continue;
                }

                r--;
                break;
            }

            result = Math.max(result, r - l);

            while (typeCount > 1) {
                final int type = fruits[l++];
                final int idx = types[0] == type ? 0 : 1;
                if (--counts[idx] == 0) {
                    typeCount--;
                    types[0] = types[idx ^ 1];
                    counts[0] = counts[idx ^ 1];
                    types[1] = -1;
                    counts[1] = 0;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Q904 q904 = new Q904();
        System.out.println(q904.totalFruit(new int[]{0}));
    }
}
