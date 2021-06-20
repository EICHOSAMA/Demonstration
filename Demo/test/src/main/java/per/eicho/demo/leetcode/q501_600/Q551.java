package per.eicho.demo.leetcode.q501_600;

/**
 * <p>551. Student Attendance Record I 的题解代码 </p>
 * 
 * <pre>
 *  You are given a string s representing an attendance record for a student where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
 *   1. 'A': Absent.
 *   2. 'L': Late.
 *   3. 'P': Present.
 *  The student is eligible for an attendance award if they meet both of the following criteria:
 *   1. The student was absent ('A') for strictly fewer than 2 days total.
 *   2. The student was never late ('L') for 3 or more consecutive days.
 *  Return true if the student is eligible for an attendance award, or false otherwise.
 * 
 * Example 1: 
 *    Input: s = "PPALLP"
 *    Output: true
 *    Explanation: The student has fewer than 2 absences and was never late 3 or more consecutive days.
 * 
 * Example 2:
 *    Input: s = "PPALLL"
 *    Output: false
 *    Explanation: The student was late 3 consecutive days in the last 3 days, so is not eligible for the award.
 * 
 * Constraints:
 *    1. 1 <= s.length <= 1000
 *    2. s[i] is either 'A', 'L', or 'P'.
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/student-attendance-record-i/">551. Student Attendance Record I</a>
 */
final public class Q551 {
    static final char ABSENT = 'A';
    static final char LATE = 'L';
    static final char PRESENT = 'P';

    public boolean checkRecord(String s) {
        int consecutiveLate = 0;
        int absentDay = 0;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);

            if (ABSENT == c) {
                consecutiveLate = 0;

                absentDay++;
                if (absentDay >= 2) {
                    return false;
                }
            } else if (LATE == c) {
                consecutiveLate++;
                if (consecutiveLate >= 3) {
                    return false;
                }
            } else if (PRESENT == c) {
                consecutiveLate = 0;
            }
        }

        return true;
    }
}
