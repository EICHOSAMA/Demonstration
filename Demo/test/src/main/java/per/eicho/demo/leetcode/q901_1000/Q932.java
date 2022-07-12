package per.eicho.demo.leetcode.q901_1000;

import java.util.HashMap;
import java.util.Map;

public final class Q932 {
    
    Map<Integer, int[]> memo;
    
    public int[] beautifulArray(int n) {
        // 1. 1 <= n <= 1000;
        memo = new HashMap<>();
        memo.put(1, new int[]{1});
        return f(n);
    }

    public int[] f(int n) {
        if (memo.containsKey(n)) return memo.get(n);

        final int[] result = new int[n];
        
        int t = 0;
        final int[] left = f((n + 1) / 2);
        for (int num: left) result[t++] = 2 * num - 1; // mapping
        
        final int[] right = f(n / 2);
        for (int num: right) result[t++] = 2 * num;
    
        memo.put(n, result);
        return result;
    }
}
