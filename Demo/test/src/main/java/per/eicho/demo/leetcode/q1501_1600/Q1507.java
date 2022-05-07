package per.eicho.demo.leetcode.q1501_1600;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>1507. Reformat Date 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reformat-date/">
 *   1507. Reformat Date</a>
 */
public final class Q1507 {
    
    static final Map<String, String> monthMap;

    static {
        monthMap = new HashMap<>();
        monthMap.put("Jan", "01");
        monthMap.put("Feb", "02");
        monthMap.put("Mar", "03");
        monthMap.put("Apr", "04");
        monthMap.put("May", "05");
        monthMap.put("Jun", "06");
        monthMap.put("Jul", "07");
        monthMap.put("Aug", "08");
        monthMap.put("Sep", "09");
        monthMap.put("Oct", "10");
        monthMap.put("Nov", "11");
        monthMap.put("Dec", "12");
    }
    
    public String reformatDate(String date) {
        // 1. The given dates are guaranteed to be valid, so no error handling is necessary.        
        final StringBuilder sb = new StringBuilder(10);
        sb.append(date.substring(date.length() - 4)).append('-');
        int l = date.length() - 4 - 4;
        sb.append(monthMap.get(date.substring(l, l + 3))).append('-');

        int day = 0;
        for (int i = 0; i < date.length(); i++) {
            final char ch = date.charAt(i);
            if (ch < '0' || '9' < ch) break;
            day *= 10;
            day += (ch - '0');
        }

        if (day < 10) sb.append('0');
        return sb.append(day).toString();
    }

    public static void main(String[] args) {
        Q1507 q1507 = new Q1507();
        System.out.println(q1507.reformatDate("16th Jun 1933"));
    }
}
