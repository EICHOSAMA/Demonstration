package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Sink.ChainedInt;

/**
 * <p>830. Positions of Large Groups 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/positions-of-large-groups/">830. Positions of Large Groups</a>
 */
public final class Q830 {
    public List<List<Integer>> largeGroupPositions(String s) {
        // 1. 1 <= s.length <= 1000
        // 2. s contains lowercase English letters only.
        final List<List<Integer>> result = new ArrayList<>();
        
        char ch = s.charAt(0);
        int start = 0; // inclusive
        int end = 0;   // inclusive 
        for (int i = 1; i < s.length(); i++) {
            final char chI = s.charAt(i);

            if (chI == ch) {
                end++;
            } else {
                if (end - start >= 2) result.add(Arrays.asList(start, end));

                ch = chI;
                start = i;
                end = i;
            }
        }
        if (end - start >= 2) result.add(Arrays.asList(start, end));

        return result;
    }
}
