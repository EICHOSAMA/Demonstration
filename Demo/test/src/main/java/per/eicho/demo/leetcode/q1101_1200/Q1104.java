package per.eicho.demo.leetcode.q1101_1200;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>1104. Path In Zigzag Labelled Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/">
 *   1104. Path In Zigzag Labelled Binary Tree</a>
 */
public final class Q1104 {

    public List<Integer> pathInZigZagTree(int label) {
        // 1. 1 <= label <= 10^6
        int size = 1;
        int layer = 1;
        while (size < label) {
            size <<= 1;
            size++;
            layer++;
            // 1,3,7,15,...
        }
        return pathInZigZagTree(label, size, layer);
    }

    private LinkedList<Integer> pathInZigZagTree(int label, int size, int layer) {
        // 1. 1 <= label <= 10^6
        if (label == 1) {
            final LinkedList<Integer> result = new LinkedList<>();
            result.add(1);
            return result;
        };

        
        final int realLabel;
        final LinkedList<Integer> result;
        if (layer % 2 == 1) {
            // odd  : l -> r
            realLabel = label;
            final int vituralLabel = realLabel / 2;
            final int distance = (size >> 1) - vituralLabel;
            result = pathInZigZagTree((size >> 2) + 1 + distance, size >> 1, layer - 1);
        } else {
            // even : r -> l
            final int distance = size - label; // [0, size / 2]
            realLabel = distance + (size + 1) / 2;
            result = pathInZigZagTree(realLabel / 2, size >> 1, layer - 1);
        }

        result.add(label);
        return result;
    }
}
