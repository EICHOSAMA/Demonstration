package per.eicho.demo.leetcode.q901_1000;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.datastructure.tree.disjoint.DisjointSetForest;

/**
 * <p>990. Satisfiability of Equality Equations 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/satisfiability-of-equality-equations/">
 *   990. Satisfiability of Equality Equations</a>
 */
public final class Q990 {
    public boolean equationsPossible(String[] equations) {
        // 1. 1 <= equations.length <= 500
        // 2. equations[i].length == 4
        // 3. equations[i][0] is a lowercase letter.
        // 4. equations[i][1] is either '=' or '!'.
        // 5. equations[i][2] is '='.
        // 6. equations[i][3] is a lowercase letter.
        final DisjointSetForest dsf = new DisjointSetForest(26);
        final List<int[]> infos = new ArrayList<>();
        for (String equation : equations) {
            final int[] info = parse(equation); // 0: from 1: to 2: 0/1 (!= / ==)
            if (info[2] == 1) {
                dsf.union(info[0], info[1]);    
            } else {
                infos.add(info);
            }
        }

        for (int[] info : infos) {
            if (dsf.find(info[0]) == dsf.find(info[1])) return false;
        }
        return true;
    }

    private int[] parse(String equation) {
        return new int[]{equation.charAt(0) - 'a', equation.charAt(3) - 'a', equation.charAt(1) == '!' ? 0 : 1};
    }
}
