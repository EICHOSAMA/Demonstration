package per.eicho.demo.nowcoder.offer;

public final class JZ004 {
    public boolean find(int target, int [][] array) {
        // 0 <= n,m <= 500, 0 <= val <= 10^9
        if (array == null) return false;
        final int n = array.length;
        if (n == 0) return false;
        final int m = array[0].length;
        if (m == 0) return false;

        int x = 0, y = m - 1;
        do {
            final int num = array[x][y];
            if (num == target) return true;
            if (num < target) {
                x++;
            } else {
                y--;
            }
        } while (x < n && y >= 0);
        return false;
    }
}
