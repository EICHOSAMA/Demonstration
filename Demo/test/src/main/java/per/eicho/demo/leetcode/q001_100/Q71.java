package per.eicho.demo.leetcode.q1_100;

import java.util.Stack;

/**
 * <p>71. Simplify Path 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/simplify-path/">71. Simplify Path</a>
 */
final public class Q71 {
    public static void main(String[] args) {
        Q71 q71 = new Q71();

        System.out.println(q71.simplifyPath("/a/./b/../../c/"));
    }

    private static final String SINGLE_PERIOD = ".";
    private static final String DOUBLE_PERIOD = "..";
    private static final char PATH_SEPARATOR = '/';
    

    public String simplifyPath(String path) {
        final Stack<String> paths = new Stack<>();

        final String[] tempPaths = path.split("/");

        for (String p : tempPaths) {
            if (p.length() == 0) {
                continue;
            }
            
            // 1. current directory
            if (p.equals(SINGLE_PERIOD)) {
                continue;
            }

            // 2. directory up a level,
            if (p.equals(DOUBLE_PERIOD)) {
                if (paths.size() == 0) {
                    continue;
                }

                paths.pop();
                continue;
            }

            paths.add(p);
        }

        if (paths.size() == 0) {
            return "/";
        }

        final StringBuilder sb = new StringBuilder();
        toCanonicalPath(paths, sb);

        return sb.toString();
    }

    private void toCanonicalPath(Stack<String> paths, StringBuilder sb) {
        if (paths.size() == 0) {
            return;
        }

        final String path = paths.pop();
        toCanonicalPath(paths, sb);
        sb.append(PATH_SEPARATOR).append(path);
    }

}
