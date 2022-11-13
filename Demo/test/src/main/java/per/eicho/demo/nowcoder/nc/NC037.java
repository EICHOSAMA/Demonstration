package per.eicho.demo.nowcoder.nc;

import java.util.ArrayList;

import per.eicho.demo.nowcoder.datastructure.Interval;

public final class NC037 {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        intervals.sort((i1, i2) -> {
            if (i1.start != i2.start) return Integer.compare(i1.start, i2.start);
            return Integer.compare(i1.end, i2.end);
        });

        Interval workingInterval = new Interval(intervals.get(0).start, intervals.get(0).end);
        final ArrayList<Interval> result = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            final Interval interval = intervals.get(i);
            if (canMerge(workingInterval, interval)) {
                workingInterval.end = Math.max(workingInterval.end, interval.end);
            } else {
                result.add(workingInterval);
                workingInterval = interval;
            }
        }
        result.add(workingInterval);

        return result;
    }

    private boolean canMerge(Interval workingInterval, final Interval interval) {
        return workingInterval.end >= interval.start;
    }
}
