package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;
import java.util.List;

/**
 * <p>539. Minimum Time Difference 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-time-difference/">
 *   539. Minimum Time Difference</a>
 */
public final class Q539 {

    final int minOf1Day = 24 * 60;

    public int findMinDifference(List<String> timePoints) {
        // 1. 2 <= timePoints.length <= 2 * 10^4
        // 2. timePoints[i] is in the format "HH:MM".        
        final int[] nums = times2Nums(timePoints);
        final int n = timePoints.size();
        Arrays.sort(nums);
        int minDiff = getDiff(nums[0], nums[n - 1]);
        for (int i = 1; i < n; i++) minDiff = Math.min(minDiff, getDiff(nums[i - 1], nums[i]));
        return minDiff;
    }

    private int[] times2Nums(List<String> timePoints) {
        final int[] nums = new int[timePoints.size()];
        for (int i = 0; i < nums.length; i++) nums[i] = time2Num(timePoints.get(i));
        return nums;
    }

    private int time2Num(String hhmm) {
        final int h = char2Digit(hhmm.charAt(0)) * 10 + char2Digit(hhmm.charAt(1));
        final int m = char2Digit(hhmm.charAt(3)) * 10 + char2Digit(hhmm.charAt(4));
        return h * 60 + m;
    }

    private int char2Digit(char digit) {
        return digit - '0';
    }

    private int getDiff(int time1, int time2) {
        // time2 >= time1
        return Math.min(time2 - time1, minOf1Day - (time2 - time1));
    }

    public static void main(String[] args) {
        Q539 q539 = new Q539();
        System.out.println(q539.findMinDifference(Arrays.asList("23:59", "00:00")));
    }
}
