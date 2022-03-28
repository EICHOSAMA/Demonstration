package per.eicho.demo.leetcode.q701_800;

/**
 * <p>729. My Calendar I 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/my-calendar-i/">729. My Calendar I</a>
 */
@SuppressWarnings("unused")
public final class Q729 {
    private static final class MyCalendar {

        private static final class SegmentTreeNode {
            final int l;
            final int r;

            SegmentTreeNode left;
            SegmentTreeNode right;
            boolean booked;

            SegmentTreeNode(int l, int r) {
                this.l = l;
                this.r = r;
                booked = false;
            }

            boolean isBooked() { return booked; }

            boolean canBook(int start, int end) {
                if (r < start || end < l) return true;
                if (isBooked()) return false;
                // booked == false;
                if (start <= l && r <= end) {
                    if (left == null) return true;
                    return left.canBook(start, end) && right.canBook(start, end);
                }

                // expand
                if (left == null && l < r) {
                    final int mid = (l + r) / 2;
                    left = new SegmentTreeNode(l, mid);
                    right = new SegmentTreeNode(mid + 1, r);
                }
                return left.canBook(start, end) && right.canBook(start, end);
            }

            void book(int start, int end) {
                if (r < start || end < l) return;

                if (start <= l && r <= end) {
                    booked = true;
                    left = null;
                    right = null;
                    return;
                }

                left.book(start, end);
                right.book(start, end);
            }
        }

        final SegmentTreeNode root; 

        public MyCalendar() {
            root = new SegmentTreeNode(0, 1_000_000_000);
        }
        
        public boolean book(int start, int end) {
            if (root.canBook(start, end - 1)) {
                root.book(start, end - 1);
                return true;
            }
            return false;
        }
    }
}
