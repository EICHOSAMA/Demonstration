package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1109. Corporate Flight Bookings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/corporate-flight-bookings/">
 *   1109. Corporate Flight Bookings</a>
 */
public final class Q1109 {

    private static final class SegementTree {

        final int l;
        final int r;

        int count = 0;
        SegementTree left;
        SegementTree right;

        SegementTree(int l, int r) {
            this.l = l;
            this.r = r;
        }

        void add(int l, int r, int count) {
            if (r < this.l || l > this.r) return;
            if (l <= this.l && this.r <= r) {
                this.count += count;
                return;
            }

            if (left == null) {
                final int mid = this.l + (this.r - this.l) / 2;
                left = new SegementTree(this.l, mid);
                right = new SegementTree(mid + 1, this.r);
            }

            left.add(l, r, count);
            right.add(l, r, count);
        }

        int count(int label) {
            if (label < this.l || label > this.r ) return 0;
            if (left == null) return count;
            return count + left.count(label) + right.count(label);
        }
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 1. 1 <= n <= 2 * 10^4
        // 2. 1 <= bookings.length <= 2 * 10^4
        // 3. bookings[i].length == 3
        // 4. 1 <= firsti <= lasti <= n
        // 5. 1 <= seatsi <= 10^4
        final int[] result = new int[n];
        final SegementTree segementTree = new SegementTree(1, n);
        for (int[] booking : bookings) segementTree.add(booking[0], booking[1], booking[2]);
        for (int i = 0; i < n; i++) result[i] = segementTree.count(i + 1);
        return result;
    }
}
