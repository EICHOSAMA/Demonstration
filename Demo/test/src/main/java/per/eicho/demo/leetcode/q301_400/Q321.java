package per.eicho.demo.leetcode.q301_400;

import java.util.Arrays;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>321. Create Maximum Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/create-maximum-number/">
 *   321. Create Maximum Number</a>
 */
public final class Q321 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // 1. m == nums1.length
        // 2. n == nums2.length
        // 3. 1 <= m, n <= 500
        // 4. 0 <= nums1[i], nums2[i] <= 9
        // 5. 1 <= k <= m + n
        final int[][] seq1 = genSequences(nums1, k);
        final int[][] seq2 = genSequences(nums2, k);

        int[] result = null;
        for (int len1 = 0; len1 <= k; len1++) {
            final int len2 = k - len1;

            if (len1 > seq1.length || len2 > seq2.length) continue;

            final int[] s1 = len1 == 0 ? null : seq1[len1 - 1];
            final int[] s2 = len2 == 0 ? null : seq2[len2 - 1];

            final int[] candidate = genCandidate(s1, s2);
            if (isGreater(candidate, result)) result = candidate;
        }

        return result;
    }

    private int[][] genSequences(int[] nums, int k) {
        final int n = nums.length;
        final int[][] seq = new int[Math.min(n, k)][];
        
        seq[0] = new int[]{nums[0]};
        for (int i = 1; i < n; i++) {
            final int num = nums[i];

            // find last idx
            int j = 0;
            while (j < seq.length && seq[j] != null) j++;
            j--;

            // process
            if (j + 1 < seq.length) {
                seq[j + 1] = Arrays.copyOf(seq[j], j + 2);
                seq[j + 1][j + 1] = num;
            }

            while (j >= 0) {
                if (seq[j][j] < num) {
                    seq[j][j] = num;
                }

                if (--j < 0) break;
                
                if (isGreater(seq, j, num)) {
                    // copy to seq1[j + 1];
                    copy(seq, j, num);
                }
            }
        }

        return seq;
    }

    private boolean isGreater(int[][] seq, int j, int num) {
        final int[] lv1 = seq[j];
        final int[] lv2 = seq[j + 1];

        int p = 0;
        while (p < lv1.length && lv1[p] == lv2[p]) p++;

        if (p == lv1.length) {
            // same prefix.
            return num > lv2[p];
        }

        return lv1[p] > lv2[p];
    }

    private void copy(int[][] seq, int j, int num) {
        final int copyedElement = seq[j].length;
        System.arraycopy(seq[j], 0, seq[j + 1], 0, copyedElement);
        seq[j + 1][copyedElement] = num;
    }

    private int[] genCandidate(int[] seq1, int[] seq2) {
        if (seq1 == null) return seq2;
        if (seq2 == null) return seq1;
        final int[] candidate = new int[seq1.length + seq2.length];

        int p = 0;
        int p1 = 0;
        int p2 = 0;

        while (p1 < seq1.length && p2 < seq2.length) {
            if (seq1[p1] > seq2[p2]) {
                candidate[p++] = seq1[p1++];
            } else if (seq1[p1] < seq2[p2]) {
                candidate[p++] = seq2[p2++];
            } else {
                // equal [6, 7] [6, 0, 4]
                int tempP1 = p1;
                int tempP2 = p2;
                while (tempP1 < seq1.length && tempP2 < seq2.length && seq1[tempP1] == seq2[tempP2]) {
                    tempP1++;
                    tempP2++;
                }

                if (tempP1 == seq1.length) {
                    candidate[p++] = seq2[p2++];
                } else if (tempP2 == seq2.length) {
                    candidate[p++] = seq1[p1++];
                } else {
                    if (seq1[tempP1] > seq2[tempP2]) {
                        candidate[p++] = seq1[p1++];
                    } else {
                        candidate[p++] = seq2[p2++];
                    }
                }
            }
        }

        while (p1 < seq1.length) candidate[p++] = seq1[p1++];
        while (p2 < seq2.length) candidate[p++] = seq2[p2++];
        return candidate;
    }

    private boolean isGreater(int[] num1, int[] num2) {
        if (num2 == null) return true;

        final int n = num1.length;
        int p = 0;
        while (p < n && num1[p] == num2[p]) p++;
        return p == n ? false : num1[p] > num2[p];
    }

    public static void main(String[] args) {
        Q321 q321 = new Q321();
        // OutputUtils.println(q321.maxNumber(new int[]{3,4,6,5}, new int[]{9,1,2,5,8,3}, 5));
        OutputUtils.println(q321.maxNumber(new int[]{6,6,6,6,6,7,6}, new int[]{6,6,6,6,6,7,5}, 14));
    }
}
