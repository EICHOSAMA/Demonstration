package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1185. Day of the Week 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/day-of-the-week//">
 *   1185. Day of the Week</a>
 */
public final class Q1185 {
    public String dayOfTheWeek(int day, int month, int year) {
        // The given dates are valid dates between the years 1971 and 2100
        final java.time.LocalDate ld = java.time.LocalDate.of(year, month, day);
        final StringBuilder sb = new StringBuilder(ld.getDayOfWeek().toString().toLowerCase());
        sb.setCharAt(0, (char)(sb.charAt(0) + 'A' - 'a'));
        return sb.toString();
    }
}
