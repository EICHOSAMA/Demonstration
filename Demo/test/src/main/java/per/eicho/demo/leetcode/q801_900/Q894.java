package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>894. All Possible Full Binary Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/all-possible-full-binary-trees/">
 *   894. All Possible Full Binary Trees</a>
 */
public final class Q894 {
    
    static Map<Integer, List<TreeNode>> memo;
    
    static {
        memo = new HashMap<>();
        
        memo.put(0, new ArrayList<>());
        memo.put(1, Arrays.asList(new TreeNode(0)));
        memo.put(2, new ArrayList<>());
        memo.put(3, Arrays.asList(new TreeNode(0, new TreeNode(0), new TreeNode(0))));
    }

    public List<TreeNode> allPossibleFBT(int n) {
        // 1. 1 <= n <= 20
        if (memo.containsKey(n)) return memo.get(n);
        final List<TreeNode> fbts = new ArrayList<>();

        for (int i = 1; i <= n - 2; i++) {
            final List<TreeNode> lFBTs = allPossibleFBT(i);
            if (lFBTs.size() == 0) continue;
            final List<TreeNode> rFBTs = allPossibleFBT(n - i - 1);
            if (rFBTs.size() == 0) continue;

            for (TreeNode l : lFBTs) {
                for (TreeNode r : rFBTs) {
                    fbts.add(new TreeNode(0, l, r));
                }
            }
        }

        memo.put(n, fbts);
        return fbts;
    }
}
