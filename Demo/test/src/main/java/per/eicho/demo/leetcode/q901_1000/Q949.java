package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>949. Largest Time for Given Digits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-time-for-given-digits/">
 *   949. Largest Time for Given Digits</a>
 */
public final class Q949 {

    String result = null;

    public String largestTimeFromDigits(int[] arr) {
        // 1. arr.length == 4
        // 2. 0 <= arr[i] <= 9
        int[] temp = new int[4];
        boolean[] mark = new boolean[4];
        search(temp, mark, arr, 0);
        return result == null ? "" : result;
    }

    private void search(int[] temp, boolean[] mark, int[] arr, int p) {
        if (p == 4) {
            if (isValidTime(temp)) {
                final String candidate = "" + temp[0] + temp[1] + ":" + temp[2] + temp[3];
                if (result == null) {
                    result = candidate;
                } else {
                    result = result.compareTo(candidate) < 0 ? candidate : result;
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (mark[i] == true) continue;

            mark[i] = true;
            temp[p] = arr[i];
            search(temp, mark, arr, p + 1);
            mark[i] = false;
        }
    }

    private boolean isValidTime(int[] temp) {
        int hh = (temp[0] * 10) + temp[1];
        int mm = (temp[2] * 10) + temp[3];
        System.out.println(hh + ":" + mm);
        return 0 <= hh && hh <= 23 && 0 <= mm && mm <= 59;
    }

    public static void main(String[] args) {
        Q949 q949 = new Q949();
        System.out.println(q949.largestTimeFromDigits(new int[]{1,2,3,4}));
    }
}
