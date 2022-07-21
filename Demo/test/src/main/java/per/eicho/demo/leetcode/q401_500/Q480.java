package per.eicho.demo.leetcode.q401_500;

import java.util.Map;
import java.util.TreeMap;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>480. Sliding Window Median 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sliding-window-median/">
 *   480. Sliding Window Median</a>
 */
public final class Q480 {

    int lSize = 0;
    int rSize = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        // 1. 1 <= k <= nums.length <= 10^5
        // 2. -2^31 <= nums[i] <= 2^31 - 1
        final TreeMap<Long, Integer> leftTreeMap = new TreeMap<>();
        final TreeMap<Long, Integer> rightTreeMap = new TreeMap<>();

        final int n = nums.length;
        final double[] result = new double[n - k + 1];
        // final int size = k / 2;
        int l = 0, r = 0; // [l, r);
        while (r < n) {
            while (r < n && r - l < k) {
                final Long num = (long)nums[r++];
                if (rightTreeMap.isEmpty() || num < rightTreeMap.firstKey()) {
                    lSize++;
                    addToTreeMap(leftTreeMap, num);
                } else {
                    rSize++;
                    addToTreeMap(rightTreeMap, num);
                }
            }

            while (lSize > rSize) {
                moveOneElementToRight(leftTreeMap, rightTreeMap);
            }

            while (rSize - lSize > 1) {
                moveOneElementToLeft(leftTreeMap, rightTreeMap);
            }


            final double median;
            if (lSize < rSize) {
                median = rightTreeMap.firstKey().doubleValue();
            } else {
                // lSize == rSize
                median = (leftTreeMap.lastKey().doubleValue() + rightTreeMap.firstKey().doubleValue()) / 2.0d;
            }

            result[l] = median;
            
            final Long num = (long)nums[l++];
            if (leftTreeMap.containsKey(num)) {
                final int count = leftTreeMap.put(num, leftTreeMap.get(num) - 1);
                if (count == 1) leftTreeMap.remove(num);
                lSize--;
            } else {
                final int count = rightTreeMap.put(num, rightTreeMap.get(num) - 1);
                if (count == 1) rightTreeMap.remove(num);
                rSize--;
            }
        }
        return result;
    }

    private void addToTreeMap(final TreeMap<Long, Integer> leftTreeMap, final Long num) {
        if (!leftTreeMap.containsKey(num)) {
            leftTreeMap.put(num, 1);
        } else {
            leftTreeMap.put(num, leftTreeMap.get(num) + 1);
        }
    }

    private void moveOneElementToRight(final TreeMap<Long, Integer> leftTreeMap, final TreeMap<Long, Integer> rightTreeMap) {
        final Map.Entry<Long, Integer> entry = leftTreeMap.lastEntry();
        final Long key = entry.getKey();
        final int count = entry.getValue();
        lSize--;
        rSize++;

        addToTreeMap(rightTreeMap, key);                

        // -1 or remove.
        if (count != 1) {
            leftTreeMap.put(key, count - 1);
        } else {
            leftTreeMap.remove(key);
        }
    }

    private void moveOneElementToLeft(final TreeMap<Long, Integer> leftTreeMap, final TreeMap<Long, Integer> rightTreeMap) {
        final Map.Entry<Long, Integer> entry = rightTreeMap.firstEntry();
        final Long key = entry.getKey();
        final int count = entry.getValue();
        lSize++;
        rSize--;

        addToTreeMap(leftTreeMap, key);                

        // -1 or remove.
        if (count != 1) {
            rightTreeMap.put(key, count - 1);
        } else {
            rightTreeMap.remove(key);
        }
    }

    public static void main(String[] args) {
        Q480 q480 = new Q480();
        OutputUtils.println(q480.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
        // System.out.println();
    }
}
