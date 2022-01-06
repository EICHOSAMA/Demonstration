package per.eicho.demo.leetcode.q001_100;

/**
 * <p>11. Container With Most Water 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/container-with-most-water/">11. Container With Most Water</a>
 */
public final class Q11 {
    public int maxArea(int[] height) {
        final int len = height.length;

        int temp;
        int max = 0;

        int l, r;
        for (int i = 0; i < len - 1; i++) {
            l = height[i];
            for (int j = i + 1; j < len; j++) {
                r = height[j];
                temp = (j - i) * Math.min(l, r);
                if (temp > max) {
                    max = temp;
                }
            }
        }

        return max;   
    }
}
