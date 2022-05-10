package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1629. Slowest Key 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/slowest-key/">
 *   1629. Slowest Key</a>
 */
public final class Q1629 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        // 1. releaseTimes.length == n
        // 2. keysPressed.length == n
        // 3. 2 <= n <= 1000
        // 4. 1 <= releaseTimes[i] <= 109
        // 5. releaseTimes[i] < releaseTimes[i+1]
        // 6. keysPressed contains only lowercase English letters.        
        final int n = releaseTimes.length;
        final int[] durations = new int[26];
        int maxDuration = 0;
        char result = 'a';

        int time = 0;
        for (int i = 0; i < n; i++) {
            final int releaseTime = releaseTimes[i];
            final char keyPressed = keysPressed.charAt(i);
            final int idx = keyPressed - 'a';

            if (durations[idx] < releaseTime - time) {
                durations[idx] = releaseTime - time;
                if (durations[idx] > maxDuration) {
                    maxDuration = durations[idx];
                    result = keyPressed;
                } else if (durations[idx] == maxDuration && keyPressed > result) {
                    result = keyPressed;
                }
            }
            time = releaseTime;
        }
        
        return result;
    }
}
