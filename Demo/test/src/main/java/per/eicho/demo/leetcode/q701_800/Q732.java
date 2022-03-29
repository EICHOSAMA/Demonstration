package per.eicho.demo.leetcode.q701_800;

/**
 * <p>732. My Calendar III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/my-calendar-iii/">732. My Calendar III</a>
 */
public final class Q732 {
    private static final class MyCalendarThree {

        private static final class SegmentTreeNode {
            final int l;
            final int r;

            int bookCount; // current range [l, r]
            int subMaxCount; // Max(left.bookCount + left.subMaxCount, right.bookCount + right.subMaxCount);  

            SegmentTreeNode left;
            SegmentTreeNode right;

            SegmentTreeNode(int l, int r) {
                this.l = l;
                this.r = r;
                bookCount = 0;
                subMaxCount = 0;
            }

            int book(int start, int end) {
                if (r < start || end < l) return bookCount + subMaxCount; // out of range.

                if (start <= l && r <= end) {
                    bookCount++;
                    return bookCount + subMaxCount;
                }

                if (left == null) {
                    final int mid = (l + r) / 2;
                    left = new SegmentTreeNode(l, mid);
                    right = new SegmentTreeNode(mid + 1, r);
                }

                final int maxL = left.book(start, end);
                final int maxR = right.book(start, end);
                subMaxCount = Math.max(maxL, maxR);

                return bookCount + subMaxCount;
            }
        }

        final SegmentTreeNode root; 

        public MyCalendarThree() {
            // 0 <= start < end <= 10^9
            root = new SegmentTreeNode(0, 1_000_000_000);
        }
        
        public int book(int start, int end) {
            // At most 400 calls will be made to book.
            return root.book(start, end - 1);
        }
    }

    public static void main(String[] args) {
        final MyCalendarThree myCalendar = new MyCalendarThree();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(50, 60));
        System.out.println(myCalendar.book(10, 40));
        System.out.println(myCalendar.book(5, 15));
        System.out.println(myCalendar.book(5, 10));
        System.out.println(myCalendar.book(25, 55));

    }
}
