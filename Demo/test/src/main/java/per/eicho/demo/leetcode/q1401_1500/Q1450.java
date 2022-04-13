package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1450. Number of Students Doing Homework at a Given Time 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/">
 *   1450. Number of Students Doing Homework at a Given Time</a>
 */
public final class Q1450 {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        // 1. startTime.length == endTime.length
        // 2. 1 <= startTime.length <= 100
        // 3. 1 <= startTime[i] <= endTime[i] <= 1000
        // 4. 1 <= queryTime <= 1000
        final int n = startTime.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) result++;
        }
        return result;
    }
}
