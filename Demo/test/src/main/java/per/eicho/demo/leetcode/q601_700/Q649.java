package per.eicho.demo.leetcode.q601_700;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>649. Dota2 Senate 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/dota2-senate/">
 *   649. Dota2 Senate</a>
 */
public final class Q649 {
    public String predictPartyVictory(String senate) {
        // 1. n == senate.length
        // 2. 1 <= n <= 10^4
        // 3. senate[i] is either 'R' or 'D'.
        // final Character R = 'R';
        final Character D = 'D';

        Deque<Character> currentRound = new LinkedList<>();
        Deque<Character> nextRound = new LinkedList<>();

        int countR = 0;
        int countD = 0;
        final int n = senate.length();
        for (int i = 0; i < n; i++) {
            final char ch = senate.charAt(i);

            if (currentRound.isEmpty()) {
                currentRound.add(ch);
                continue;
            }

            if (ch == currentRound.peekFirst().charValue()) {
                currentRound.add(ch);
            } else {
                nextRound.addLast(currentRound.pollFirst());
                if (nextRound.peekLast().equals(D)) {
                    countD++;
                } else {
                    countR++;
                }
            }
        }

        while (!currentRound.isEmpty()) {
            nextRound.addFirst(currentRound.pollLast());
            if (nextRound.peekFirst().equals(D)) {
                countD++;
            } else {
                countR++;
            }
        }

        while (countD != 0 && countR != 0) {
            final Deque<Character> senators = nextRound;
            nextRound = new LinkedList<>();

            countD = 0;
            countR = 0;

            final int len = senators.size();
            for (int i = 0; i < len; i++) {
                final Character ch = senators.pollFirst();

                if (currentRound.isEmpty()) {
                    currentRound.add(ch);
                    continue;
                }
    
                if (ch == currentRound.peekFirst()) {
                    currentRound.add(ch);
                } else {
                    nextRound.addLast(currentRound.pollFirst());
                    if (nextRound.peekLast().equals(D)) {
                        countD++;
                    } else {
                        countR++;
                    }
                }
            }

            while (!currentRound.isEmpty()) {
                nextRound.addFirst(currentRound.pollLast());
                if (nextRound.peekFirst().equals(D)) {
                    countD++;
                } else {
                    countR++;
                }
            }
        }

        return countD == 0 ? "Radiant" : "Dire";
    }
}
