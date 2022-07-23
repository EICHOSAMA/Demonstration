package per.eicho.demo.leetcode.q601_700;

import java.util.List;
import java.util.TreeSet;

import per.eicho.demo.leetcode.debug.InputUtils;
import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>632. Smallest Range Covering Elements from K Lists 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/">
 *   632. Smallest Range Covering Elements from K Lists</a>
 */
public final class Q632 {

    private static final class Info {
        final List<Integer> nums;
        final int size;
        int pointer;

        Info(List<Integer> nums) {
            this.nums = nums;
            size = nums.size();
            pointer = 0;
        }

        private int getCurrent() {
            return nums.get(pointer);
        }

        private int getNext() {
            return nums.get(pointer + 1);
        }

        private boolean hasNext() {
            return pointer < size - 1;
        }

        private void move2Next() {
            pointer++;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        // 1. nums.length == k
        // 2. 1 <= k <= 3500
        // 3. 1 <= nums[i].length <= 50
        // 4. -10^5 <= nums[i][j] <= 10^5
        // 5. nums[i] is sorted in non-decreasing order.
        final TreeSet<Info> infos = new TreeSet<>((i1, i2) -> {
            int diff = i1.getCurrent() - i2.getCurrent();
            if (diff != 0) return diff;
            if (!i1.hasNext()) return -1;
            if (!i2.hasNext()) return 1;

            diff = i1.getNext() - i2.getNext();
            if (diff != 0) return diff;
            return Integer.compare(i1.hashCode(), i2.hashCode());
        });

        for (List<Integer> num : nums) {
            infos.add(new Info(num));
        }

        int l = infos.first().getCurrent();
        int r = infos.last().getCurrent();
        int len = r - l + 1;
        Info info = null;
        while ((info = infos.pollFirst()).hasNext()) {
            info.move2Next();
            infos.add(info);

            final int nL = infos.first().getCurrent();
            final int nR = infos.last().getCurrent();
            final int nLen = nR - nL + 1;
            if (nLen < len) {
                l = nL;
                r = nR;
                len = nLen;
            }
        }
        return new int[]{l, r};
    }

    public static void main(String[] args) {
        Q632 q632 = new Q632();

        List<List<Integer>> data = InputUtils.gen2DList("[[-38,15,17,18],[-34,46,58,59,61],[-55,-31,-13,64,82,82,83,84,85],[-3,63,70,90],[2,6,10,28,28,32,32,32,33],[-23,82,88,88,88,89],[33,60,72,74,75],[-5,44,44,57,58,58,60],[-29,-22,-4,-4,17,18,19,19,19,20],[22,57,82,89,93,94],[24,38,45],[-100,-56,41,49,50,53,53,54],[-76,-69,-66,-53,-27,-1,9,29,31,32,32,32,34],[22,47,56],[-34,-28,7,44]]");

        OutputUtils.println(q632.smallestRange(data));
    }
}
