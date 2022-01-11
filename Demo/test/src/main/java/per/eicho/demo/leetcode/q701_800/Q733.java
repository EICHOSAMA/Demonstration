package per.eicho.demo.leetcode.q701_800;

/**
 * <p>733. Flood Fill 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flood-fill/">733. Flood Fill</a>
 */
public final class Q733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        dfs(image, image[sr][sc] , newColor, sr, sc);
        return image;
    }

    private void dfs(final int[][] image, final int oldColor, final int newColor, int x, int y) {
        // coordinate check
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) return;
        if (image[x][y] != oldColor) return;
        if (image[x][y] == newColor) return;
        
        image[x][y] = newColor;
        dfs(image, oldColor, newColor, x - 1, y); // 1. up
        dfs(image, oldColor, newColor, x + 1, y); // 2. down
        dfs(image, oldColor, newColor, x, y - 1); // 3. left
        dfs(image, oldColor, newColor, x, y + 1); // 4. right
    }
}
