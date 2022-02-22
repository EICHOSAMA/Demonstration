package per.eicho.demo.leetcode.q101_200;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>166. Fraction to Recurring Decimal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/fraction-to-recurring-decimal/">166. Fraction to Recurring Decimal</a>
 */
public final class Q166 {
    public String fractionToDecimal(int numerator, int denominator) {
        // 1. -2^31 <= numerator, denominator <= 2^31 - 1
        // 2. denominator != 0
        return fractionToDecimal((long)numerator, (long)denominator);
    }

    public String fractionToDecimal(long numerator, long denominator) {
        final StringBuilder sb = new StringBuilder();
        if (numerator * denominator < 0) sb.append('-');
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);        
        
        if (numerator % denominator == 0) return sb.append(numerator / denominator).toString();
        sb.append(numerator / denominator).append('.');
        numerator %= denominator;

        // Map<numerator, idx>
        final Map<Long, Integer> map = new HashMap<>();

        Long iNumerator = numerator;
        while (iNumerator != 0 && !map.containsKey(iNumerator)) {
            long num = iNumerator;

            map.put(iNumerator, sb.length());
            
            num *= 10;
            sb.append(num / denominator);
            iNumerator = num % denominator;
        }

        if (iNumerator == 0) return sb.toString();
        return sb.insert(map.get(iNumerator), "(")
                 .append(')')
                 .toString();
    }
    
    public static void main(String[] args) {
        Q166 q166 = new Q166();
        System.out.println(q166.fractionToDecimal(-400, 333));
        System.out.println(q166.fractionToDecimal(-400, -333));
        System.out.println(q166.fractionToDecimal(400, -333));
        
    }
}
