package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1013. Partition Array Into Three Parts With Equal Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/">
 *   1013. Partition Array Into Three Parts With Equal Sum</a>
 */
public final class Q1013 {
    public boolean canThreePartsEqualSum(int[] arr) {
        // 1. 3 <= arr.length <= 5 * 10^4
        // 2. -10^4 <= arr[i] <= 10^4        
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        if (sum % 3 != 0) return false;

        int target = sum / 3;
        int time = 0b01;
        int mark = 0b00;
        sum = 0;
        for (int num : arr) {
            if (mark == 0b11) return true;
            sum += num;

            if (sum == target * time) {
                mark |= time;
                time <<= 1;
            }
        }
        return false;
    }
}
