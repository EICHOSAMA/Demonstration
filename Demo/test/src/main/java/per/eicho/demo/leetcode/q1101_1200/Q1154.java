package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1154. Day of the Year 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/day-of-the-year/">1154. Day of the Year</a>
 */
public final class Q1154 {
    public int dayOfYear(String date) {
        // 1. date.length == 10
        // 2. date[4] == date[7] == '-', and all other date[i]'s are digits
        // 3. date represents a calendar date between Jan 1st, 1900 and Dec 31th, 2019.        
        return java.time.LocalDate.parse(date).getDayOfYear();
    }
}
