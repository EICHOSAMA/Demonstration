package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.List;

public final class Q728 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        final List<Integer> result = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (isSelfDividingNumber(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isSelfDividingNumber(int num) {
        int temp = num;

        while (temp != 0) {
            int digit = temp % 10;

            if (digit == 0) return false;
            if (num % digit != 0) return false;

            temp /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        Q728 q728 = new Q728();

        q728.selfDividingNumbers(1, 10000).forEach(i -> System.out.print(i + ","));
    }
}
