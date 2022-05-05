package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1518. Water Bottles 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/water-bottles/">
 *   1518. Water Bottles</a>
 */
public final class Q1518 {
    public int numWaterBottles(int numBottles, int numExchange) {
        // 1. 1 <= numBottles <= 100
        // 2. 2 <= numExchange <= 100        
        int result = 0;
        int emptyBottles = 0;
        while (numBottles != 0) {
            result += numBottles;
            emptyBottles += numBottles;
            numBottles = emptyBottles / numExchange;
            emptyBottles = emptyBottles % numExchange;
        }
        return result;
    }
}
