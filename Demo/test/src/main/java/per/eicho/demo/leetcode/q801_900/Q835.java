package per.eicho.demo.leetcode.q801_900;

/**
 * <p>835. Image Overlap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/image-overlap/">
 *   835. Image Overlap</a>
 */
public final class Q835 {
    public int largestOverlap(int[][] img1, int[][] img2) {
        // 1. n == img1.length == img1[i].length
        // 2. n == img2.length == img2[i].length
        // 3. 1 <= n <= 30
        // 4. img1[i][j] is either 0 or 1.
        // 5. img2[i][j] is either 0 or 1.
        final int n = img1.length;
        int result = 0;
        for (int offsetX = -n + 1; offsetX < n; offsetX++) {
            // [-(n-1), (n-1)]
            for (int offsetY = -n + 1; offsetY < n; offsetY++) {
                final int[] compareArea1 = 
                    new int[]{
                        offsetX > 0 ? 0 : -offsetX, offsetY > 0 ? 0 : -offsetY, 
                        n - Math.abs(offsetX), n - Math.abs(offsetY)};
                if (compareArea1[2] * compareArea1[3] <= result) continue;
                final int[] compareArea2 = 
                    new int[]{
                        offsetX < 0 ? 0 : offsetX, offsetY < 0 ? 0 : offsetY, 
                        n - Math.abs(offsetX), n - Math.abs(offsetY)};
                final int overlap = compare(img1, img2, compareArea1, compareArea2);
                if (overlap > result) result = overlap;
            }
        }

        return result;
    }

    private int compare(int[][] img1, int[][] img2, 
        int[] compareArea1, int[] compareArea2) {
        int x1 = compareArea1[0];        
        int x2 = compareArea2[0];

        int overlap = 0;
        for (int i = 0; i < compareArea1[2]; i++, x1++, x2++) {

            int y1 = compareArea1[1];
            int y2 = compareArea2[1];
            for (int j = 0; j < compareArea2[3]; j++, y1++, y2++) {
                if (img1[x1][y1] == 0) continue;
                if (img2[x2][y2] == 0) continue;
                overlap++;
            }
        }
        return overlap;
    }
}
