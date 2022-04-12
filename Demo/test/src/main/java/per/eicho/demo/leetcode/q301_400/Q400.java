package per.eicho.demo.leetcode.q301_400;

/**
 * <p>400. Nth Digit 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/nth-digit/">400. Nth Digit</a>
 */
public class Q400 {
    public int findNthDigit(int n) {
        // 1. 1 <= n <= 2^31 - 1        
        long num = n;
        long l = 1, r= 9;
        long digitCount = 1;
        long totalDigitsCountOfRangeLR;
        while (num != 0) {
            totalDigitsCountOfRangeLR = (r - l + 1) * digitCount;
            if (num > totalDigitsCountOfRangeLR) {
                num -= totalDigitsCountOfRangeLR;

                l = r + 1;
                r = l * 10 - 1;
                digitCount++;
            } else {
                long targetNumber = l + (num - 1) / digitCount; // 103 -> target:100
                num = ((num - 1) % digitCount) + 1; // 1 , 2 , 3 , ..
                // get n th digit at temp2.
                while (digitCount != num) {
                    digitCount--;
                    targetNumber /= 10;
                }
                return (int)(targetNumber % 10);
            }
        }
        return 0;
    }
}
