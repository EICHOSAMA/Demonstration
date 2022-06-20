package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>722. Remove Comments 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-comments/">
 *   722. Remove Comments</a>
 */
public final class Q722 {
    public List<String> removeComments(String[] source) {
        // 1. 1 <= source.length <= 100
        // 2. 0 <= source[i].length <= 80
        // 3. source[i] consists of printable ASCII characters.
        // 4. Every open block comment is eventually closed.
        // 5. There are no single-quote or double-quote in the input.        
        final int n = source.length;
        int p = 0;
        final StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>(n);
        int status = 0; // 0: code, 1: /*
        while (p < n) {
            final String line = source[p++];

            int lp = 0;
            while (lp < line.length()) {
                char ch = line.charAt(lp++);
                
                if (status == 1) {
                    if (ch != '*') continue; //skip character

                    if (lp >= line.length()) {
                        sb.append(ch); // single *
                        continue;
                    }

                    if ((ch = line.charAt(lp++)) == '/') {
                        status = 0;
                    } else {
                        lp--;
                    }
                    continue;
                }

                // status == 0
                if (ch != '/') {
                    sb.append(ch);
                    continue;
                }

                if (lp >= line.length()) {
                    sb.append(ch);
                    break;
                }
                ch = line.charAt(lp++);
                if (ch == '/') break; // ignore left characters.
                if (ch == '*') {
                    status = 1;
                } else {
                    sb.append('/');
                    sb.append(ch);
                }
            }

            if (sb.length() != 0 && status == 0) {
                result.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Q722 q722 = new Q722();
        System.out.println(q722.removeComments(new String[]{
            "struct Node{", 
            "    /*/ declare members;/**/", 
            "    int size;", 
            "    /**/int val;", 
            "};"     
        }));
    }
}
