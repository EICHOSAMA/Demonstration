package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>17. Letter Combinations of a Phone Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/">17. Letter Combinations of a Phone Number</a>
 */
public final class Q17 {
    private static List<char[]> map;

    static {
        map = new ArrayList<>(8);

        map.add(new char[]{'a', 'b', 'c'}); // 2
        map.add(new char[]{'d', 'e', 'f'}); // 3
        map.add(new char[]{'g', 'h', 'i'}); // 4
        map.add(new char[]{'j', 'k', 'l'}); // 5
        map.add(new char[]{'m', 'n', 'o'}); // 6
        map.add(new char[]{'p', 'q', 'r', 's'}); // 7
        map.add(new char[]{'t', 'u', 'v'}); // 8
        map.add(new char[]{'w', 'x', 'z', 'y'}); // 9

    }

    private String input;
    private List<String> result;

    /**
     * 2 - abc
     * 3 - def
     * 4 - ghi
     * 5 - jkl
     * 6 - mno
     * 7 - pqrs
     * 8 - tuv
     * 9 - wxzy
     *
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        input = digits; // record input.
        if (digits == null ||
            digits.length() == 0)
            return new ArrayList<>(0);
        // digits is in [2, 9]
        result = new LinkedList<>();

        process(0, new StringBuilder());

        return result;
    }

    public void process(int i, StringBuilder sb) {
        if (i >= input.length()) {
            result.add(sb.toString()); // terminate.
            return;
        }

        int index = input.charAt(i) - 50; // 48 + 2, '2' - '9' , '48' = 0
        char[] chars = map.get(index);

        for (char ch: chars) {
            process(i+1, sb.append(ch));
            sb.deleteCharAt(i);
        }
    }
}
