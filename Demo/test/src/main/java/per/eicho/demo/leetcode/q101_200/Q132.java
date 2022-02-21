package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>132. Palindrome Partitioning II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/palindrome-partitioning-ii/">132. Palindrome Partitioning II</a>
 */
public final class Q132 {

    int min;
    int[] f;

    public int minCut(String s) {
        // 1. 1 <= s.length <= 2000
        // 2. s consists of lowercase English letters only.        
        final int n = s.length();

        // Map<StartIdx, List<EndIdx>>
        final Map<Integer, List<Integer>> map = new HashMap<>();
        final Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
            map.get(i).add(i);
            queue.add(new int[]{i, i});
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                map.get(i).add(i + 1);
                queue.add(new int[]{i, i + 1});
            }
        }

        while (!queue.isEmpty()) {
            final int[] range = queue.poll();
            final int l = range[0];
            final int r = range[1];

            if (l == 0 || r == n -1) continue;
            if (s.charAt(l - 1) != s.charAt(r + 1)) continue;
            range[0]--;
            range[1]++;

            map.get(range[0]).add(range[1]);
            queue.add(range);
        }

        min = n;
        f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        search(map, 0, n, 0);
        return min - 1;
    }

    private void search(final Map<Integer, List<Integer>> map, int l, int n, int currentPart) {
        if (l == n) {
            min = Math.min(min, currentPart);
            return;
        }

        if (currentPart >= f[l]) return;
        //assert currentPart < f[l];
        f[l] = currentPart;

        final List<Integer> ends = map.get(l);
        for (int i = ends.size() - 1; i >= 0; i--) {
            final int end = ends.get(i);
            search(map, end + 1, n, currentPart + 1);
        }
    }

    public static void main(String[] args) {
        Q132 q132 = new Q132();
        System.out.println(new Date());
        System.out.println(q132.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp"));
        System.out.println(new Date());
    }
}
