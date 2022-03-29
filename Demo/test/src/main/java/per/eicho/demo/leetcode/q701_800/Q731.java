package per.eicho.demo.leetcode.q701_800;

/**
 * <p>731. My Calendar II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/my-calendar-ii/">731. My Calendar II</a>
 */
@SuppressWarnings("unused")
public final class Q731 {
    private static final class MyCalendarTwo {

        private static final class SegmentTreeNode {
            final int l;
            final int r;

            SegmentTreeNode left;
            SegmentTreeNode right;

            int count;

            SegmentTreeNode(int l, int r) { this(l, r, 0); }
            SegmentTreeNode(int l, int r, int count) {
                this.l = l;
                this.r = r;
                this.count = count;
            }

            boolean isBooked() { return count == 2; }

            boolean canBook(int start, int end) {
                if (r < start || end < l) return true;
                if (isBooked()) return false;
                // count < 2
                if (start <= l && r <= end) {
                    if (left == null) return true;
                    return left.canBook(start, end) && right.canBook(start, end);
                }

                // expand current node.
                expand();

                return left.canBook(start, end) && right.canBook(start, end);
            }

            private void expand() {
                if (left == null && l < r) {
                    final int mid = (l + r) / 2;
                    left = new SegmentTreeNode(l, mid, this.count);
                    right = new SegmentTreeNode(mid + 1, r, this.count);
                }
            }

            void book(int start, int end) {
                if (r < start || end < l) return;

                if (start <= l && r <= end) {
                    if (left == null) {
                        count++;
                        if (count == 2) {
                            left = null;
                            right = null;
                        }
                    } else {
                        left.book(start, end);
                        right.book(start, end);
                        if (left.count == 2 && right.count == 2) {
                            count = 2;
                            left = null;
                            right = null;
                        }  
                    }
                    return;
                } else {
                    left.book(start, end);
                    right.book(start, end);
                    if (left.count == 2 && right.count == 2) {
                        count = 2;
                        left = null;
                        right = null;
                    }
                }
            }
        }

        final SegmentTreeNode root; 

        public MyCalendarTwo() {
            // 0 <= start < end <= 10^9
            root = new SegmentTreeNode(0, 1_000_000_000); 
        }
        
        public boolean book(int start, int end) {
            // At most 1000 calls will be made to book.
            if (root.canBook(start, end - 1)) {
                root.book(start, end - 1);
                return true;
            }
            return false;
        }
    }
}
