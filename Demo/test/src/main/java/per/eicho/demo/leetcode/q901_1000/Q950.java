package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;
import java.util.LinkedList;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>950. Reveal Cards In Increasing Order 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reveal-cards-in-increasing-order/">
 *   950. Reveal Cards In Increasing Order</a>
 */
public final class Q950 {
    public int[] deckRevealedIncreasing(int[] deck) {
        // 1. 1 <= deck.length <= 1000
        // 2. 1 <= deck[i] <= 10^6
        // 3. All the values of deck are unique.
        final int n = deck.length;
        final LinkedList<Integer> indexes = new LinkedList<>();
        for (int i = 0; i < n; i++) indexes.add(i);
        Arrays.sort(deck);
        final int[] idxes = new int[n];
        int p = 0;
        while (p < n) {
            idxes[p++] = indexes.pollFirst();
            indexes.addLast(indexes.pollFirst());
        }

        final int[] result = new int[n];
        p = 0;
        while (p < n) {
            result[idxes[p]] = deck[p];
            p++; 
        }
        return result;
    }

    public static void main(String[] args) {
        Q950 q950 = new Q950();
        OutputUtils.println(q950.deckRevealedIncreasing(new int[]{17,13,11,2,3,5,7}));
    }
}
