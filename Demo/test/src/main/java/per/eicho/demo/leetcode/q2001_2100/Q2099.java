package per.eicho.demo.leetcode.q2001_2100;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>2099. Find Subsequence of Length K With the Largest Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/">
 *   2099. Find Subsequence of Length K With the Largest Sum</a>
 */
public final class Q2099 {

    private static final class Node {
        int val;
        int idx;

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int[] maxSubsequence(int[] nums, int k) {
        List<Node> nodes = new ArrayList<>(nums.length);
        
        for (int i = 0; i < nums.length; i++) {
            nodes.add(new Node(nums[i], i));
        }

        int[] result = nodes.stream().sorted((a, b) -> {
            if (a.val > b.val) return -1; // val: big ⇒ small
            if (a.val < b.val) return 1;
            return Integer.compare(a.idx, b.idx);
        }).limit(k)
          .sorted((a, b) -> {
            return Integer.compare(a.idx, b.idx); // idx: small ⇒ big
          })
          .mapToInt(node -> node.val)
          .toArray();
        return result;
    }
}
