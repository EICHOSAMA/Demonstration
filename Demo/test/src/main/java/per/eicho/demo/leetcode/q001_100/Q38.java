package per.eicho.demo.leetcode.q001_100;

/**
 * <p>38. Count and Say 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-and-say/">38. Count and Say</a>
 */
public final class Q38 {
    public String countAndSay(int n) {
        // 1. 1 <= n <= 30
        if (n == 1) return "1";
        return say(countAndSay(n-1));
    }

    private String say(String lastSequence) {
        StringBuilder sb = new StringBuilder(lastSequence.length() * 2);


        int cursor = -1; // processing
        while (++cursor < lastSequence.length()) {
            char ch = lastSequence.charAt(cursor);

            int count = 1;
            // count ch
            while (cursor + 1 < lastSequence.length() && lastSequence.charAt(cursor + 1) == ch) {
                cursor++;
                count++;
            }

            // add to result.
            sb.append(count).append(ch);
        }
        //assert cursor == lastSequence.length();
        return sb.toString();
    }
}
