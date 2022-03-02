package per.eicho.demo.leetcode.q201_300;

import java.util.Stack;

/**
 * <p>273. Integer to English Words 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/integer-to-english-words/">
 *   273. Integer to English Words</a>
 */
public final class Q273 {

    private static final int THOUSAND = 1000;

    public String numberToWords(int num) {
        // 0 <= num <= 2^31 - 1
        if (num == 0) return "Zero";
        
        final String[] digitStr = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        final String[] specialStr = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen"
          ,"Eighteen", "Nineteen"};
        final String[] specialStr2 = new String[]{null, null, "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        final String hundred = "Hundred";

        final String[] units = new String[]{"", "Thousand", "Million", "Billion"};
        int p = 0;
        
        final Stack<String> elements = new Stack<>();

        while (num != 0) {
            final int digits = num % THOUSAND;
            num /= THOUSAND;

            if (digits == 0) {
                p++;
                continue;
            } else {
                if (p != 0) {
                    elements.add(units[p++]);
                } else {
                    p++;
                }
            }

            int lastTwoDigits = digits % 100;
            if (lastTwoDigits == 0) {
            } else if (lastTwoDigits < 10) {
                elements.add(digitStr[lastTwoDigits]); // lastTwoDigits % 10 == lastTwoDigits  
            } else if (10 <= lastTwoDigits && lastTwoDigits <= 19) {
                elements.add(specialStr[lastTwoDigits % 10]);
            } else {
                if (lastTwoDigits % 10 != 0) elements.add(digitStr[lastTwoDigits % 10]);
                elements.add(specialStr2[lastTwoDigits / 10]);
            }

            if (digits / 100 != 0) {
                elements.add(hundred);
                elements.add(digitStr[digits / 100]);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!elements.isEmpty()) {
            sb.append(elements.pop());

            if (!elements.isEmpty()) sb.append(' ');
        }
        return sb.toString();
    }
}
