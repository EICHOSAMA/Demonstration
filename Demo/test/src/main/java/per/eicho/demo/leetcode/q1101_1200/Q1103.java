package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1103. Distribute Candies to People 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/distribute-candies-to-people/">
 *   1103. Distribute Candies to People</a>
 */
public final class Q1103 {
    public int[] distributeCandies(int candies, int num_people) {
        // 1. 1 <= candies <= 10^9
        // 2. 1 <= num_people <= 1000        
        final int[] result = new int[num_people];

        int turn = 1;
        int p = 0;
        while (candies > 0) {
            if (candies >= turn) {
                result[p++] += turn;
                candies -= turn;
            } else {
                result[p++] += candies;
                candies = 0;
            }
            p %= num_people;
            
            turn++;
        }

        return result;
    }
}
