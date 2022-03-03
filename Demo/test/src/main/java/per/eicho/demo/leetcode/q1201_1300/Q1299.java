package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1299. Replace Elements with Greatest Element on Right Side 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/">
 *   1299. Replace Elements with Greatest Element on Right Side</a>
 */
public final class Q1299 {
    public int[] replaceElements(int[] arr) {
        // 1. 1 <= arr.length <= 10^4
        // 2. 1 <= arr[i] <= 10^5        
        int greatestElement = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            final int num = arr[i];
            arr[i] = greatestElement;
            if (num > greatestElement) greatestElement = num;
        }

        return arr;
    }
}
