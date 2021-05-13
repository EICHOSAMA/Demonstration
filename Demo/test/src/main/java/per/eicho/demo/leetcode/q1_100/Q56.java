package per.eicho.demo.leetcode.q1_100;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>56. Merge Intervals 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/merge-intervals/">56. Merge Intervals</a>
 */
final public class Q56 {

    private static class Range {
        final int l;
        final int r;

        Range(int l, int r) {
            assert l <= r;
            this.l = l;
            this.r = r;
        }
        
        static int compare(Range r1, Range r2) {
            if (r1.l < r2.l) {
                return -1;
            } else if (r1.l > r2.l) {
                return 1;
            }

            //assert r1.l == r2.l;
            return (r1.r == r2.r)? 0: 
                   (r1.r < r2.r) ? -1: 1;
        }

        /**
         * <p>Merge Two Ranges</p>
         * 
         * <pre>
         *  Merge two given ranges if considered overlapping. else return null.
         * </pre>
         * 
         * @param r1 range 1
         * @param r2 range 2
         * @return null or Range Instance.
         */
        static Range merge(Range r1, Range r2) {
            if (r2.r < r1.l || r1.r < r2.l) {
                return null;
            }
            return new Range(Math.min(r1.l, r2.l), Math.max(r1.r, r2.r));
        }
    }

    public int[][] merge(int[][] intervals) {
        // Domain Model Mapping. 2d array ⇒ Domain Model List 
        List<Range> ranges = toRanges(intervals);

        // Sort , 1st KEY range.l, 2nd KEY range.r.
        ranges.sort(Range::compare);

        // Main Process: merge.
        final List<Range> resultList = new ArrayList<>();

        Range currentRange = null;
        for (Range range : ranges) {
           if (currentRange == null) {
               currentRange = range;
               continue;
           }

           final Range mergedRange = Range.merge(currentRange, range);
           
           if (mergedRange == null) {
               resultList.add(currentRange);
               currentRange = range;
           } else {
               currentRange = mergedRange;
           }
        }

        if (currentRange != null) {
            resultList.add(currentRange);
        }

        // Convert List 2 Array.
        return resultList.stream()
                .map(r -> new int[]{r.l, r.r})
                .toArray(size -> new int[size][2]);
    }

    private List<Range> toRanges(int[][] intervals) {
        final List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            final int[] interval = intervals[i];

            final int l = interval[0];
            final int r = interval[1];

            ranges.add(new Range(l, r));
        }

        return ranges;
    }
}
