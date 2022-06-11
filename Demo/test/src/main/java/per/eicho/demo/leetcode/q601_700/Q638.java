package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>638. Shopping Offers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shopping-offers/">
 *   638. Shopping Offers</a>
 */
public final class Q638 {

    Map<List<Integer>, Integer> memo = new HashMap<>();
    List<Integer> price;
    List<List<Integer>> special;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 1. n == price.length
        // 2. n == needs.length
        // 3. 1 <= n <= 6
        // 4. 0 <= price[i] <= 10
        // 5. 0 <= needs[i] <= 10
        // 6. 1 <= special.length <= 100
        // 7. special[i].length == n + 1
        // 8. 0 <= special[i][j] <= 50
        this.price = price;
        this.special = filter(special, price);
        return search(needs);
    }

    private List<List<Integer>> filter(List<List<Integer>> special, List<Integer> price) {
        final List<List<Integer>> newSpecial = new ArrayList<>();
        for (List<Integer> offer : special) {
            final int offerPrice = offer.get(offer.size() - 1);
            int originalPrice = 0;
            for (int i = 0; i < price.size(); i++) {
                originalPrice += offer.get(i) * price.get(i);
            }
            if (offerPrice < originalPrice) newSpecial.add(offer);
        }
        return newSpecial;
    }

    private int search(List<Integer> currentNeeds) {
        if (memo.containsKey(currentNeeds)) return memo.get(currentNeeds);

        int cost = 0;
        for (int i = 0; i < currentNeeds.size(); i++) {
            final int need = currentNeeds.get(i);
            cost += need * price.get(i);
        }

        outer:
        for (List<Integer> offer : special) {
            for (int i = 0; i < currentNeeds.size(); i++) {
                if (offer.get(i) > currentNeeds.get(i)) continue outer; // skip current offer.
            }

            final int offerPrice = offer.get(offer.size() - 1);
            final List<Integer> nextStatus = new ArrayList<>(currentNeeds);
            for (int i = 0; i < nextStatus.size(); i++) nextStatus.set(i, nextStatus.get(i) - offer.get(i));

            cost = Math.min(cost, search(nextStatus) + offerPrice);
        }
        memo.put(currentNeeds, cost);
        return cost;
    }

    public static void main(String[] args) {
        Q638 q638 = new Q638();
        System.out.println(q638.shoppingOffers(Arrays.asList(5), 
            Arrays.asList(Arrays.asList(1,3), Arrays.asList(4,7), Arrays.asList(3,3)), 
            Arrays.asList(4)));
    }
}
