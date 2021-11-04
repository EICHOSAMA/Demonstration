package per.eicho.demo.leetcode.q601_700;

/**
 * <p>605. Can Place Flowers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/can-place-flowers/">605. Can Place Flowers</a>
 */
public class Q605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        final int L = 0;
        final int R = flowerbed.length;
        
        for (int i = 0; i < R && n > 0; i++) {
            final int isplanted = flowerbed[i];

            // 1. planted case
            if (isplanted == 1) continue;
            
            // 2. not planted (assert isplanted = 0)
            final boolean canPlanted = 
                ((i - 1) >= L ? flowerbed[i-1] == 0 : true) && 
                ((i + 1) < R ? flowerbed[i+1] == 0 : true); 

            if (canPlanted) {
                flowerbed[i] = 1;
                n--;
            }
        }

        return n == 0;
    }
}
