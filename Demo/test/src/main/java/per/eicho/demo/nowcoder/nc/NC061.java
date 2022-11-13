package per.eicho.demo.nowcoder.nc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class NC061 {
    /**
     * 
     * @param numbers int整型一维数组 
     * @param target int整型 
     * @return int整型一维数组
     */
    public int[] twoSum (int[] numbers, int target) {
        final Map<Integer, List<Integer>> num2IndexesMap = new HashMap<>(); 
        final int n = numbers.length;
        for (int i = 0; i < n; i++) {
            final int num = numbers[i];

            int partB = target - num;
            if (num2IndexesMap.containsKey(partB)) return new int[]{num2IndexesMap.get(partB).get(0) + 1, i + 1};

            final Integer index = i;
            num2IndexesMap.computeIfAbsent(num, (k) -> new LinkedList<>());
            num2IndexesMap.computeIfPresent(num, (k, list) -> {
                list.add(index);
                return list;
            });
        }

        return null;
    }
}
