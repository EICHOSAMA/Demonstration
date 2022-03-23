package per.eicho.demo.leetcode.q1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>1282. Group the People Given the Group Size They Belong To 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/">
 *   1282. Group the People Given the Group Size They Belong To</a>
 */
public final class Q1282 {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        // 1. groupSizes.length == n
        // 2. 1 <= n <= 500
        // 3. 1 <= groupSizes[i] <= n
        return groupThePeople(Arrays.stream(groupSizes).boxed().collect(Collectors.toList()));
    }

    public List<List<Integer>> groupThePeople(List<Integer> groupSizes) {
        final Map<Integer, List<Integer>> map = new HashMap<>();
        final List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupSizes.size(); i++) {
            final Integer groupSize = groupSizes.get(i);

            if (!map.containsKey(groupSize)) {
                map.put(groupSize, new ArrayList<>(groupSize));
            }

            List<Integer> group = map.get(groupSize);
            group.add(i);

            if (group.size() == groupSize) {
                result.add(group);
                map.put(groupSize, new ArrayList<>(groupSize));
            }
        }
        return result;
    }
}
