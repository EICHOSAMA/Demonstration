package per.eicho.demo.leetcode.q001_100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>22. Generate Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/generate-parentheses/">22. Generate Parentheses</a>
 */
public final class Q22 {
    public List<String> generateParenthesis(int n) {
        // 1. 1 <= n <= 8
        if (n == 1) {
            return Arrays.asList("()");
        }

        final Set<String> result = new HashSet<>();
        int mid = n / 2; // 5 -> 2 , 6 -> 3

        List<String> resultOfNM1 = generateParenthesis(n-1); // result of n minus 1.
        for (String str: resultOfNM1) {
            result.add(str + "()");
            result.add("()" + str);
            result.add('(' + str + ')');
        }

        for (int i = 2; i <= mid; i++) {
            List<String> res1 = generateParenthesis(i);
            List<String> res2 = generateParenthesis(n-i);
            for (int j = 0; j < res1.size(); j++) {
                for (int k = 0; k < res2.size(); k++) {
                    result.add(res1.get(j) + res2.get(k));
                    result.add(res2.get(k) + res1.get(j));
                }
            }
        }
        return result.stream().collect(Collectors.toList());
    }
}
