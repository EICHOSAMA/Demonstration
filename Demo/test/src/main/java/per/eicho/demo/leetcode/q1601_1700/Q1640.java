package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1640. Check Array Formation Through Concatenation 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-array-formation-through-concatenation/">
 *   1640. Check Array Formation Through Concatenation</a>
 */
public final class Q1640 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        // 1. 1 <= pieces.length <= arr.length <= 100
        // 2. sum(pieces[i].length) == arr.length
        // 3. 1 <= pieces[i].length <= arr.length
        // 4. 1 <= arr[i], pieces[i][j] <= 100
        // 5. The integers in arr are distinct.
        // 6. The integers in pieces are distinct 
        //    (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).        
        final int n = arr.length;
        int p = 0;
        while (p < n) {
            int num = arr[p];

            int[] pieceArr = null;
            for (int[] piece : pieces) {
                if (piece[0] == num) {
                    pieceArr = piece;
                    break;
                }
            }

            if (pieceArr == null) return false;

            int p2 = 0;

            while (p2 < pieceArr.length) {
                if (pieceArr[p2++] != arr[p++]) return false; 
            }
        } 

        return true;
    }
}
