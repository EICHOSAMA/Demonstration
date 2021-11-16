package per.eicho.demo.leetcode.q601_700;


/**
 * <p>661. Image Smoother 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/image-smoother/">661. Image Smoother</a>
 */
public final class Q661 {
    public int[][] imageSmoother(int[][] img) {
        final int x = img.length;
        final int y = img[0].length;

        final int[][] result = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                // img[i][j];
                result[i][j] = imageSmoother(img, x, y, i, j);
            }
        }
        return result;
    }

    private int imageSmoother(final int[][] img, final int x, final int y, final int i, final int j) {
        int sum = img[i][j];
        int count = 1;

        boolean i0 = i - 1 >= 0;
        boolean ix = i + 1 < x;
        boolean j0 = j - 1 >= 0;
        boolean jy = j + 1 < y;

        if (i0 && j0) {
            sum += img[i - 1][j - 1];
            count++;
        }

        if (ix && j0) {
            sum += img[i + 1][j - 1];
            count++;
        }

        if (i0 && jy) {
            sum += img[i - 1][j + 1];
            count++;
        }

        if (ix && jy) {
            sum += img[i + 1][j + 1];
            count++;
        }

        if (i0) {
            sum += img[i - 1][j];
            count++;
        }

        if (j0) {
            sum += img[i][j - 1];
            count++;
        }

        if (ix) {
            sum += img[i + 1][j];
            count++;
        }

        if (jy) {
            sum += img[i][j + 1];
            count++;
        }

        return Math.floorDiv(sum, count);
    }
}
