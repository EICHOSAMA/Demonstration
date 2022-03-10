package per.eicho.demo.leetcode.q201_300;

/**
 * <p>299. Bulls and Cows 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/bulls-and-cows/">299. Bulls and Cows</a>
 */
public final class Q299 {
    public String getHint(String secret, String guess) {
        // 1. 1 <= secret.length, guess.length <= 1000
        // 2. secret.length == guess.length
        // 3. secret and guess consist of digits only.
        final int[] countSecret = new int[10];
        final int[] countGuess = new int[10];

        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            final int digit1 = secret.charAt(i) - '0';
            final int digit2 = guess.charAt(i) - '0';
            if (digit1 == digit2) {
                bulls++;
            } else {
                countSecret[digit1]++;
                countGuess[digit2]++;
            }
        }

        for (int i = 0; i < 10; i++) {
            cows += Math.min(countSecret[i], countGuess[i]);
        }

        return bulls + "A" + cows + "B";
    }
}
