package per.eicho.demo.leetcode.q301_400;

import java.util.Stack;

/**
 * <p>388. First Unique Character in a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/first-unique-character-in-a-string/">
 *   388. First Unique Character in a String</a>
 */
public final class Q388 {
    public int lengthLongestPath(String input) {
        // 1. 1 <= input.length <= 10^4
        // 2. input may contain lowercase or uppercase English letters, 
        //    a new line character '\n', a tab character '\t', a dot '.', a space ' ', and digits.
        // 3. All file and directory names have positive length.
        final Stack<String> dirLayers = new Stack<>();
        int len = 0;
        final StringBuilder sb = new StringBuilder();
        final int n = input.length();
        int lv = 0;
        boolean isFile = false;
        int result = 0;
        for (int i = 0; i <= n; i++) {
            final char ch;

            if (i == n || (ch = (input.charAt(i))) == '\n' ) {
                // new_line
                while (dirLayers.size() > lv) {
                    final String poppedDir = dirLayers.pop();
                    len -= (poppedDir.length() + 1);
                }

                final String name = sb.toString();
                sb.setLength(0);
                System.out.println(name + ":" + len);

                if (isFile) {
                    // file
                    result = Math.max(result, len + name.length());
                } else {
                    // dir
                    dirLayers.add(name);
                    len += name.length() + 1; // 'dirName/'
                }

                // init
                isFile = false;
                lv = 0;
            } else if (ch == '\t') {
                // tab
                lv++;
            } else {
                // character or '.'
                sb.append(ch);
                if (ch == '.') isFile = true;
            }
        }

        return result;
    }
}
