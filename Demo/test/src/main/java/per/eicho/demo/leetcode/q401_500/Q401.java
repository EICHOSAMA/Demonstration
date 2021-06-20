package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>401. Binary Watch 的题解代码 </p>
 * 
 * <pre>
 *  A binary watch has 4 LEDs on the top which represent the hours (0-11), 
 *  and the 6 LEDs on the bottom represent the minutes (0-59). 
 *  Each LED represents a zero or one, with the least significant bit on the right.
 * 
 *  For example, the below binary watch reads "4:51".
 * 
 *  Given an integer turnedOn which represents the number of LEDs that are currently on, 
 *  return all possible times the watch could represent. You may return the answer in any order.
 *  The hour must not contain a leading zero.
 * 
 *  For example, "01:00" is not valid. It should be "1:00".
 *  The minute must be consist of two digits and may contain a leading zero.
 * 
 *  For example, "10:2" is not valid. It should be "10:02".
 * 
 * Example 1:
 *    Input: turnedOn = 1
 *    Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * 
 * Example 2:
 *    Input: turnedOn = 9
 *    Output: []
 * 
 *  Constraints:
 *   1. 0 <= turnedOn <= 10
 * </pre>
 * @see <a href="https://leetcode.com/problems/binary-watch/">401. Binary Watch</a>
 */
final public class Q401 {
    public List<String> readBinaryWatch(int turnedOn) {
        // H: 8, 4, 2, 1
        // M: 32, 16, 8, 4, 2, 1
        final List<String> result = new ArrayList<String>();
        if (turnedOn == 9 || turnedOn == 10) {
            return result;
        }

        final int[] turnedOnFlags = new int[]{0,0,0,0,0,0,0,0,0,0};
        search(result, turnedOnFlags, turnedOn, 0);

        // out: "0:01"
        return result;
    }

    private void search(final List<String> result, final int[] turnedOnFlags, final int remain, final int index) {
        if (remain == 0) {
            ifValidThenAdd2Result(result, turnedOnFlags);
            return;
        }

        if (index >= turnedOnFlags.length) {
            return;
        }
        
        // 1. use 
        turnedOnFlags[index] = 1;
        search(result, turnedOnFlags, remain - 1, index + 1);
        turnedOnFlags[index] = 0;
        

        // 1. not use
        search(result, turnedOnFlags, remain, index + 1);
    }

    private void ifValidThenAdd2Result(final List<String> result, final int[] turnedOnFlags) {
        final int hour = turnedOnFlags[0] * 8 +
            turnedOnFlags[1] * 4 +
            turnedOnFlags[2] * 2 +
            turnedOnFlags[3] * 1;

        if (hour > 11) {
            return;
        }

        final int minute = turnedOnFlags[4] * 32 +
            turnedOnFlags[5] * 16 +
            turnedOnFlags[6] * 8 +
            turnedOnFlags[7] * 4 +
            turnedOnFlags[8] * 2 +
            turnedOnFlags[9] * 1 ;

        if (minute > 59) {
            return;
        }

        result.add(String.format("%d:%02d", hour, minute));
    }

    public static void main(String[] args) {
        Q401 q401 = new Q401();


        q401.readBinaryWatch(0);
    }
}
