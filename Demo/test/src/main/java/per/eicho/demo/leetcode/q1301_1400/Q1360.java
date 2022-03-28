package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1360. Number of Days Between Two Dates 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-days-between-two-dates/">
 *   1360. Number of Days Between Two Dates</a>
 */
public final class Q1360 {
    public int daysBetweenDates(String date1, String date2) {
        // The given dates are valid dates between the years 1971 and 2100.
        java.time.LocalDate d1 = java.time.LocalDate.parse(date1);
        java.time.LocalDate d2 = java.time.LocalDate.parse(date2);
        return (int)java.time.temporal.ChronoUnit.DAYS.between(d1, d2) * (d1.compareTo(d2) > 0 ? -1 : 1);
    }
}
