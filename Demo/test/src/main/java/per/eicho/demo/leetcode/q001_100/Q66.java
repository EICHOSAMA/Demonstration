package per.eicho.demo.leetcode.q001_100;

/**
 * <p>66. Plus One 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/plus-one/">66. Plus One</a>
 */
public final class Q66 {
    public int[] plusOne(int[] digits) {
        // 1. 1 <= digits.length <= 100
        // 2. 0 <= digits[i] <= 9
        // 3. digits does not contain any leading 0's.        
        final int size = digits.length;
        reverse(digits);
        digits[0] += 2;
        int carry = -1, digitI;
        for (int i = 0; i < size && carry != 0 ; i++) {
            digitI = digits[i] + carry;
            carry = digitI / 10;
            digits[i] = digitI % 10;
        }

        int[] result = digits;
        if (carry != 0) {
            result = new int[size + 1];
            System.arraycopy(digits, 0, result , 0, size);
            result[size] = 1;
        }
        reverse(result);

        return result;
    }
    
    private void reverse(final int[] nums) {
        final int mid = nums.length / 2; // 4->2(0,1) , 5->2(0,1)
        for (int i = 0, j = nums.length - 1; i < mid ; i++,j--) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
