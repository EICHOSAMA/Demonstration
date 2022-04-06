package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>923. 3Sum With Multiplicity 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/3sum-with-multiplicity/">923. 3Sum With Multiplicity</a>
 */
public final class Q923 {
    
    int[] bucket;
    long result;

    private static long MODULO = 1_000_000_000 + 7;
    
    public int threeSumMulti(int[] arr, int target) {
        // 1. 3 <= arr.length <= 3000
        // 2. 0 <= arr[i] <= 100
        // 3. 0 <= target <= 300        
        bucket = new int[101]; // [0, 100]
        for (int num : arr) bucket[num]++;
        result = 0;

        return (int)(search(0, target, 3) % MODULO);
    }

    private long search(int current, int target, int remain) {
        if (remain == 0) return target == 0 ? 1L : 0L;
        if (remain < 0 || current > 100) return 0L;
        if (remain == 1) {
            if (target < current) return 0L;
            if (target > 100) return 0L;
            return bucket[target];
        }

        final long count = bucket[current];

        long result = 0L;

        if (count >= 3) {
            result = (result + search(current + 1, target - current * 3, remain - 3) * count * (count - 1L) * (count - 2L) / 6L) % MODULO;
        }

        if (count >= 2) {
            result = (result + search(current + 1, target - current * 2, remain - 2) * count * (count - 1L) / 2L) % MODULO;
        }

        if (count >= 1) {
            result = (result + search(current + 1, target - current, remain - 1) * count) % MODULO;
        }
        
        // not use
        result = (result + search(current + 1, target, remain)) % MODULO;
        return result;
    }

    public static void main(String[] args) {
        Q923 q923 = new Q923();
        System.out.println(q923.threeSumMulti(new int[]{1,1,2,2,2,2}, 5));
        System.out.println(q923.threeSumMulti(new int[]{1,1,2,2,3,3,4,4,5,5}, 8));
        System.out.println(q923.threeSumMulti(new int[]{12,97,74,39,56,3,85,39,18,29,41,7,33,97,13,47,11,52,32,45,8,36,35,45,59,54,18,55,63,65,57,63,60,71,86,76,65,12,59,83,70,100,20,2,41,70,53,39,54,64,48,93,86,100,75,100,23,2,46,54,81,10,94,32,75,31,20,35,49,46,46,96,43,75,37,37,51,86,4,18,30,73,65,1,55,22,32,12,86,100,95,24,16,40,13,95,43,87,46,86}, 213));
    }
}
