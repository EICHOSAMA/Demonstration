package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>57. Insert Interval 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/insert-interval/">57. Insert Interval</a>
 */
final public class Q57 {

    private static class Range {
        final int l;
        final int r;

        Range(int l, int r) {
            assert l <= r;
            this.l = l;
            this.r = r;
        }
        
        boolean contain(Range r) {
            return this.l <= r.l && r.r <= this.r;
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

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Domain Model Mapping. 2d array ⇒ Domain Model List 
        List<Range> ranges = toRanges(intervals);
        final Range newRange = new Range(newInterval[0], newInterval[1]);

        // Main Process: merge.
        final List<Range> resultList = new ArrayList<>();

        if (intervals.length == 0) {
            resultList.add(newRange);
            return toArray(resultList);
        }

        if (newRange.r < ranges.get(0).l) {
            resultList.add(newRange);
            resultList.addAll(ranges);
            return toArray(resultList);
        } else if (ranges.get(ranges.size() - 1).r < newRange.l) {
            resultList.addAll(ranges);
            resultList.add(newRange);
            return toArray(resultList);
        } 

        // CASE 1. insert 
        for (int i = 0; i < ranges.size() - 1; i++) {
            final Range rangeI = ranges.get(i);
            final Range rangeI1 = ranges.get(i + 1);

            if (rangeI.r < newRange.l && newRange.r < rangeI1.l) {
                resultList.addAll(ranges);
                resultList.add(i + 1, newRange);
                return toArray(resultList);
            }
        }
    
        // CASE 2. contained
        for (int i = 0; i < ranges.size(); i++) {
            final Range rangeI = ranges.get(i);

            if (rangeI.contain(newRange)) {
                resultList.addAll(ranges);
                return toArray(resultList);
            }
        }

        // CASE 3. merge
        Range currentRange = newRange;
        for (int i = 0; i < ranges.size(); i++) {
            final Range rangeI = ranges.get(i);

            final Range mergedRange = Range.merge(rangeI, currentRange);
            if (mergedRange == null) {
                // FIX BUG
                if (Range.compare(rangeI, currentRange) < 0) {
                    // < 0;
                    resultList.add(rangeI);
                } else {
                    // > 0;
                    resultList.add(currentRange);
                    currentRange = rangeI;
                }
                continue;
            }

            currentRange = mergedRange;
        }
        if (currentRange != null) {
            resultList.add(currentRange);
        }        
        
        // Convert List 2 Array.
        return toArray(resultList);
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

    private int[][] toArray(List<Range> ranges) {
        return ranges.stream()
            .map(r -> new int[]{r.l, r.r})
            .toArray(size -> new int[size][2]);
    }
}
