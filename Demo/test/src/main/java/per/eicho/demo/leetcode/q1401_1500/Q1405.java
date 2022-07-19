package per.eicho.demo.leetcode.q1401_1500;

import java.util.PriorityQueue;

/**
 * <p>1405. Longest Happy String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-happy-string/">
 *   1405. Longest Happy String</a>
 */
public final class Q1405 {

    private static final int COUNT = 0;
    private static final int CHAR = 1;

    public String longestDiverseString(int a, int b, int c) {
        // 1. 0 <= a, b, c <= 100
        // 2. a + b + c > 0
        final PriorityQueue<int[]> maxHeap = new PriorityQueue<>((info1, info2) -> {
            if (info1[COUNT] != info2[COUNT]) return Integer.compare(info2[COUNT], info1[COUNT]); // descending
            return Integer.compare(info1[CHAR], info2[CHAR]);
        });

        maxHeap.add(new int[]{a, 'a'});
        maxHeap.add(new int[]{b, 'b'});
        maxHeap.add(new int[]{c, 'c'});

        final StringBuilder sb = new StringBuilder();
        do {
            final int[] info1 = maxHeap.poll();
            final int[] info2 = maxHeap.poll();

            final char charA = (char)info1[CHAR];
            final char charB = (char)info2[CHAR];
            if (info1[COUNT] == info2[COUNT]) {
                final int[] info3 = maxHeap.poll();
                int count = info1[COUNT];
                while (count > info3[COUNT]) {
                    sb.append(charA);
                    sb.append(charB);
                    count--;
                }

                final char charC = (char)info3[CHAR];
                while (count > 0) {
                    sb.append(charA);
                    sb.append(charB);
                    sb.append(charC);
                    count--;
                }
                return sb.toString();
            }

            sb.append(charA);
            if (--info1[COUNT] > 0) {
                sb.append(charA);
                info1[COUNT]--;
            }
            if (info2[COUNT] > 0) {
                sb.append(charB);
                info2[COUNT]--;
            } else {
                break;
            }

            maxHeap.add(info1);
            maxHeap.add(info2);
        } while (true);

        return sb.toString();
    }

    public static void main(String[] args) {
        Q1405 q1405 = new Q1405();
        System.out.println(q1405.longestDiverseString(2, 2, 1));
    }
}
