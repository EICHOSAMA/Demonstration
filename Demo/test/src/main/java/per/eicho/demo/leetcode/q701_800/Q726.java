package per.eicho.demo.leetcode.q701_800;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>726. Number of Atoms 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-atoms/">
 *   726. Number of Atoms</a>
 */
public final class Q726 {

    public String countOfAtoms(String formula) {
        // 1. 1 <= formula.length <= 1000
        // 2. formula consists of English letters, digits, '(', and ')'.
        // 3. formula is always valid.
        final Map<String, Integer> countMap = parse(formula, 0, formula.length() - 1);
        final TreeMap<String, Integer> treeMap = new TreeMap<>(countMap);        
        final StringBuilder sb = new StringBuilder();
        while (!treeMap.isEmpty()) {
            final Map.Entry<String, Integer> entry = treeMap.pollFirstEntry();
            sb.append(entry.getKey());
            if (entry.getValue() > 1) sb.append(entry.getValue());
        }
        return sb.toString();
    }

    private Map<String, Integer> parse(String formula, int l, int r) {
        // <Atom Name, Count>
        final Map<String, Integer> result = new HashMap<>();

        int p = l;
        int lv = 0;
        final StringBuilder sb = new StringBuilder();
        while (p <= r) {
            char ch = formula.charAt(p);
            if (ch == '(') {
                lv++;

                final int nL = p;
                while (lv != 0) {
                    ch = formula.charAt(++p);

                    if (ch == '(') {
                        lv++;
                    } else if (ch == ')') {
                        lv--;
                    }
                }
                final int nR = p;

                int num = 0;
                while (p + 1 <= r && isDigit(ch = formula.charAt(p + 1))) {
                    num *= 10;
                    num += (ch - '0');
                    p++;
                }
                p++;
                num = num != 0 ? num : 1; // default value 1

                if (l - r == 1) continue; // pattern '()'
                final Map<String, Integer> subMap = parse(formula, nL + 1, nR - 1);

                for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
                    final String key = entry.getKey();
                    final int count = entry.getValue() * num;

                    result.putIfAbsent(key, 0);
                    result.put(key, result.get(key) + count);
                }
                continue;
            }

            if (isUpperLetter(ch)) {
                sb.delete(0, sb.length());
                sb.append(ch);

                while (p + 1 <= r && isLowerLetter(ch = formula.charAt(p + 1))) {
                    p++;
                    sb.append(ch);
                }

                int num = 0;
                while (p + 1 <= r && isDigit(ch = formula.charAt(p + 1))) {
                    num *= 10;
                    num += (ch - '0');
                    p++;
                }
                p++;
                num = num != 0 ? num : 1; // default value 1

                final String atomName = sb.toString(); 
                result.putIfAbsent(atomName, 0);
                result.put(atomName, result.get(atomName) + num);
                continue;
            }
        }

        return result;
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private boolean isUpperLetter(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }

    private boolean isLowerLetter(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    public static void main(String[] args) {
        Q726 q726 = new Q726();
        System.out.println(q726.countOfAtoms("K4(ON(SO3)2)2"));
    }
}
